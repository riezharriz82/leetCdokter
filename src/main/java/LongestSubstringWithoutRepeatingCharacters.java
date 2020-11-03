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
     * Increase the window size if adding the character at the end does not introduce a repeating character, otherwise keep shrinking the
     * window until this character can be added
     * {@link FindAllAnagramInString} {@link LongestSubstringWithAtMostKDistinctCharacters} for related problem
     */
    public int lengthOfLongestSubstringSlidingWindow(String s) {
        int begin = 0, end = 0, result = 0;
        int[] cnt = new int[256];
        while (end < s.length()) {
            char last = s.charAt(end);
            cnt[last]++;
            while (cnt[last] > 1) { //if this character is already present in the window, keep shrinking the window until it's possible to add this character
                char first = s.charAt(begin);
                cnt[first]--;
                begin++;
            }
            result = Math.max(result, end - begin + 1);
            end++;
        }
        return result;
    }
}
