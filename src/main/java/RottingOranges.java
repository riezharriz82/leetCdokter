import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * https://leetcode.com/problems/rotting-oranges/
 * <p>
 * In a given grid, each cell can have one of three values:
 * <p>
 * the value 0 representing an empty cell;
 * the value 1 representing a fresh orange;
 * the value 2 representing a rotten orange.
 * Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.
 * <p>
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.
 * <p>
 * Input: [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 */
public class RottingOranges {
    int[] x_offset = new int[]{0, 1, 0, -1};
    int[] y_offset = new int[]{1, 0, -1, 0};

    public int orangesRotting(int[][] grid) {
        Queue<Pair<Integer, Integer>> queue = new ArrayDeque<>();
        int fresh = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new Pair<>(i, j));
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }
        if (fresh > 0) {
            int res = doBFS(queue, grid);
            //instead of doing this alternatively can count no of fresh oranges processed by BFS vs actual fresh
            //if they match then no fresh oranges is unreachable otherwise return -1
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 1) {
                        return -1; // this orange is unreachable from any rotten orange
                    }
                }
            }
            return res;
        }
        return 0;
    }

    private int doBFS(Queue<Pair<Integer, Integer>> queue, int[][] grid) {
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            for (int i = 0; i < size; i++) { //process all the entries present in the same level
                Pair<Integer, Integer> val = queue.poll();
                int row = val.getKey();
                int col = val.getValue();
                for (int j = 0; j < x_offset.length; j++) {
                    int new_row = row + x_offset[j];
                    int new_col = col + y_offset[j];
                    if (isValid(new_row, new_col, grid.length, grid[0].length) && grid[new_row][new_col] == 1) { //only consider adjacent fresh oranges
                        //this is important to do here otherwise same element can be added to the queue twice e.g. {2,2}, {1,1}
                        //Initially I marked it as rotten after popping from queue but by then duplicate element had already been added to the queue
                        //this is only relevant since we are starting to do bfs from multiple rotten oranges
                        grid[new_row][new_col] = 2; //so that it does not ret picked later, avoid using visited array
                        queue.add(new Pair<>(new_row, new_col));
                    }
                }
            }
        }
        return level - 1; //subtract the root level
    }

    private boolean isValid(int new_rol, int new_col, int max_row, int max_col) {
        return new_rol >= 0 && new_rol < max_row && new_col >= 0 && new_col < max_col;
    }
}
