import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/
 * <p>
 * Given a m * n grid, where each cell is either 0 (empty) or 1 (obstacle). In one step, you can move up, down, left or right from and to an empty cell.
 * <p>
 * Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m-1, n-1) given that you can eliminate at most k obstacles.
 * If it is not possible to find such walk return -1.
 * <p>
 * Input:
 * <pre>
 * [[0,0,0],
 *  [1,1,0],
 *  [0,0,0],
 *  [0,1,1],
 *  [0,0,0]],
 * </pre>
 * k = 1
 * Output: 6
 * Explanation:
 * The shortest path without eliminating any obstacle is 10.
 * The shortest path with one obstacle elimination at position (3,2) is 6. Such path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).
 * <p>
 * Constraints:
 * grid.length == m
 * grid[0].length == n
 * 1 <= m, n <= 40
 * 1 <= k <= m*n
 * grid[i][j] == 0 or 1
 * grid[0][0] == grid[m-1][n-1] == 0
 */
public class ShortestPathInGridWithObstacleElimination {
    int[] x_offset = new int[]{0, 1, 0, -1};
    int[] y_offset = new int[]{1, 0, -1, 0};

    /**
     * <pre>
     * Approach: Since the problem talks about finding shortest path in unweighted graph, BFS is the way to go
     * Initially I tried solving it with DFS and got TLE, because you can't memoize in DFS
     *
     * Try to solve this question as if it had no additional constraint (normal BFS) and augment the information that you would store per node
     * ie. if it had been normal BFS, you would have solved it by keeping a boolean[][] visited = new boolean[i][j] as the graph has cycles.
     * Now we have additional information per cell ie. no of obstacles eliminated so far, so keep that information
     * ie. new boolean[i][j][j]
     *
     * Why do you want to persist that additional information? Why not just track of shortest distance per cell irrespective of no of eliminations performed
     * because you might reach a cell with a longer distance but with lower eliminations performed. Lower no of eliminations will allow you to go further
     *               a
     *          50 /   \ 10
     *           b _20_ c
     *        5 /
     *        d
     * If you keep shortest distance only then you will not be able to reach d from a because you will mark the shortest distance from a to b as 30 and will discard
     * the direct path from a to b as it has a higher distance of 50 but since it has only one eliminations it actually allows you to go further ahead and reach d.
     *
     * While doing BFS, keep track of the obstacles eliminated so far. CurrentElimination <= MaxElimination
     * The first time we reach the end node irrespective of the no of eliminations performed, that should be the shortest path
     *
     * In my initial implementation, I stored the path length of reaching end node by eliminating 0,1,2..K nodes and returned the min of them {dp[i][j][k]}
     * which was redundant because of BFS
     *
     * Another gotcha is if I reach a node after performing 1 eliminations, don't mark the entire node as visited because we can reach that node in a shorter way
     * possibly by performing 0 eliminations hence keep the kth dimension too for tracking the visited set.
     * </pre>
     */
    public int shortestPathBFS(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.add(new Node(0, 0, 0, 0));
        boolean[][][] visited = new boolean[m][n][k + 1]; //visited set is required as the graph has cycles
        //3rd dimension is important, keep additional information associated with each node and  try to solve the question as if it had no constraint
        //if it had no constraint, you would have directly performed BFS
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node head = queue.remove();
                if (head.row == m - 1 && head.col == n - 1) { //target node reached, guaranteed to have reached in shortest distance because of BFS
                    return head.curLength;
                }
                for (int j = 0; j < 4; j++) {
                    int new_row = head.row + x_offset[j];
                    int new_col = head.col + y_offset[j];
                    if (new_row >= 0 && new_row < m && new_col >= 0 && new_col < n) {
                        //need to mark the cells as visited to avoid cycles, I repeat, the graph has cycles
                        if (grid[new_row][new_col] == 0 && !visited[new_row][new_col][head.currentEliminations]) { //free node reached
                            visited[new_row][new_col][head.currentEliminations] = true;
                            queue.add(new Node(new_row, new_col, head.currentEliminations, head.curLength + 1));
                        } else if (grid[new_row][new_col] == 1 && head.currentEliminations + 1 <= k &&
                                !visited[new_row][new_col][head.currentEliminations + 1]) { //blocked node reached, need to check, can be eliminated or not?
                            visited[new_row][new_col][head.currentEliminations + 1] = true;
                            queue.add(new Node(new_row, new_col, head.currentEliminations + 1, head.curLength + 1));
                        }
                    }
                }
            }
        }
        return -1; //target node can't be reached
    }

    /**
     * Approach: BFS with reduced time/space complexity.
     * Instead of maintaining a nested visited array with another dimension, maintain a separate array which tracks no of eliminations performed.
     * The benefit of this approach is lower time/space complexity as the nested dimension solution times out for bigger constraint problem like {@link CheapestFlightWithinKStop}
     * <p>
     * Visit an edge if it reduces the time to reach the node or reaches it with lesser eliminations.
     * Make sure to maintain a consistent state ie. update the cost even though it reaches a cell with higher cost but with lower eliminations
     */
    public int shortestPathBFSOptimized(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.add(new Node(0, 0, 0, 0));
        int[][] cost = new int[m][n];
        for (int[] rows : cost) {
            Arrays.fill(rows, Integer.MAX_VALUE);
        }
        cost[0][0] = 0;
        int[][] eliminations = new int[m][n];
        for (int[] rows : eliminations) {
            Arrays.fill(rows, Integer.MAX_VALUE);
        }
        eliminations[0][0] = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node head = queue.remove();
                if (head.row == m - 1 && head.col == n - 1) { //target node reached, guaranteed to have reached in shortest distance because of BFS
                    return head.curLength;
                }
                for (int j = 0; j < 4; j++) {
                    int new_row = head.row + x_offset[j];
                    int new_col = head.col + y_offset[j];
                    int new_cost = head.curLength + 1;
                    if (new_row >= 0 && new_row < m && new_col >= 0 && new_col < n) {
                        if (grid[new_row][new_col] == 0 && (cost[new_row][new_col] > new_cost || eliminations[new_row][new_col] > head.currentEliminations)) {
                            //free node reached with either lower cost or lower eliminations
                            cost[new_row][new_col] = new_cost;
                            eliminations[new_row][new_col] = head.currentEliminations;
                            queue.add(new Node(new_row, new_col, head.currentEliminations, new_cost));
                        } else if (grid[new_row][new_col] == 1 && head.currentEliminations + 1 <= k &&
                                (cost[new_row][new_col] > new_cost || eliminations[new_row][new_col] > head.currentEliminations + 1)) {
                            //blocked node reached with either lower cost or lower eliminations
                            cost[new_row][new_col] = new_cost;
                            eliminations[new_row][new_col] = head.currentEliminations + 1;
                            queue.add(new Node(new_row, new_col, head.currentEliminations + 1, new_cost));
                        }
                    }
                }
            }
        }
        return -1; //target node can't be reached
    }

    /**
     * <pre>
     * Approach: Greedy, use Djikstra algorithm to find the shortest path between source node and target node.
     * There are a couple of gotchas though:
     * 1. Instead of just associating the cost directly with cell, we need to carry additional information per state ie. no of eliminations performed so far
     * e.g associate cost with grid[i][j][k]
     *
     * 2. This is an unweighted graph as all edges have equal weight, so Djikstra is a bit of overkill here as it increases time complexity but still runs fast.
     * Time ~75 ms vs ~25 ms of BFS
     *
     * Lesson learnt: When provided additional constraints in graph traversal, keep that information directly associated with each node and try to solve the question
     * you would do normally without any constraint.
     * </pre>
     * {@link CheapestFlightWithinKStop} {@link MinimumCostToMakeAtLeastOneValidPathInAGrid}
     */
    public int shortestPathDjikstra(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.curLength, o2.curLength));
        int[][][] cost = new int[m][n][k + 1];
        for (int[][] rows : cost) {
            for (int[] row : rows) {
                Arrays.fill(row, Integer.MAX_VALUE);
            }
        }
        cost[0][0][0] = 0;
        pq.add(new Node(0, 0, 0, 0));
        while (!pq.isEmpty()) {
            Node head = pq.poll();
            if (head.row == m - 1 && head.col == n - 1) {
                return head.curLength;
            }
            for (int i = 0; i < 4; i++) {
                int new_r = head.row + x_offset[i];
                int new_c = head.col + y_offset[i];
                int new_cost = head.curLength + 1;
                if (new_r >= 0 && new_r < m && new_c >= 0 && new_c < n) {
                    if (grid[new_r][new_c] == 1 && head.currentEliminations + 1 <= k && cost[new_r][new_c][head.currentEliminations + 1] > new_cost) { //obstacle
                        cost[new_r][new_c][head.currentEliminations + 1] = new_cost;
                        pq.add(new Node(new_r, new_c, head.currentEliminations + 1, new_cost));
                    } else if (grid[new_r][new_c] == 0 && cost[new_r][new_c][head.currentEliminations] > new_cost) { //no obstacle
                        cost[new_r][new_c][head.currentEliminations] = new_cost;
                        pq.add(new Node(new_r, new_c, head.currentEliminations, new_cost));
                    }
                }
            }
        }
        return -1;
    }

    /**
     * Approach: Optimized Djikstra, instead of maintaining a nested array (3D) with another dimension for eliminations performed per cell,
     * maintain a separate 2D array for no of eliminations performed.
     * Do regular Djikstra and visit an edge only if it reduces current cost or visits the node with lesser eliminations.
     * Only gotcha is to maintain a consistent state per node ie. if you visit a node with greater cost but with less eliminations, update the cost to higher value
     * <p>
     * Took around ~16 ms, similar to unoptimized BFS solution but much faster than unoptimized Djikstra
     * <p>
     * {@link CheapestFlightWithinKStop} related optimized Djikstra problem
     */
    public int shortestPathDjikstraOptimized(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.curLength, o2.curLength));
        int[][] cost = new int[m][n];
        for (int[] rows : cost) {
            Arrays.fill(rows, Integer.MAX_VALUE);
        }
        cost[0][0] = 0;
        int[][] eliminations = new int[m][n];
        for (int[] rows : eliminations) {
            Arrays.fill(rows, Integer.MAX_VALUE);
        }
        eliminations[0][0] = 0;
        pq.add(new Node(0, 0, 0, 0));
        while (!pq.isEmpty()) {
            Node head = pq.poll();
            if (head.row == m - 1 && head.col == n - 1) {
                return head.curLength;
            }
            for (int i = 0; i < 4; i++) {
                int new_r = head.row + x_offset[i];
                int new_c = head.col + y_offset[i];
                int new_cost = head.curLength + 1;
                if (new_r >= 0 && new_r < m && new_c >= 0 && new_c < n) {
                    if (grid[new_r][new_c] == 1 && head.currentEliminations + 1 <= k && (cost[new_r][new_c] > new_cost || eliminations[new_r][new_c] > head.currentEliminations + 1)) {
                        //obstacle found, visit this edge if either the distance gets reduced or we visit this node with lesser eliminations
                        //maintain consistent state ie. update the cost even though it might be higher but with lesser eliminations
                        cost[new_r][new_c] = new_cost;
                        eliminations[new_r][new_c] = head.currentEliminations + 1;
                        pq.add(new Node(new_r, new_c, head.currentEliminations + 1, new_cost));
                    } else if (grid[new_r][new_c] == 0 && (cost[new_r][new_c] > new_cost || eliminations[new_r][new_c] > head.currentEliminations)) {
                        //no obstacle found, visit this edge if either the distance gets reduced or we visit this node with lesser eliminations
                        cost[new_r][new_c] = new_cost;
                        eliminations[new_r][new_c] = head.currentEliminations;
                        pq.add(new Node(new_r, new_c, head.currentEliminations, new_cost));
                    }
                }
            }
        }
        return -1;
    }

    private static class Node {
        int row;
        int col;
        int currentEliminations;
        int curLength;

        public Node(int row, int col, int currentEliminations, int curLength) {
            this.row = row;
            this.col = col;
            this.currentEliminations = currentEliminations;
            this.curLength = curLength;
        }
    }
}
