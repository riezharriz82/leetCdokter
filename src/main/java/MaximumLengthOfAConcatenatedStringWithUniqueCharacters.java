import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/
 *
 * Given an array of strings arr. String s is a concatenation of a sub-sequence of arr which have unique characters.
 *
 * Return the maximum possible length of s.
 *
 * Input: arr = ["un","iq","ue"]
 * Output: 4
 * Explanation: All possible concatenations are "","un","iq","ue","uniq" and "ique".
 * Maximum length is 4.
 *
 * Input: arr = ["cha","r","act","ers"]
 * Output: 6
 * Explanation: Possible solutions are "chaers" and "acters".
 *
 * Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
 * Output: 26
 *
 * Constraints:
 * 1 <= arr.length <= 16
 * 1 <= arr[i].length <= 26
 * arr[i] contains only lower case English letters.
 * </pre>
 */
public class MaximumLengthOfAConcatenatedStringWithUniqueCharacters {
    /**
     * Approach: Bitmasking + Backtracking, Every string has two options, either get picked or be skipped in the result string.
     * We pick a string if it does not add duplicate char to the result.
     * How do we check that? Instead of looping through the characters and finding whether a char is duplicate we can maintain a bitset for each word.
     * If we perform & on two integers, if they don't have any common bits, result should be 0.
     * <p>
     * Initially I was keeping track of the actual concatenated string but then realized that it's not really required as the no of set bits in
     * the curMask can tell the actual length of concatenated string.
     * <p>
     * Memoization is optional as the constraints are not that high. Very happy to solve this on my own.
     * <p>
     * {@link MaximumProductOfWordLengths} {@link Subsets}
     */
    public int maxLength(List<String> arr) {
        int n = arr.size();
        List<Integer> bitmask = new ArrayList<>(n); //stores bitmask for each word containing unique chars
        List<String> uniqueChars = new ArrayList<>(n);
        for (String word : arr) {
            int mask = 0;
            boolean hasDuplicateChars = false;
            for (char c : word.toCharArray()) {
                int index = 1 << (c - 'a');
                if (Integer.bitCount(mask & index) == 1) { //duplicate char found, can also be written as (mask & index) > 0
                    hasDuplicateChars = true;
                    break;
                } else {
                    mask |= index;
                }
            }
            if (!hasDuplicateChars) {
                uniqueChars.add(word);
                bitmask.add(mask);
            }
        }
        Map<Pair<Integer, Integer>, Integer> memo = new HashMap<>(); //constraints of the problem statement are too low to demand memoization
        //adding memoization increases the actual runtime from 2 ms to 10 ms
        return recur(uniqueChars, bitmask, 0, 0, memo);
    }

    private int recur(List<String> uniqueChars, List<Integer> bitmask, int index, int curMask, Map<Pair<Integer, Integer>, Integer> memo) {
        Pair<Integer, Integer> key = new Pair<>(index, curMask);
        if (index == uniqueChars.size()) {
            return Integer.bitCount(curMask); //no of set bits is the max length of concatenated string as the string does not contain duplicate chars
        } else if (memo.containsKey(key)) {
            return memo.get(key);
        } else {
            //skip index
            int len1 = recur(uniqueChars, bitmask, index + 1, curMask, memo);
            //pick index if it does not add duplicate chars to candidate
            int len2 = 0;
            if ((curMask & bitmask.get(index)) == 0) {
                int newMask = curMask | bitmask.get(index); //no need to actually perform string concatenation as we don't need to return the concatenated string
                len2 = recur(uniqueChars, bitmask, index + 1, newMask, memo);
            }
            int res = Math.max(len1, len2);
            memo.put(key, res);
            return res;
        }
    }
}
