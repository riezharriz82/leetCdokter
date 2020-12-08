/**
 * https://leetcode.com/problems/longest-repeating-character-replacement/
 * <p>
 * Given a string s that consists of only uppercase English letters, you can perform at most k operations on that string.
 * <p>
 * In one operation, you can choose any character of the string and change it to any other uppercase English character.
 * <p>
 * Find the length of the longest sub-string containing all repeating letters you can get after performing the above operations.
 * <p>
 * Input:
 * s = "ABAB", k = 2
 * Output:
 * 4
 * <p>
 * Explanation:
 * Replace the two 'A's with two 'B's or vice versa.
 * <p>
 * Input:
 * s = "AABABBA", k = 1
 * Output:
 * 4
 * <p>
 * Explanation:
 * Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 * The substring "BBBB" has the longest repeating letters, which is 4.
 */
public class LongestRepeatingCharacterReplacement {
    /**
     * Approach: Use sliding window to find the longest valid window.
     * To find the longest window, you have to keep increasing the window until it becomes invalid,
     * post that start shrinking the window until it becomes valid again
     * {@link SubarrayProductLessThanK} for similar longest sliding window problem
     * <p>
     * Strategy to find the longest window is different than finding the smallest valid window.
     * In smallest window, you keep expanding the window till you find the first valid window, then start shrinking until it becomes invalid
     * {@link MinimumWindowSubstring} for similar shortest sliding window problem
     * <p>
     * Here valid window is defined by the number of characters that needs to be changed to make entire window full of same characters
     * if a window has a=3, b=2, c=6, to make all characters same min no of operations is 3+2=5, i.e. all the characters need to be changed to most
     * frequent character to transform the window in min steps
     * Now if required operations <= k, then window is valid otherwise its invalid and we need to shrink window until it becomes valid again
     * <p>
     * I was able to solve this question on my own but got confused a bit during implementation of sliding window
     *
     * Another way of solving this problem would be to leverage binary search to find the answer between 1 and length of string
     * but that would be quasilinear solution. Binary search can be used to solve optimization problems, if you can't think of linear solution
     */
    public int characterReplacement(String s, int k) {
        int begin = 0, end = 0;
        int result = 0;
        int[] cnt = new int[26];
        while (end < s.length()) {
            char last = s.charAt(end);
            cnt[last - 'A']++;
            int operationsRequiredToMakeAllCharactersOfWindowSame = operationsRequired(cnt);
            while (operationsRequiredToMakeAllCharactersOfWindowSame > k) { //if window is invalid, shrink from the left
                char first = s.charAt(begin);
                cnt[first - 'A']--;
                operationsRequiredToMakeAllCharactersOfWindowSame = operationsRequired(cnt);
                begin++;
            }
            result = Math.max(result, end - begin + 1);
            end++;
        }
        return result;
    }

    private int operationsRequired(int[] cnt) {
        int totalLength = 0, maxFreq = 0;
        for (int i = 0; i < 26; i++) {
            totalLength += cnt[i];
            maxFreq = Math.max(maxFreq, cnt[i]);
        }
        //all characters apart from the most frequent character needs to be changed to the most frequent character so that
        //the all characters of the window becomes same in min steps
        return totalLength - maxFreq;
    }
}
