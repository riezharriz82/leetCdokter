import java.util.Arrays;

/**
 * https://leetcode.com/problems/cherry-pickup/
 * <p>
 * In a N x N grid representing a field of cherries, each cell is one of three possible integers.
 * <p>
 * 0 means the cell is empty, so you can pass through;
 * 1 means the cell contains a cherry, that you can pick up and pass through;
 * -1 means the cell contains a thorn that blocks your way.
 * <p>
 * Your task is to collect maximum number of cherries possible by following the rules below:
 * <p>
 * Starting at the position (0, 0) and reaching (N-1, N-1) by moving right or down through valid path cells (cells with value 0 or 1);
 * After reaching (N-1, N-1), returning to (0, 0) by moving left or up through valid path cells;
 * When passing through a path cell containing a cherry, you pick it up and the cell becomes an empty cell (0);
 * If there is no valid path between (0, 0) and (N-1, N-1), then no cherries can be collected.
 * <p>
 * Input: grid =
 * [[0, 1, -1],
 * [1, 0, -1],
 * [1, 1,  1]]
 * Output: 5
 * Explanation:
 * The player started at (0, 0) and went down, down, right right to reach (2, 2).
 * 4 cherries were picked up during this single trip, and the matrix becomes [[0,1,-1],[0,0,-1],[0,0,0]].
 * Then, the player went left, up, up, left to return home, picking up one more cherry.
 * The total number of cherries picked up is 5, and this is the maximum possible.
 */
public class CherryPickup {
    /**
     * Approach: Doing two separate DP does not give optimal results in all the cases
     * <p>
     * 1 1 1 1 0 0 0
     * 0 0 0 1 0 0 1
     * 1 0 0 1 0 0 0
     * 0 0 0 1 1 1 1
     * Here first DP will pick 10 1's and next DP will pick one 1, total 11 1's
     * However optimal result is 1 0 1 0 0 1 1 1 1 1 -> 7 1's and in next turn 0 1 1 1 1 0 0 1 -> 5 1's, total 12 1's
     * <p>
     * DP's does not work well when optimal result is required by performing multiple passes over the input, need to use recursion for that
     * Because DP optimizes only for the first pass kinda gives local maximum result similar to the problems where greedy fails and DP works
     * <p>
     * Similar Tricky questions {@link BusRoutes} {@link SurroundedRegions} {@link NumberOfAtoms} {@link MinDistanceToTypeUsingTwoFingers}
     * {@link DungeonGame}
     */
    int[] x_offsets = new int[]{0, 1}; //right and down
    int[] y_offsets = new int[]{1, 0};

    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int[][][][] memoized = new int[n][n][n][n];
        for (int[][][] ints : memoized) {
            for (int[][] anInt : ints) {
                for (int[] ints1 : anInt) {
                    Arrays.fill(ints1, -1);
                }
            }
        }
        int res = recur(grid, 0, 0, 0, 0, memoized);
        return res == Integer.MIN_VALUE ? 0 : res;
    }

    /**
     * Goal is to make sure both the persons reaches the target position, because it will signify that we have found a valid path
     * If one of the person has reached a blocked cell (thorn), we have to return from that state because we can't reach our goal
     * <p>
     * If one of the person has reached end state, this means that the other person has also reached the end state
     * Because both person are moving at the same speed, so if they travel the same distance, they must reach it at the same time
     * <p>
     * An optimization to DP would be to reduce it to n^3 state DP because of a subtle math trick.
     * If we have taken t steps, r1 + c1 = t, and since person2 is moving at the same speed, r2 + c2 = t as well
     * so we can find c2 = r2 - t => c2 = r2 - (r1 + c1)
     * So this means, we don't have to explicitly pass c2 around and can generate it from r1, c1 and r2
     * Similar optimization can be seen in {@link InterleavingString}
     */
    private int recur(int[][] grid, int row1, int col1, int row2, int col2, int[][][][] memoized) {
        int cherriesPicked, n = grid.length;
        if (grid[row1][col1] == -1 || grid[row2][col2] == -1) { //one of the person has reached blocked cell
            return Integer.MIN_VALUE;
        }
        if (row1 == n - 1 && col1 == n - 1 && row2 == n - 1 && col2 == n - 1) {
            //target cell reached, checking for row2, col2 was redundant, because person1 and person2 move at the same speed
            return grid[row1][col1];
        }
        if (memoized[row1][col1][row2][col2] != -1) {
            return memoized[row1][col1][row2][col2];
        }
        if (row1 == row2 && col1 == col2) {
            cherriesPicked = grid[row1][col1]; //avoid double counting
        } else {
            cherriesPicked = grid[row1][col1] + grid[row2][col2];
        }
        int remain = Integer.MIN_VALUE;
        for (int i = 0; i < 2; i++) {
            int new_row1 = row1 + x_offsets[i];
            int new_col1 = col1 + y_offsets[i];
            for (int j = 0; j < 2; j++) {
                int new_row2 = row2 + x_offsets[j];
                int new_col2 = col2 + y_offsets[j];
                if (new_row1 < n && new_col1 < n && new_row2 < n && new_col2 < n) {
                    remain = Math.max(remain, recur(grid, new_row1, new_col1, new_row2, new_col2, memoized));
                }
            }
        }
        if (remain == Integer.MIN_VALUE) {
            return memoized[row1][col1][row2][col2] = Integer.MIN_VALUE;
        } else {
            return memoized[row1][col1][row2][col2] = cherriesPicked + remain;
        }
    }
}
