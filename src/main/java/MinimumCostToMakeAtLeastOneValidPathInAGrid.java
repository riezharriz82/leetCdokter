import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/
 * <p>
 * Given a m x n grid. Each cell of the grid has a sign pointing to the next cell you should visit if you are currently in this cell. The sign of grid[i][j] can be:
 * 1 which means go to the cell to the right. (i.e go from grid[i][j] to grid[i][j + 1])
 * 2 which means go to the cell to the left. (i.e go from grid[i][j] to grid[i][j - 1])
 * 3 which means go to the lower cell. (i.e go from grid[i][j] to grid[i + 1][j])
 * 4 which means go to the upper cell. (i.e go from grid[i][j] to grid[i - 1][j])
 * Notice that there could be some invalid signs on the cells of the grid which points outside the grid.
 * <p>
 * You will initially start at the upper left cell (0,0). A valid path in the grid is a path which starts from the upper left cell (0,0)
 * and ends at the bottom-right cell (m - 1, n - 1) following the signs on the grid. The valid path doesn't have to be the shortest.
 * <p>
 * You can modify the sign on a cell with cost = 1. You can modify the sign on a cell one time only.
 * <p>
 * Return the minimum cost to make the grid have at least one valid path.
 * <p>
 * Input: grid = [[1,1,1,1],[2,2,2,2],[1,1,1,1],[2,2,2,2]]
 * Output: 3
 * Explanation: You will start at point (0, 0).
 * The path to (3, 3) is as follows. (0, 0) --> (0, 1) --> (0, 2) --> (0, 3) change the arrow to down with cost = 1 --> (1, 3) --> (1, 2) --> (1, 1) --> (1, 0) change the arrow to down with cost = 1 --> (2, 0) --> (2, 1) --> (2, 2) --> (2, 3) change the arrow to down with cost = 1 --> (3, 3)
 * The total cost = 3.
 * <p>
 * Input: grid = [[1,1,3],[3,2,2],[1,1,4]]
 * Output: 0
 * Explanation: You can follow the path from (0, 0) to (2, 2).
 * <p>
 * Input: grid = [[1,2],[4,3]]
 * Output: 1
 * <p>
 * Input: grid = [[2,2,2],[2,2,2]]
 * Output: 3
 * <p>
 * Input: grid = [[4]]
 * Output: 0
 * <p>
 * Constraints:
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 100
 */
public class MinimumCostToMakeAtLeastOneValidPathInAGrid {
    //order of offsets is very specific, it's arranged in order of the direction to take depending upon grid value
    //i.e. if cell value is 1, you should go right, so 0th index of offset would move to right
    //similarly if cell value is 2, you should go left, so 1st index of offset would move to left
    private final int[] x_offsets = new int[]{0, 0, 1, -1};
    private final int[] y_offsets = new int[]{1, -1, 0, 0};

    /**
     * Approach: 0-1 BFS, as the graph has edges with weight 0 and 1, we can apply 0-1 BFS instead of Djikstra to find shortest path
     * Time Complexity is O(mn) instead of Djikstra O(ElogV) ie. O(4mn * log(mn))
     * <p>
     * Reference: https://cp-algorithms.com/graph/01_bfs.html
     */
    public int minCost01BFS(int[][] grid) {
        ArrayDeque<Pair<Integer, Integer>> queue = new ArrayDeque<>();
        int m = grid.length;
        int n = grid[0].length;
        queue.addLast(new Pair<>(0, 0));
        int[][] cost = new int[m][n];
        for (int[] rows : cost) {
            Arrays.fill(rows, Integer.MAX_VALUE);
        }
        cost[0][0] = 0;
        while (!queue.isEmpty()) {
            Pair<Integer, Integer> head = queue.removeFirst();
            if (head.getKey() == m - 1 && head.getValue() == n - 1) { //target cell reached
                return cost[head.getKey()][head.getValue()];
            }
            for (int i = 0; i < 4; i++) {
                int new_r = head.getKey() + x_offsets[i];
                int new_c = head.getValue() + y_offsets[i];
                if (new_r >= 0 && new_r < m && new_c >= 0 && new_c < n) {
                    int additionalCost = (grid[head.getKey()][head.getValue()] - 1) == i ? 0 : 1; //if the new cell is not in the allowed direction, we would incur additional cost
                    if (cost[new_r][new_c] > additionalCost + cost[head.getKey()][head.getValue()]) {
                        cost[new_r][new_c] = additionalCost + cost[head.getKey()][head.getValue()];
                        if (additionalCost == 1) {
                            queue.addLast(new Pair<>(new_r, new_c));
                        } else {
                            queue.addFirst(new Pair<>(new_r, new_c));
                        }
                    }
                }
            }
        }
        return -1; //not possible
    }

    /**
     * Approach: Greedy, use Djikstra algorithm. Imagine the matrix as a complete graph with cells that are not adjacent connected with the parent cell with cost 1.
     * So when we reach the last cell, the total cost would be the no of cells that needs to be flipped in order to reach the last cell.
     * <p>
     * Was not able to solve this on my own. This imagining of hidden edges similar to {@link OptimizeWaterDistributionInAVillage} is very tricky.
     * <p>
     * {@link ShortestPathInGridWithObstacleElimination} {@link PathWithMaximumMinimumValue} {@link SwimInRisingWater}
     */
    public int minCostDjikstra(int[][] grid) {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
        int m = grid.length;
        int n = grid[0].length;
        int[][] cost = new int[m][n]; //standard djikstra stuff required to not re-visit cell
        for (int[] row : cost) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        pq.add(new Node(0, 0, 0));
        cost[0][0] = 0;
        while (!pq.isEmpty()) {
            Node head = pq.poll();
            if (head.r == m - 1 && head.c == n - 1) {
                return head.cost;
            }
            for (int i = 0; i < 4; i++) {
                int new_r = head.r + x_offsets[i];
                int new_c = head.c + y_offsets[i];
                if (new_r >= 0 && new_r < m && new_c >= 0 && new_c < n) {
                    int additionalCost = (grid[head.r][head.c] - 1) == i ? 0 : 1; //if the new cell is not in the allowed direction, we would incur additional cost
                    if (cost[new_r][new_c] > additionalCost + head.cost) {
                        cost[new_r][new_c] = additionalCost + head.cost;
                        pq.add(new Node(new_r, new_c, cost[new_r][new_c]));
                    }
                }
            }
        }
        return -1; //not possible
    }

    private static class Node {
        int r, c;
        int cost;

        public Node(int r, int c, int cost) {
            this.r = r;
            this.c = c;
            this.cost = cost;
        }
    }
}
