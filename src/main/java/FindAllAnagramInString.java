import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 * <p>
 * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
 * <p>
 * The order of output does not matter.
 * <p>
 * Input:
 * s: "cbaebabacd" p: "abc"
 * <p>
 * Output:
 * [0, 6]
 * <p>
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 */
public class FindAllAnagramInString {
    /**
     * Approach: Sliding Window, keep increasing the window until the current window contains all the required chars
     * Once done, keep shrinking the window to see if the window size can be reduced to match the target string length
     * If yes, we found ourselves an anagram
     * <p>
     * {@link PermutationInString} {@link MinimumWindowSubstring} for related problem
     */
    public List<Integer> findAnagrams(String s, String p) {
        if (s.length() < p.length()) {
            return Arrays.asList();
        }
        //count of characters for the key
        int[] count = buildKeyCharCountMap(p);
        List<Integer> res = new ArrayList<>();
        int begin = 0, end = 0, requiredChars = 0;
        while (end < s.length()) {
            char last = s.charAt(end);
            if (count[last - 'a'] > 0) {
                requiredChars++;
            }
            count[last - 'a']--;
            while (requiredChars == p.length()) {
                if (end - begin + 1 == p.length()) { //if the window size equals the target size
                    res.add(begin);
                }
                char first = s.charAt(begin);
                if (count[first - 'a'] == 0) {
                    requiredChars--;
                }
                count[first - 'a']++;
                begin++;
            }
            end++;
        }
        return res;
    }

    private int[] buildKeyCharCountMap(String p) {
        int[] count = new int[26];
        for (char c : p.toCharArray()) {
            count[c - 'a']++;
        }
        return count;
    }
}
