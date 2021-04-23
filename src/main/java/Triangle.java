import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/triangle/
 * <pre>
 * Given a triangle array, return the minimum path sum from top to bottom.
 *
 * For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the current row,
 * you may move to either index i or index i + 1 on the next row.
 *
 * Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * Output: 11
 * Explanation: The triangle looks like:
 *    2
 *   3 4
 *  6 5 7
 * 4 1 8 3
 * The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
 *
 * Input: triangle = [[-10]]
 * Output: -10
 *
 * Constraints:
 * 1 <= triangle.length <= 200
 * triangle[0].length == 1
 * triangle[i].length == triangle[i - 1].length + 1
 * -10^4 <= triangle[i][j] <= 10^4
 *
 * Follow up: Could you do this using only O(n) extra space, where n is the total number of rows in the triangle?
 * </pre>
 */
public class Triangle {
    /**
     * Approach: DFS with Memoization. There are no cycles in the graph, so it's much simpler and much faster.
     * During my initial implementation, I didn't think of this solution and implemented the greedy and bfs with pruning solution which works when there are cycles in the graph.
     * <p>
     * Time: 1 ms
     * <p>
     * {@link PathWithMaximumGold} {@link DungeonGame} {@link PathWithMaximumMinimumValue}
     */
    public int minimumTotalDFS(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] cost = new int[n][n];
        for (int[] row : cost) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        return recur(triangle, 0, 0, cost);
    }

    private int recur(List<List<Integer>> triangle, int row, int col, int[][] cost) {
        if (row == triangle.size() - 1) {
            return triangle.get(row).get(col);
        } else if (cost[row][col] != Integer.MAX_VALUE) {
            return cost[row][col];
        } else {
            int minCost = triangle.get(row).get(col) + Math.min(recur(triangle, row + 1, col, cost), recur(triangle, row + 1, col + 1, cost));
            return cost[row][col] = minCost;
        }
    }

    /**
     * Approach: Greedy using Djikstra. Visualize the problem as graph, reducing the problem to find the shortest path from starting node to any cell in last row.
     * Djikstra is non optimal in this problem as the graph does not have cycles. So we can simply use DFS with memoization which would have a better time complexity.
     * If the problem statement allowed movement in 4 directions, which would have introduced cycles, then this solution would have been the fastest :)
     * <p>
     * Time: ~200ms
     */
    public int minimumTotalDjikstra(List<List<Integer>> triangle) {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
        int n = triangle.size();
        int[][] cost = new int[n][n];
        for (int[] row : cost) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        cost[0][0] = triangle.get(0).get(0);
        pq.add(new Node(0, 0, cost[0][0]));
        int minCost = Integer.MAX_VALUE;
        while (!pq.isEmpty()) {
            Node head = pq.poll();
            if (head.row == n - 1) { //goal is to reach any cell of last row with smallest cost, so the first path that leads to last row
                //may not be optimal, hence we have to pursue all the paths that lead to last row.
                minCost = Math.min(minCost, head.cost);
                continue;
            }
            int curDistance = head.cost;
            if (curDistance + triangle.get(head.row + 1).get(head.index) < cost[head.row + 1][head.index]) {
                cost[head.row + 1][head.index] = curDistance + triangle.get(head.row + 1).get(head.index);
                pq.add(new Node(head.row + 1, head.index, cost[head.row + 1][head.index]));
            }
            if (curDistance + triangle.get(head.row + 1).get(head.index + 1) < cost[head.row + 1][head.index + 1]) {
                cost[head.row + 1][head.index + 1] = curDistance + triangle.get(head.row + 1).get(head.index + 1);
                pq.add(new Node(head.row + 1, head.index + 1, cost[head.row + 1][head.index + 1]));
            }
        }
        return minCost;
    }

    /**
     * Approach: BFS with pruning. Runtime is worse than djikstra because the pruning isn't good. Djikstra always takes better paths first,
     * resulting in better pruning.
     * <p>
     * Time: ~650 ms
     */
    public int minimumTotalBFS(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] cost = new int[n][n];
        for (int[] row : cost) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        cost[0][0] = triangle.get(0).get(0);
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.add(new Node(0, 0, cost[0][0]));
        int minCost = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Node head = queue.poll();
            if (head.row == n - 1) {
                minCost = Math.min(minCost, head.cost);
            } else {
                int curDistance = head.cost;
                if (curDistance + triangle.get(head.row + 1).get(head.index) < cost[head.row + 1][head.index]) { //prune
                    cost[head.row + 1][head.index] = curDistance + triangle.get(head.row + 1).get(head.index);
                    queue.add(new Node(head.row + 1, head.index, cost[head.row + 1][head.index]));
                }
                if (curDistance + triangle.get(head.row + 1).get(head.index + 1) < cost[head.row + 1][head.index + 1]) { //prune
                    cost[head.row + 1][head.index + 1] = curDistance + triangle.get(head.row + 1).get(head.index + 1);
                    queue.add(new Node(head.row + 1, head.index + 1, cost[head.row + 1][head.index + 1]));
                }
            }
        }
        return minCost;
    }

    private static class Node {
        int row;
        int index;
        int cost;

        public Node(int row, int index, int cost) {
            this.row = row;
            this.index = index;
            this.cost = cost;
        }
    }
}
