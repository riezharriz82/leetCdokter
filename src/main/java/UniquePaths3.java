/**
 * https://leetcode.com/problems/unique-paths-iii/
 * <p>
 * On a 2-dimensional grid, there are 4 types of squares:
 * <p>
 * 1 represents the starting square.  There is exactly one starting square.
 * 2 represents the ending square.  There is exactly one ending square.
 * 0 represents empty squares we can walk over.
 * -1 represents obstacles that we cannot walk over.
 * Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.
 * <p>
 * Input: [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
 * Output: 2
 * Explanation: We have the following two paths:
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
 * 2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
 */
public class UniquePaths3 {
    int[] x_offset = new int[]{0, 1, 0, -1};
    int[] y_offset = new int[]{1, 0, -1, 0};

    int res = 0;

    /**
     * Approach: DFS with backtracking, once we reach the end node (2), check if we included all the zeroes.
     * If yes, increment the result.
     * If no, backtrack
     * If no result found, still backtrack
     */
    public int uniquePathsIII(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int zeros = 0, start_row = 0, start_col = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    zeros++;
                } else if (grid[i][j] == 1) {
                    start_row = i;
                    start_col = j;
                }
            }
        }
        DFS(grid, start_row, start_col, zeros);
        return res;
    }

    private void DFS(int[][] grid, int row, int col, int totalZeroes) {
        for (int i = 0; i < 4; i++) {
            int new_row = row + x_offset[i];
            int new_col = col + y_offset[i];
            if (new_row >= 0 && new_row < grid.length && new_col >= 0 && new_col < grid[0].length && grid[new_row][new_col] != -1) {
                if (grid[new_row][new_col] == 0) {
                    grid[new_row][new_col] = -1; //mark it as visited to avoid picking the same node again
                    DFS(grid, new_row, new_col, totalZeroes - 1);
                    grid[new_row][new_col] = 0; //reset
                } else if (grid[new_row][new_col] == 2 && totalZeroes == 0) {
                    res++;
                }
            }
        }
    }
}
