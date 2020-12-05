import java.util.Arrays;

/**
 * https://leetcode.com/problems/out-of-boundary-paths/
 * <p>
 * There is an m by n grid with a ball. Given the start coordinate (i,j) of the ball, you can move the ball to adjacent cell
 * or cross the grid boundary in four directions (up, down, left, right). However, you can at most move N times.
 * Find out the number of paths to move the ball out of grid boundary. The answer may be very large, return it after mod 109 + 7.
 * <p>
 * Input: m = 2, n = 2, N = 2, i = 0, j = 0
 * Output: 6
 * <p>
 * Input: m = 1, n = 3, N = 3, i = 0, j = 1
 * Output: 12
 * <p>
 * Note:
 * Once you move the ball out of boundary, you cannot move it back.
 * The length and height of the grid is in range [1,50].
 * N is in range [0,50].
 */
public class OutOfBoundaryPaths {
    int[] x_offsets = new int[]{0, 1, 0, -1};
    int[] y_offsets = new int[]{1, 0, -1, 0};
    int MOD = 1_000_000_007;

    /**
     * Approach: DFS + Memoization
     * <p>
     * {@link KnightDialer} {@link KnightProbabilityInChessboard} related problems
     */
    public int findPaths(int m, int n, int steps, int i, int j) {
        int[][][] memoized = new int[m][n][steps + 1];
        for (int[][] ints : memoized) {
            for (int[] anInt : ints) {
                Arrays.fill(anInt, -1);
            }
        }
        return recur(m, n, steps, i, j, memoized);
    }

    private int recur(int m, int n, int steps, int row, int col, int[][][] memoized) {
        if (row < 0 || row >= m || col < 0 || col >= n) { //if reached outside the grid, include it as a valid step
            return 1;
        } else if (steps == 0) { //if no more steps remaining
            return 0;
        } else if (memoized[row][col][steps] != -1) {
            return memoized[row][col][steps];
        } else {
            int totalPaths = 0;
            for (int i = 0; i < 4; i++) {
                int new_row = row + x_offsets[i];
                int new_col = col + y_offsets[i];
                totalPaths = (totalPaths + recur(m, n, steps - 1, new_row, new_col, memoized)) % MOD; //keep track of total paths
            }
            return memoized[row][col][steps] = (totalPaths % MOD);
        }
    }
}
