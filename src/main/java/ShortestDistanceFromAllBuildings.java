import javafx.util.Pair;

import java.util.ArrayDeque;

/**
 * https://leetcode.com/problems/shortest-distance-from-all-buildings/
 * <p>
 * You want to build a house on an empty land which reaches all buildings in the shortest amount of distance.
 * You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
 * <p>
 * Each 0 marks an empty land which you can pass by freely.
 * Each 1 marks a building which you cannot pass through.
 * Each 2 marks an obstacle which you cannot pass through.
 * <pre>
 * Input: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
 *
 * 1 - 0 - 2 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 * </pre>
 * Output: 7
 * <p>
 * Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2),
 * the point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.
 * <p>
 * Note:
 * There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
 */
public class ShortestDistanceFromAllBuildings {
    int[] x_offsets = new int[]{0, 1, 0, -1};
    int[] y_offsets = new int[]{1, 0, -1, 0};

    /**
     * Approach: Didn't think that this would be faster, was thinking the multi bfs would be faster.
     * Check my submission history to get the code for multi bfs, but it's slower
     * <p>
     * Do a BFS from each building (always do BFS from the nodes that terminate, rather than inside nodes) and increment the
     * shortest distance to current land. Result would be the sum of the distances required to reach a building only if
     * it's reachable from all the buildings.
     * <p>
     * An optimization to return early would be to find whether all buildings are reachable from a building. If no, return -1
     * <p>
     * {@link AsFarFromLandAsPossible} {@link ShortestBridge} {@link SurroundedRegions}
     */
    public int shortestDistance(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] distances = new int[m][n]; //keep track of total distances of current cell from all the reachable buildings
        int[][] buildingsReached = new int[m][n]; //keep track of the no of buildings reachable from current cell
        int totalBuildings = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) { //increment the shortest distance of land from current building
                    BFS(i, j, distances, buildingsReached, grid, m, n);
                    totalBuildings++;
                }
            }
        }
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (buildingsReached[i][j] == totalBuildings) { //if current cell is reachable from all the buildings, update the minDistance
                    minDistance = Math.min(distances[i][j], minDistance);
                }
            }
        }
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }

    private void BFS(int i, int j, int[][] distances, int[][] buildingsReached, int[][] grid, int m, int n) {
        ArrayDeque<Pair<Integer, Integer>> queue = new ArrayDeque<>();
        queue.add(new Pair<>(i, j));
        boolean[][] visited = new boolean[m][n];
        visited[i][j] = true;
        int distance = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            distance++;
            for (int x = 0; x < size; x++) {
                Pair<Integer, Integer> head = queue.remove();
                for (int k = 0; k < 4; k++) {
                    int new_i = head.getKey() + x_offsets[k];
                    int new_j = head.getValue() + y_offsets[k];
                    //if new location is not visited and is an empty land, visit it
                    if (new_i >= 0 && new_i < m && new_j >= 0 && new_j < n && !visited[new_i][new_j] && grid[new_i][new_j] == 0) {
                        visited[new_i][new_j] = true;
                        buildingsReached[new_i][new_j]++; //increment the buildings reachable
                        distances[new_i][new_j] += distance; //increment the distance
                        queue.add(new Pair<>(new_i, new_j));
                    }
                }
            }
        }
    }
}
