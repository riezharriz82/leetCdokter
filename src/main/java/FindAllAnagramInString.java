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
    public List<Integer> findAnagrams(String s, String p) {
        if (s.length() < p.length()) {
            return Arrays.asList();
        }
        int window = p.length();
        //count of characters for the key
        int[] keyCount = buildKeyCharCountMap(p);
        List<Integer> res = new ArrayList<>();
        int begin = 0, end = 0;
        while (end < s.length()) {
            if (keyCount[s.charAt(end) - 'a'] > 0) {
                keyCount[s.charAt(end) - 'a']--;
                if (end - begin + 1 == window) { //window of size equals key length found
                    res.add(begin);
                }
                end++; //increase the window size if part of the anagram
            } else if (begin == end) { //single character which is not part of the key
                begin++;
                end++;
            } else { //decrease the window size
                keyCount[s.charAt(begin) - 'a']++;
                begin++;
            }
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
