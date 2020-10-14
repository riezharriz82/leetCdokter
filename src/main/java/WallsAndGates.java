import javafx.util.Pair;

import java.util.ArrayDeque;

/**
 * https://leetcode.com/problems/walls-and-gates/ Premium
 * <p>
 * You are given a m x n 2D grid initialized with these three possible values.
 * <p>
 * -1 - A wall or an obstacle.
 * 0 - A gate.
 * INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
 * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
 *
 * <pre>
 * Given the 2D grid:
 *
 * INF  -1  0  INF
 * INF INF INF  -1
 * INF  -1 INF  -1
 *   0  -1 INF INF
 * After running your function, the 2D grid should be:
 *
 *   3  -1   0   1
 *   2   2   1  -1
 *   1  -1   2  -1
 *   0  -1   3   4
 * </pre>
 */
public class WallsAndGates {
    int[] x_offset = new int[]{0, 1, 0, -1};
    int[] y_offset = new int[]{1, 0, -1, 0};

    /**
     * Approach: Since the problem asked to identify the shortest distance between a gate and a empty room, BFS comes into picture
     * Push all the gates into the queue and update the distance of the adjacent empty rooms. No pruning required. Because once a node is marked
     * it won't be picked up later.
     * Similar to previously solved {@link RottingOranges}
     */
    public void wallsAndGates(int[][] rooms) {
        if (rooms.length == 0) {
            return;
        }
        ArrayDeque<Pair<Integer, Integer>> queue = new ArrayDeque<>();
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    queue.add(new Pair<>(i, j));
                }
            }
        }
        int distance = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            distance++;
            for (int i = 0; i < size; i++) {
                Pair<Integer, Integer> head = queue.remove();
                int x = head.getKey();
                int y = head.getValue();
                for (int j = 0; j < 4; j++) {
                    int new_x = x + x_offset[j];
                    int new_y = y + y_offset[j];
                    //if valid cell and is an empty room, update the distance
                    if (new_x >= 0 && new_x < rooms.length && new_y >= 0 && new_y < rooms[0].length && rooms[new_x][new_y] == Integer.MIN_VALUE) {
                        rooms[new_x][new_y] = distance;
                        queue.add(new Pair<>(new_x, new_y));
                    }
                }
            }
        }
    }
}
