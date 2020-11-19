/**
 * https://leetcode.com/problems/permutation-in-string/
 * <p>
 * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.
 * <p>
 * Input: s1 = "ab" s2 = "eidbaooo"
 * Output: True
 * Explanation: s2 contains one permutation of s1 ("ba").
 * <p>
 * Input:s1= "ab" s2 = "eidboaoo"
 * Output: False
 */
public class PermutationInString {
    /**
     * Approach: Use Sliding Window template to find whether the current window is an anagram of s1 or not
     * Leverage prior learnings of when to increment/decrement the window
     * <p>
     * Keep track of valid chars in current window, if valid chars == s1.length, then check whether the current window size == s1.length
     * i.e. check whether the current window contains only the chars from s1.
     * If yes, we have found ourselves a valid anagram
     * If no, continue shrinking the window until window becomes invalid ie. valid chars != s1.length
     * <p>
     * {@link MinimumWindowSubstring} {@link FindAllAnagramInString} {@link LongestRepeatingCharacterReplacement} {@link LongestSubstringWithAtMostKDistinctCharacters}
     * {@link LongestSubstringWithAtLeastKRepeatingCharacters}
     */
    public boolean checkInclusion(String s1, String s2) {
        int[] cnt = new int[26];
        for (char c : s1.toCharArray()) {
            cnt[c - 'a']++;
        }
        int begin = 0, end = 0, requiredCharsInWindow = 0;
        while (end < s2.length()) {
            char c = s2.charAt(end);
            if (cnt[c - 'a'] > 0) {
                requiredCharsInWindow++; //found one required char
            }
            cnt[c - 'a']--;
            while (requiredCharsInWindow == s1.length()) { //if all required chars have been found, check if window can be shrunk to match s1.length
                int windowLength = end - begin + 1;
                if (windowLength == s1.length()) {
                    //current window is an anagram of s1
                    return true;
                }
                char start = s2.charAt(begin);
                if (cnt[start - 'a'] == 0) {
                    //one of the required chars is sliding off the window
                    requiredCharsInWindow--;
                }
                cnt[start - 'a']++;
                begin++;
            }
            end++;
        }
        return false;
    }
}
