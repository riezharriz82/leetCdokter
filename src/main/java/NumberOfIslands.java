/**
 * https://leetcode.com/problems/number-of-islands/
 * <p>
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 * <p>
 * Input:
 * 11110
 * 11010
 * 11000
 * 00000
 * <p>
 * Output: 1
 */
public class NumberOfIslands {
    int[] x_offset = new int[]{0, 1, 0, -1};
    int[] y_offset = new int[]{1, 0, -1, 0};

    public int numIslands(char[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    private void dfs(char[][] grid, int row, int col) {
        grid[row][col] = '0';
        for (int i = 0; i < x_offset.length; i++) {
            int new_row = row + x_offset[i];
            int new_col = col + y_offset[i];
            if (isValid(new_row, new_col, grid.length, grid[0].length) && grid[new_row][new_col] == '1') {
                dfs(grid, new_row, new_col);
            }
        }
    }

    private boolean isValid(int new_rol, int new_col, int max_row, int max_col) {
        return new_rol >= 0 && new_rol < max_row && new_col >= 0 && new_col < max_col;
    }
}

