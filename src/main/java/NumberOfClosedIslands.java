/**
 * https://leetcode.com/problems/number-of-closed-islands/
 * <p>
 * Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of 0s
 * and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.
 * <p>
 * Return the number of closed islands.
 * <pre>
 * Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
 * Output: 2
 * Explanation:
 * Islands in gray are closed because they are completely surrounded by water (group of 1s).
 *
 * Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
 * Output: 1
 *
 * Input: grid = [[1,1,1,1,1,1,1],
 *                [1,0,0,0,0,0,1],
 *                [1,0,1,1,1,0,1],
 *                [1,0,1,0,1,0,1],
 *                [1,0,1,1,1,0,1],
 *                [1,0,0,0,0,0,1],
 *                [1,1,1,1,1,1,1]]
 * Output: 2
 * </pre>
 */
public class NumberOfClosedIslands {
    int[] x_offset = new int[]{0, 1, 0, -1};
    int[] y_offset = new int[]{1, 0, -1, 0};

    /**
     * Approach: First mark the nodes that are not closed ie. start from border water cells and mark all the connected water cells
     * The water cells left unmarked are closed as they are surrounded by 1's, hence can't be reached from outside water cells
     * <p>
     * {@link SurroundedRegions} {@link PacificAtlanticWaterFlow}
     */
    public int closedIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        //mark all the cells that are not closed
        for (int col = 0; col < n; col++) {
            DFS(0, col, grid);
            DFS(m - 1, col, grid);
        }
        for (int row = 0; row < m; row++) {
            DFS(row, 0, grid);
            DFS(row, n - 1, grid);
        }
        int isolatedWaterCells = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) { //closed cell found, mark all the cells connected to it as they are part of a single component
                    isolatedWaterCells++;
                    DFS(i, j, grid);
                }
            }
        }
        return isolatedWaterCells;
    }

    private void DFS(int row, int col, int[][] grid) {
        if (grid[row][col] == 0) {
            grid[row][col] = 2;
            for (int i = 0; i < 4; i++) {
                int new_row = row + x_offset[i];
                int new_col = col + y_offset[i];
                if (new_row >= 0 && new_row < grid.length && new_col >= 0 && new_col < grid[0].length && grid[new_row][new_col] == 0) {
                    DFS(new_row, new_col, grid);
                }
            }
        }
    }
}
