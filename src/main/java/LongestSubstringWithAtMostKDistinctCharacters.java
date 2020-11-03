/**
 * https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
 * <p>
 * Given a string, find the length of the longest substring T that contains at most k distinct characters.
 * <p>
 * Input: s = "eceba", k = 2
 * Output: 3
 * Explanation: T is "ece" which its length is 3.
 * <p>
 * Input: s = "aa", k = 1
 * Output: 2
 * Explanation: T is "aa" which its length is 2.
 */
public class LongestSubstringWithAtMostKDistinctCharacters {
    /**
     * Approach: Standard longest Sliding window paradigm, keep increasing the window until the window has num distinct chars > k
     * Then keep shrinking the window until the condition is invalid
     * <p>
     * {@link LongestSubstringWithAtLeastKRepeatingCharacters} {@link LongestRepeatingCharacterReplacement} {@link LongestSubstringWithoutRepeatingCharacters}
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int begin = 0, end = 0, result = 0;
        int[] cnt = new int[256];
        int distinctChars = 0;
        while (end < s.length()) {
            char last = s.charAt(end);
            if (cnt[last] == 0) {
                distinctChars++;
            }
            cnt[last]++;
            while (distinctChars > k) { //if condition is invalid, need to shrink the window until it becomes valid
                char first = s.charAt(begin);
                if (cnt[first] == 1) {
                    distinctChars--;
                }
                cnt[first]--;
                begin++;
            }
            result = Math.max(result, end - begin + 1);
            end++;
        }
        return result;
    }
}
