import java.util.Arrays;

/**
 * <pre>
 * https://leetcode.com/problems/minimum-falling-path-sum/
 *
 * Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.
 *
 * A falling path starts at any element in the first row and chooses the element in the next row that is either directly below or diagonally left/right.
 * Specifically, the next element from position (row, col) will be (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).
 *
 * Input: matrix = [[2,1,3],[6,5,4],[7,8,9]]
 * Output: 13
 * Explanation: There are two falling paths with a minimum sum underlined below:
 * [[2,1,3],      [[2,1,3],
 *  [6,5,4],       [6,5,4],
 *  [7,8,9]]       [7,8,9]]
 *
 * Input: matrix = [[-19,57],[-40,-5]]
 * Output: -59
 * Explanation: The falling path with a minimum sum is underlined below:
 * [[-19,57],
 *  [-40,-5]]
 *
 * Input: matrix = [[-48]]
 * Output: -48
 *
 * Constraints:
 * n == matrix.length
 * n == matrix[i].length
 * 1 <= n <= 100
 * -100 <= matrix[i][j] <= 100
 * </pre>
 */
public class MinimumFallingPathSum {
    /**
     * Approach: Recursion with Memoization, the specified movement generates a directed acyclic graph, so we can apply recursion + memoization
     * If it had cycles, need to use other techniques like greedy
     * <p>
     * {@link Triangle}
     */
    public int minFallingPathSum(int[][] matrix) {
        int minSum = Integer.MAX_VALUE;
        int rows = matrix.length, cols = matrix[0].length;
        int[][] dp = new int[rows][cols];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        for (int col = 0; col < matrix[0].length; col++) {
            int cost = recur(matrix, 0, col, dp); //start recursion from every column in first row
            minSum = Math.min(minSum, cost);
        }
        return minSum;
    }

    private int recur(int[][] matrix, int row, int col, int[][] dp) {
        if (col < 0 || col >= matrix[0].length) { //out of bounds
            return Integer.MAX_VALUE;
        } else if (row == matrix.length - 1) { //reached last row
            return matrix[row][col];
        } else if (dp[row][col] != Integer.MAX_VALUE) {
            return dp[row][col];
        } else {
            int minCost = Integer.MAX_VALUE;
            for (int i = -1; i <= 1; i++) { //iterate next state in next row
                minCost = Math.min(minCost, recur(matrix, row + 1, col + i, dp));
            }
            return dp[row][col] = matrix[row][col] + minCost;
        }
    }
}
