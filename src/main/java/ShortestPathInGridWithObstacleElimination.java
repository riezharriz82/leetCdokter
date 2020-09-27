import java.util.ArrayDeque;

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
 */
public class ShortestPathInGridWithObstacleElimination {
    int[] x_offset = new int[]{0, 1, 0, -1};
    int[] y_offset = new int[]{1, 0, -1, 0};

    /**
     * Approach: Since the problem talks about finding shortest path, BFS is the way to go
     * Initially I tried solving it with DFS and got TLE.
     * <p>
     * While doing BFS, keep track of the obstacles eliminated so far. CurrentElimination <= MaxElimination
     * The first time we reach the end node irrespective of the no of eliminations performed, that should be the shortest path
     * <p>
     * In my initial implementation, I stored the path length of reaching end node by eliminating 0,1,2..K nodes and returned the min of them {dp[i][j][k]}
     * which was redundant because of BFS
     * <p>
     * Another gotcha is if I reach a node after performing 1 eliminations, don't mark the entire node as visited because we can reach that node in a shorter way
     * possibly by performing 0 eliminations hence keep the kth dimension too for tracking the visited set.
     */
    public int shortestPath(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][][] visited = new boolean[m][n][k + 1]; //3rd dimension is important, got WA in my initial implementation because I was careless
        return BFS(visited, grid, k);
    }

    private int BFS(boolean[][][] visited, int[][] grid, int maxEliminations) {
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.add(new Node(0, 0, 0, 0));
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node head = queue.remove();
                if (head.row == grid.length - 1 && head.col == grid[0].length - 1) { //target node reached, return
                    return head.curLength;
                }
                for (int j = 0; j < 4; j++) {
                    int new_row = head.row + x_offset[j];
                    int new_col = head.col + y_offset[j];
                    if (new_row >= 0 && new_row < grid.length && new_col >= 0 && new_col < grid[0].length) {
                        if (grid[new_row][new_col] == 0 && !visited[new_row][new_col][head.currentEliminations]) { //free node reached
                            visited[new_row][new_col][head.currentEliminations] = true;
                            queue.add(new Node(new_row, new_col, head.currentEliminations, head.curLength + 1));
                        } else if (grid[new_row][new_col] == 1 && head.currentEliminations + 1 <= maxEliminations &&
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
