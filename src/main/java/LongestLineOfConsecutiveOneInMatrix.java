import java.util.Arrays;

/**
 * https://leetcode.com/problems/longest-line-of-consecutive-one-in-matrix/ Premium
 * <p>
 * Given a 01 matrix M, find the longest line of consecutive one in the matrix. The line could be horizontal, vertical, diagonal or anti-diagonal.
 * Example:
 * Input:
 * [[0,1,1,0],
 * [0,1,1,0],
 * [0,0,0,1]]
 * Output: 3
 * Hint: The number of elements in the given matrix will not exceed 10,000.
 */
public class LongestLineOfConsecutiveOneInMatrix {
    /**
     * Approach: DP, instead of running 4 loops, and separately tracking horizontal, vertical and diagonal lines, directly
     * update the longest length so far by using a 4D DP
     * TimeComplexity: O(mn)
     * <p>
     * {@link LargestPlusSign} {@link alternate.ToeplitzMatrix} {@link NQueen}
     */
    public int longestLineDP(int[][] M) {
        int m = M.length;
        if (m == 0) {
            return 0;
        }
        int n = M[0].length, ans = 0;
        int[][][] dp = new int[m][n][4];
        for (int i = 0; i < m; i++) {
            //[0] -> horizontal, [1] -> vertical, [2] -> diagonal, [3] -> anti diagonal
            for (int j = 0; j < n; j++) {
                if (M[i][j] == 1) {
                    dp[i][j][0] = j == 0 ? 1 : 1 + dp[i][j - 1][0]; //horizontal
                    dp[i][j][1] = i == 0 ? 1 : 1 + dp[i - 1][j][1]; //vertical
                    dp[i][j][2] = (i == 0 || j == 0) ? 1 : 1 + dp[i - 1][j - 1][2]; //diagonal
                    dp[i][j][3] = (i == 0 || j == n - 1) ? 1 : 1 + dp[i - 1][j + 1][3]; //anti diagonal
                    for (int k = 0; k < 4; k++) {
                        ans = Math.max(dp[i][j][k], ans);
                    }
                }
            }
        }
        return ans;
    }

    /**
     * Approach: Perform a linear scan of all horizontal, vertical and diagonal lines and keep track of max consecutive ones
     * For diagonal lines, leverage the fact of using row-col, row+col as a key
     * Notice that row-col can be negative, so make it +ve by adding +n as an offset, so that we can use array for holding
     * TimeComplexity: O(4mn)
     */
    public int longestLineBruteForce(int[][] M) {
        int m = M.length;
        if (m == 0) {
            return 0;
        }
        int n = M[0].length;
        int ans = 0;
        //horizontal
        for (int row = 0; row < m; row++) {
            int curConsecutiveOnes = 0;
            for (int col = 0; col < n; col++) {
                if (M[row][col] == 1) {
                    curConsecutiveOnes++;
                } else {
                    ans = Math.max(curConsecutiveOnes, ans);
                    curConsecutiveOnes = 0;
                }
            }
            ans = Math.max(curConsecutiveOnes, ans);
        }
        //vertical
        for (int col = 0; col < n; col++) {
            int curConsecutiveOnes = 0;
            for (int row = 0; row < m; row++) {
                if (M[row][col] == 1) {
                    curConsecutiveOnes++;
                } else {
                    ans = Math.max(curConsecutiveOnes, ans);
                    curConsecutiveOnes = 0;
                }
            }
            ans = Math.max(curConsecutiveOnes, ans);
        }
        int[] diagonal = new int[m + n];
        //anti diagonal /
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                //carefully notice the key, row - col + n (+n to make it positive, allows us to use array)
                if (M[row][col] == 1) {
                    diagonal[row - col + n]++; //carry forward
                } else {
                    diagonal[row - col + n] = 0; //reset
                }
                ans = Math.max(diagonal[row - col + n], ans);
            }
        }
        Arrays.fill(diagonal, 0);
        //diagonal \
        for (int row = 0; row < m; row++) {
            for (int col = n - 1; col >= 0; col--) {
                if (M[row][col] == 1) {
                    diagonal[row + col]++;
                } else {
                    diagonal[row + col] = 0;
                }
                ans = Math.max(ans, diagonal[row + col]);
            }
        }
        return ans;
    }
}
