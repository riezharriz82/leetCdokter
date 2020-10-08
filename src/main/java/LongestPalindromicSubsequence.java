import java.util.Arrays;

/**
 * https://leetcode.com/problems/longest-palindromic-subsequence/
 * <p>
 * Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.
 * <p>
 * Input:
 * "bbbab"
 * Output:
 * 4
 * One possible longest palindromic subsequence is "bbbb".
 */
public class LongestPalindromicSubsequence {
    int res = 0;

    /**
     * {@link LongestPalindromicSubstring} {@link LongestCommonSubarray} {@link LongestCommonSubsequence} for related problem
     * <p>
     * Approach: Top Down Recursion with memoization, if s[left] == s[right] then candidate length will be 2 + recur(left+1, right-1)
     * Otherwise it will be max(recur(left+1, right), recur(left, right-1))
     */
    public int longestPalindromeSubseq(String s) {
        int[][] memoized = new int[s.length()][s.length()];
        for (int[] ints : memoized) {
            Arrays.fill(ints, -1);
        }
        recur(s, 0, s.length() - 1, memoized);
        return res;
    }

    private int recur(String s, int left, int right, int[][] memoized) {
        if (memoized[left][right] != -1) {
            return memoized[left][right];
        }
        if (left == right) { //base case for odd length substring
            if (1 > res) {
                res = 1;
            }
            return memoized[left][right] = 1;
        }
        if (left > right) { //base case for even length subarray
            return memoized[left][right] = 0;
        }
        if (s.charAt(left) == s.charAt(right)) { //if characters match, they are guaranteed to be part of the candidate substring
            int remaining = recur(s, left + 1, right - 1, memoized);
            if (2 + remaining > res) {
                res = 2 + remaining;
            }
            return memoized[left][right] = 2 + remaining;
        }
        int opt1 = recur(s, left + 1, right, memoized);
        int opt2 = recur(s, left, right - 1, memoized);
        return memoized[left][right] = Math.max(opt1, opt2); //whichever option gives a better result
    }
}
