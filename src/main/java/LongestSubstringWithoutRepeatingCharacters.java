import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * <p>
 * Given a string, find the length of the longest substring without repeating characters.
 * <p>
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * <p>
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 */
public class LongestSubstringWithoutRepeatingCharacters {

    /**
     * Sliding window problem. Time Complexity O(2*n)
     * Increase the window size if no repeating character found, decrease the window size if repeating character found
     * {@link FindAllAnagramInString} for similar problem
     * <p>
     * An optimization to the problem would be to set the begin pointer directly after the first occurrence of the duplicate character (if i is behind that index)
     * eg. abba
     * so that we don't move the begin pointer one by one
     */
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int begin = 0, end = 0, res = 0;
        while (end < s.length()) {
            char windowLastChar = s.charAt(end);
            if (!set.contains(windowLastChar)) {
                set.add(windowLastChar);
                res = Math.max(res, end - begin + 1);
                end++;
            } else {
                char windowFirstChar = s.charAt(begin);
                set.remove(windowFirstChar);
                begin++;
            }
        }
        return res;
    }

    public int lengthOfLongestSubstringOptimized(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i); //max is required so that we don't decrement i to a smaller value
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }
}
