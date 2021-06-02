import javafx.util.Pair;

import java.util.ArrayDeque;

/**
 * <pre>
 * https://leetcode.com/problems/shortest-path-to-get-food/
 *
 * You are starving and you want to eat food as quickly as possible. You want to find the shortest path to arrive at any food cell.
 *
 * You are given an m x n character matrix, grid, of these different types of cells:
 *
 * '*' is your location. There is exactly one '*' cell.
 * '#' is a food cell. There may be multiple food cells.
 * 'O' is free space, and you can travel through these cells.
 * 'X' is an obstacle, and you cannot travel through these cells.
 * You can travel to any adjacent cell north, east, south, or west of your current location if there is not an obstacle.
 *
 * Return the length of the shortest path for you to reach any food cell. If there is no path for you to reach food, return -1.
 *
 * Input: grid = [["X","X","X","X","X","X"],["X","*","O","O","O","X"],["X","O","O","#","O","X"],["X","X","X","X","X","X"]]
 * Output: 3
 * Explanation: It takes 3 steps to reach the food.
 *
 * Input: grid = [["X","X","X","X","X"],["X","*","X","O","X"],["X","O","X","#","X"],["X","X","X","X","X"]]
 * Output: -1
 * Explanation: It is not possible to reach the food.
 *
 * Input: grid = [["X","X","X","X","X","X","X","X"],["X","*","O","X","O","#","O","X"],["X","O","O","X","O","O","X","X"],["X","O","O","O","O","#","O","X"],["X","X","X","X","X","X","X","X"]]
 * Output: 6
 * Explanation: There can be multiple food cells. It only takes 6 steps to reach the bottom food.
 *
 * Input: grid = [["O","*"],["#","O"]]
 * Output: 2
 *
 * Input: grid = [["X","*"],["#","X"]]
 * Output: -1
 *
 * Constraints:
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * grid[row][col] is '*', 'X', 'O', or '#'.
 * The grid contains exactly one '*'.
 * </pre>
 */
public class ShortestPathToGetFood {
    private final int[] x_offsets = new int[]{-1, 0, 1, 0};
    private final int[] y_offsets = new int[]{0, 1, 0, -1};

    /**
     * Approach: BFS, Since graph has cycles due to traversal being allowed in 4 directions, DFS + Memoization can't be applied.
     * Since the graph is unweighted, BFS can be applied instead of Djikstra algorithm.
     * <p>
     * {@link RottingOranges} {@link CheapestFlightWithinKStop} {@link Triangle}
     */
    public int getFood(char[][] grid) {
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == '*') { //apply BFS from the starting point
                    return BFS(grid, r, c);
                }
            }
        }
        return -1;
    }

    private int BFS(char[][] grid, int r, int c) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        ArrayDeque<Pair<Integer, Integer>> queue = new ArrayDeque<>();
        visited[r][c] = true;
        queue.add(new Pair<>(r, c));
        int distance = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            distance++;
            for (int i = 0; i < size; i++) {
                Pair<Integer, Integer> head = queue.remove();
                char val = grid[head.getKey()][head.getValue()];
                if (val == '#') {
                    return distance - 1;
                }
                for (int j = 0; j < 4; j++) {
                    int new_r = head.getKey() + x_offsets[j];
                    int new_c = head.getValue() + y_offsets[j];
                    if (new_r >= 0 && new_r < m && new_c >= 0 && new_c < n && grid[new_r][new_c] != 'X' && !visited[new_r][new_c]) {
                        //if the new cell is not yet visited and not blocked, mark the new cell as visited
                        visited[new_r][new_c] = true;
                        queue.add(new Pair<>(new_r, new_c));
                    }
                }
            }
        }
        return -1;
    }
}
