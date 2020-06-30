import java.util.HashSet;
import java.util.Set;

/**
 * https://me-ramesh.blogspot.com/2019/07/number-of-distinct-islands.html
 * /**
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
    public static int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length < 1 || grid[0].length < 1) return 0;
        int m = grid.length, n = grid[0].length;
        Set<String> res = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Set<String> set = new HashSet<>();
                if (grid[i][j] == 1) {
                    dfs(grid, i, j, i, j, set);
                    res.add(set.toString()); // set will ensure that we don't add duplicate shapes
                    //we need not worry about the order of processing as we are sweeping them in one order left to right/ top to bottom
                }
            }
        }
        return res.size();
    }

    public static void dfs(int[][] grid, int i, int j, int baseX, int baseY, Set<String> set) {
        int m = grid.length, n = grid[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 0) return;
        set.add((i - baseX) + "_" + (j - baseY)); //add the hash of local coordinate
        grid[i][j] = 0; //act as visitedJ
        dfs(grid, i + 1, j, baseX, baseY, set);
        dfs(grid, i - 1, j, baseX, baseY, set);
        dfs(grid, i, j - 1, baseX, baseY, set);
        dfs(grid, i, j + 1, baseX, baseY, set);
    }
}
