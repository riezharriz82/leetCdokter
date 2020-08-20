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
    /**
     * Problem is similar to finding longest common substring
     * Top down solution was pretty complex to understand, hence going with bottom up solution
     * But the recursive formula for top down solution should be understood
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
}
