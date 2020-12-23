import java.util.Arrays;

/**
 * https://leetcode.com/problems/longest-common-subsequence/
 * <p>
 * Given two strings text1 and text2, return the length of their longest common subsequence.
 * <p>
 * A subsequence of a string is a new string generated from the original string with some characters(can be none) deleted without
 * changing the relative order of the remaining characters. (eg, "ace" is a subsequence of "abcde" while "aec" is not).
 * A common subsequence of two strings is a subsequence that is common to both strings.
 * <p>
 * If there is no common subsequence, return 0.
 * <p>
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 * <p>
 * Another question that indirectly uses LCS
 * https://leetcode.com/discuss/interview-question/981597/rubrik-oa-question-minimum-operations-to-transform-array
 * <p>
 * Given two arrays of size N each containing the elements 1 to N, find the minimum number of operations needed to transform the second array into the first.
 * <p>
 * An operation is defined as:
 * Taking any current value in an array and moving it to the beginning or end.
 * <p>
 * Example:
 * Turn Array2 into Array1
 * <p>
 * Array1 = [4,2,3,1,5,6]
 * Array2 = [3,1,4,6,5,2]
 * <p>
 * Returns 3:
 * <p>
 * Operation 1: Move 2 to beginning.
 * Array2 = [2,3,1,4,6,5]
 * <p>
 * Operation 2: Move 4 to beginning.
 * Array2 = [4,2,3,1,6,5]
 * <p>
 * Operation 3: Move 6 to end:
 * Array2 = [4,2,3,1,5,6]
 */
public class LongestCommonSubsequence {
    int ans;

    public int longestCommonSubsequenceSimplified(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        //Important thing to note here is that initializing it with + 1 rows/columns simplifies the if/else logic
        int max = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

    /**
     * Top down code is similar to the one used in {@link LongestCommonSubarray}
     */
    public int longestCommonSubsequenceTopDown(String A, String B) {
        int[][] memoized = new int[A.length()][B.length()];
        for (int[] ints : memoized) {
            Arrays.fill(ints, -1);
        }
        recur(A, B, A.length() - 1, B.length() - 1, memoized);
        return ans;
    }

    private int recur(String a, String b, int a_index, int b_index, int[][] memoized) {
        if (a_index < 0 || b_index < 0) {
            return 0;
        }
        if (memoized[a_index][b_index] != -1) {
            return memoized[a_index][b_index];
        }
        if (a.charAt(a_index) == b.charAt(b_index)) {
            //if characters match, we are guaranteed to include this character in the result
            //hence, we can safely return from here
            int len = 1 + recur(a, b, a_index - 1, b_index - 1, memoized);
            ans = Math.max(ans, len);
            return memoized[a_index][b_index] = len;
        }
        int len1 = recur(a, b, a_index - 1, b_index, memoized);
        int len2 = recur(a, b, a_index, b_index - 1, memoized);
        return memoized[a_index][b_index] = Math.max(len1, len2);
    }
}
