/**
 * https://leetcode.com/problems/path-with-maximum-gold/
 * <p>
 * In a gold mine grid of size m * n, each cell in this mine has an integer representing the amount of gold in that cell, 0 if it is empty.
 * <p>
 * Return the maximum amount of gold you can collect under the conditions:
 * <p>
 * Every time you are located in a cell you will collect all the gold in that cell.
 * From your position you can walk one step to the left, right, up or down.
 * You can't visit the same cell more than once.
 * Never visit a cell with 0 gold.
 * You can start and stop collecting gold from any position in the grid that has some gold.
 * <pre>
 * Input: grid = [[0,6,0],[5,8,7],[0,9,0]]
 * Output: 24
 * Explanation:
 * [[0,6,0],
 *  [5,8,7],
 *  [0,9,0]]
 * Path to get the maximum gold, 9 -> 8 -> 7.
 *
 * Input: grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
 * Output: 28
 * Explanation:
 * [[1,0,7],
 *  [2,0,6],
 *  [3,4,5],
 *  [0,3,0],
 *  [9,0,20]]
 * Path to get the maximum gold, 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7.
 * </pre>
 * Constraints:
 * 1 <= grid.length, grid[i].length <= 15
 * 0 <= grid[i][j] <= 100
 * There are at most 25 cells containing gold.
 */
public class PathWithMaximumGold {
    int[] x_offsets = new int[]{0, 1, 0, -1};
    int[] y_offsets = new int[]{1, 0, -1, 0};

    /**
     * Approach: Use backtracking as Greedy/Djikstra won't work here
     * A hint that greedy won't work here would be that problem statement mentions that very low no of cells (25) contains gold
     * Need to use backtracking to visit all the paths and keep track of the path with max gold
     * DP also doesn't work here because DP can't work if there is cycle in the graph ! Remember that DP needs DAG !
     * Because in order to memoize you need the current direction as well, so basically you need to reach each cell in 4 directions
     * which is similar to backtracking.
     * <p>
     * Anyways Djikstra can't give the path with the longest total distance, so that should be the first clue to not use djikstra
     * <p>
     * {@link PathWithMaximumMinimumValue} {@link PathWithMinimumEffort} {@link SwimInRisingWater} {@link PathWithMaxProbability} related problems
     */
    public int getMaximumGold(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int maxGoldCollected = 0;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) { //do a DFS from each cell with gold present and keep track of the max gold collected
                    visited[i][j] = true;
                    maxGoldCollected = Math.max(maxGoldCollected, DFS(i, j, visited, grid));
                    visited[i][j] = false;
                }
            }
        }
        return maxGoldCollected;
    }

    private int DFS(int row, int col, boolean[][] visited, int[][] grid) {
        int goldCollected = grid[row][col];
        int maxGoldCollectedFromSingleNeighbour = 0;
        for (int i = 0; i < 4; i++) {
            int new_row = row + x_offsets[i];
            int new_col = col + y_offsets[i];
            if (new_row >= 0 && new_row < grid.length && new_col >= 0 && new_col < grid[0].length
                    && !visited[new_row][new_col] && grid[new_row][new_col] != 0) {
                visited[new_row][new_col] = true;
                maxGoldCollectedFromSingleNeighbour = Math.max(maxGoldCollectedFromSingleNeighbour, DFS(new_row, new_col, visited, grid));
                visited[new_row][new_col] = false; //backtrack
            }
        }
        return goldCollected + maxGoldCollectedFromSingleNeighbour;
    }
}
