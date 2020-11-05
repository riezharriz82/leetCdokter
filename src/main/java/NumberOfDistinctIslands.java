import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * https://leetcode.com/problems/number-of-distinct-islands/ Premium
 * <p>
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 * <p>
 * Count the number of distinct islands. An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.
 * <p>
 * Example 1:
 * 11000
 * 11000
 * 00011
 * 00011
 * Given the above grid map, return 1.
 * <p>
 * Example 2:
 * 11011
 * 10000
 * 00001
 * 11011
 * Given the above grid map, return 3.
 */
public class NumberOfDistinctIslands {
    int[] x_offset = new int[]{0, 1, 0, -1};
    int[] y_offset = new int[]{1, 0, -1, 0};

    /**
     * Approach: DFS along with storing the path of current island.
     * While storing the path, store the relative positions instead of absolute positions i.e if the dfs starts from (4,5)
     * relative position of (4,5) is (0,0) and if next node in dfs is (4,6), it's relative position is (0,1)
     * <p>
     * Storing relative position helps in identifying similar paths
     */
    public int numDistinctIslands(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        HashSet<List<Pair<Integer, Integer>>> allPaths = new HashSet<>(); //set of all paths of island
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    ArrayList<Pair<Integer, Integer>> curPath = new ArrayList<>();
                    recur(visited, grid, i, j, curPath, i, j);
                    allPaths.add(curPath);
                }
            }
        }
        return allPaths.size(); //count of all unique paths
    }

    private void recur(boolean[][] visited, int[][] grid, int i, int j,
                       ArrayList<Pair<Integer, Integer>> curPath, int parent_i, int parent_j) {
        if (!visited[i][j]) {
            visited[i][j] = true;
            curPath.add(new Pair<>(i - parent_i, j - parent_j)); //store the relative index of current node in current path
            for (int k = 0; k < 4; k++) {
                int new_i = i + x_offset[k];
                int new_j = j + y_offset[k];
                if (new_i >= 0 && new_i < grid.length && new_j >= 0 && new_j < grid[0].length && grid[new_i][new_j] == 1) {
                    recur(visited, grid, new_i, new_j, curPath, parent_i, parent_j);
                }
            }
        }
    }
}
