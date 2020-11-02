/**
 * https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/
 * <p>
 * Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every character in T appears no less than k times.
 * <p>
 * Input: s = "aaabb", k = 3
 * Output: 3
 * The longest substring is "aaa", as 'a' is repeated 3 times.
 * <p>
 * Input: s = "ababbc", k = 2
 * Output: 5
 * The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 */
public class LongestSubstringWithAtLeastKRepeatingCharacters {
    /**
     * Approach: During my thought process, I was able to think that the characters with count < k are invalid and will not contribute anything.
     * I used those characters as breaking point during sliding window but couldn't decide when to move the begin pointer of the window.
     * <p>
     * Instead of using sliding window, recursion should be applied at all the substrings split by invalid characters
     * eg for input abaabcabad, 2
     * c and d can't contribute anything
     * so first recur for abaab, then recur for aba
     * abaab is a valid string with no bad characters
     * in aba, b is a bad character, so recur for a and a
     */
    public int longestSubstringRecursive(String s, int k) {
        return recur(s, k);
    }

    private int recur(String s, int k) {
        if (s.length() == 0) {
            return 0;
        }
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }
        boolean invalidChar = false; //character with count < k
        for (int i = 0; i < 26; i++) {
            if (cnt[i] > 0 && cnt[i] < k) {
                invalidChar = true;
                break;
            }
        }
        if (!invalidChar) { //if there are no invalid characters, entire string is valid
            return s.length();
        }
        int result = 0, begin = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (cnt[c - 'a'] < k) { //bad character found, split it and recur
                result = Math.max(result, recur(s.substring(begin, i), k));
                begin = i + 1;
            }
        }
        result = Math.max(result, recur(s.substring(begin), k));
        return result;
    }

    /**
     * Approach: Very tricky unseen Sliding Window pattern
     * In sliding window questions, the trickiest thing is to identify when to shrink the window from the left. We generally shrink the window
     * only if the current window is valid and we try to shrink from the left to see whether we can find a smaller valid window
     * <p>
     * Here we don't see any pattern when to shrink the window, so we chose to shrink window when the unique characters in the window crosses a threshold
     * Weird logic but no other way
     * {@link SentenceScreenFitting} for similar tricky unseen pattern
     */
    public int longestSubstringSlidingWindow(String s, int k) {
        int result = 0;
        for (int uniqueCharCounts = 1; uniqueCharCounts < 26; uniqueCharCounts++) {
            int[] cnts = new int[26];
            int begin = 0, end = 0;
            int curUniqueChars = 0, charsWithCountAtLeastK = 0;
            while (end < s.length()) {
                if (curUniqueChars <= uniqueCharCounts) { //if current window has valid no of unique chars, expand to the right
                    char c = s.charAt(end);
                    cnts[c - 'a']++;
                    if (cnts[c - 'a'] == 1) {
                        curUniqueChars++;
                    }
                    if (cnts[c - 'a'] == k) {
                        charsWithCountAtLeastK++;
                    }
                    end++;
                } else { //if current window has more unique chars, shrink the window
                    char c = s.charAt(begin);
                    cnts[c - 'a']--;
                    if (cnts[c - 'a'] == 0) {
                        curUniqueChars--;
                    }
                    if (cnts[c - 'a'] == k - 1) {
                        charsWithCountAtLeastK--;
                    }
                    begin++;
                }
                if (curUniqueChars == uniqueCharCounts && charsWithCountAtLeastK == curUniqueChars) {
                    //valid window found with all characters repeating at least k times
                    result = Math.max(result, end - begin);
                }
            }
        }
        return result;
    }
}
