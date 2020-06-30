/**
 * https://leetcode.com/problems/island-perimeter/
 * <p>
 * You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water.
 * <p>
 * Grid cells are connected horizontally/vertically (not diagonally).
 * The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).
 * <p>
 * The island doesn't have "lakes" (water inside that isn't connected to the water around the island).
 * One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.
 * <p>
 * Example:
 * <p>
 * Input:
 * <pre>
 * [[0,1,0,0],
 *  [1,1,1,0],
 *  [0,1,0,0],
 *  [1,1,0,0]]
 * </pre>
 * Output: 16
 */
public class IslandPerimeter {
    int res;
    int[] x_offset = new int[]{0, 1, 0, -1};
    int[] y_offset = new int[]{1, 0, -1, 0};

    public int islandPerimeterWithoutDFS(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    result += 4;
                    if (i > 0 && grid[i - 1][j] == 1) { //if the top block is also an island, remove the double counting
                        result -= 2;
                    }
                    if (j > 0 && grid[i][j - 1] == 1) { //if the left block is also an island, remove the double counting
                        result -= 2;
                    }
                }
            }
        }
        return result;
    }

    public int islandPerimeterUsingDFS(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        int[][] visited = new int[grid.length][grid[0].length];
        int row = 0, col = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    row = i;
                    col = j;
                    break;
                }
            }
        }
        dfs(grid, visited, row, col);
        return res;
    }

    private void dfs(int[][] grid, int[][] visited, int row, int col) {
        if (visited[row][col] == 0) {
            visited[row][col] = 1;
            int adjacentLand = 0;
            for (int i = 0; i < x_offset.length; i++) {
                int new_row = row + x_offset[i];
                int new_col = col + y_offset[i];
                if (new_row >= 0 && new_row < grid.length && new_col >= 0 && new_col < grid[0].length && grid[new_row][new_col] == 1) {
                    dfs(grid, visited, new_row, new_col);
                    adjacentLand++;
                }
            }
            res += (4 - adjacentLand); //each block can contribute max 4 to the perimeter, each adjacent land reduces it by 1
        }
    }
}
