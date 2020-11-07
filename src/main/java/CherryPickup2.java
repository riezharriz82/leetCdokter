import java.util.Arrays;

/**
 * https://leetcode.com/problems/cherry-pickup-ii/
 * <p>
 * Given a rows x cols matrix grid representing a field of cherries. Each cell in grid represents the number of cherries that you can collect.
 * <p>
 * You have two robots that can collect cherries for you, Robot #1 is located at the top-left corner (0,0) ,
 * and Robot #2 is located at the top-right corner (0, cols-1) of the grid.
 * <p>
 * Return the maximum number of cherries collection using both robots  by following the rules below:
 * <p>
 * From a cell (i,j), robots can move to cell (i+1, j-1) , (i+1, j) or (i+1, j+1).
 * When any robot is passing through a cell, It picks it up all cherries, and the cell becomes an empty cell (0).
 * When both robots stay on the same cell, only one of them takes the cherries.
 * Both robots cannot move outside of the grid at any moment.
 * Both robots should reach the bottom row in the grid.
 * <p>
 * Input: grid = [[3,1,1],[2,5,1],[1,5,5],[2,1,1]]
 * Output: 24
 * Explanation: Path of robot #1 and #2 are described in color green and blue respectively.
 * Cherries taken by Robot #1, (3 + 2 + 5 + 2) = 12.
 * Cherries taken by Robot #2, (1 + 5 + 5 + 1) = 12.
 * Total of cherries: 12 + 12 = 24.
 */
public class CherryPickup2 {
    /**
     * Approach: Do not do the same mistake done in {@link CherryPickup}, move both the robots simultaneously
     * Take care of the double counting if both the robots are at the same cell
     */
    int[] x_offset = new int[]{1, 1, 1};
    int[] y_offset = new int[]{-1, 0, 1};

    public int cherryPickup(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][][] memoized = new int[m][n][n];
        for (int[][] ints : memoized) {
            for (int[] anInt : ints) {
                Arrays.fill(anInt, -1);
            }
        }
        return recur(grid, 0, 0, grid[0].length - 1, memoized);
    }

    //does not use row2 because both the robots can only be at the same cell at anytime
    private int recur(int[][] grid, int row1, int col1, int col2, int[][][] memoized) {
        int m = grid.length;
        int n = grid[0].length;
        if (memoized[row1][col1][col2] != -1) {
            return memoized[row1][col1][col2];
        }
        int cherriesPicked;
        if (col1 == col2) { //prevent double counting
            cherriesPicked = grid[row1][col1];
        } else {
            cherriesPicked = grid[row1][col1] + grid[row1][col2];
        }
        int remain = 0;
        for (int i = 0; i < 3; i++) {
            int new_row1 = row1 + x_offset[i];
            int new_col1 = col1 + y_offset[i];
            for (int j = 0; j < 3; j++) {
                int new_col2 = col2 + y_offset[j];
                if (new_row1 < m && new_col1 >= 0 && new_col1 < n && new_col2 >= 0 && new_col2 < n) {
                    remain = Math.max(remain, recur(grid, new_row1, new_col1, new_col2, memoized));
                }
            }
        }
        return memoized[row1][col1][col2] = cherriesPicked + remain;
    }
}
