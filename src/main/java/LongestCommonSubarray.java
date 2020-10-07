import java.util.Arrays;

/**
 * https://leetcode.com/problems/maximum-length-of-repeated-subarray/
 * <p>
 * Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.
 * <p>
 * Input:
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * Output: 3
 * The repeated subarray with maximum length is [3, 2, 1].
 */
public class LongestCommonSubarray {
    int ans;

    /**
     * Problem is similar to finding longest common substring
     * Recursive formula for top down solution should be understood
     * f(A, B, m, n, lcsCount)
     * if (A[m] == B[n]) then candidate1 = f (A, B, m-1, n-1, lcsCount + 1)
     * candidate2 = f(A, B, m-1, n, lcsCount)
     * candidate3 = f(A, B, m, n-1, lcsCount)
     * return max(candidate1, candidate2, candidate3);
     * <p>
     * A think to note in the case when last characters where same -- we did not do 1 + f(A, B, m-1, n-1) because it won't guarantee
     * that the result would be part of current substring
     */
    public int findLength(int[] A, int[] B) {
        int m = A.length, n = B.length;
        int[][] dp = new int[m][n];
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    if (A[i] == B[j]) {
                        dp[i][j] = 1;
                    }
                } else {
                    if (A[i] == B[j]) {
                        //extend the results from the previous substring
                        dp[i][j] = 1 + dp[i - 1][j - 1];
                    }
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        //if asked to return the contents of the subarray, then just find the max (i,j) and then traverse (i-1,j-1)
        return max;
    }

    /**
     * Approach: Tricky return conditions
     */
    public int findLengthTopDown(int[] A, int[] B) {
        int[][] memoized = new int[A.length][B.length];
        for (int[] ints : memoized) {
            Arrays.fill(ints, -1);
        }
        recur(A, B, A.length - 1, B.length - 1, memoized);
        return ans;
    }

    private int recur(int[] a, int[] b, int a_index, int b_index, int[][] memoized) {
        if (a_index < 0 || b_index < 0) {
            return 0;
        }
        if (memoized[a_index][b_index] != -1) {
            return memoized[a_index][b_index];
        }
        //first recurse all possible combinations
        recur(a, b, a_index - 1, b_index, memoized);
        recur(a, b, a_index, b_index - 1, memoized);
        if (a[a_index] == b[b_index]) { //this check needs to be done after recursing all possible combinations
            int len = 1 + recur(a, b, a_index - 1, b_index - 1, memoized);
            ans = Math.max(ans, len);
            return memoized[a_index][b_index] = len; //returning here is important, otherwise length will be cascaded wrong
        }
        return memoized[a_index][b_index] = 0; //returning 0 is important
    }
}
