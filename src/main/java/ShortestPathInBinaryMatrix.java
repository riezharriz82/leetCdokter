import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/shortest-path-in-binary-matrix/
 * <p>
 * In an N by N square grid, each cell is either empty (0) or blocked (1).
 * <p>
 * A clear path from top-left to bottom-right has length k if and only if it is composed of cells C_1, C_2, ..., C_k such that:
 * <p>
 * Adjacent cells C_i and C_{i+1} are connected 8-directionally (ie., they are different and share an edge or corner)
 * C_1 is at location (0, 0) (ie. has value grid[0][0])
 * C_k is at location (N-1, N-1) (ie. has value grid[N-1][N-1])
 * If C_i is located at (r, c), then grid[r][c] is empty (ie. grid[r][c] == 0).
 * Return the length of the shortest such clear path from top-left to bottom-right.  If such a path does not exist, return -1.
 */
public class ShortestPathInBinaryMatrix {
    int[] x_offset = new int[]{0, 1, 1, 1, 0, -1, -1, -1};
    int[] y_offset = new int[]{1, 1, 0, -1, -1, -1, 0, 1};

    public int shortestPathBinaryMatrixUsingAStar(int[][] grid) {
        int targetRow = grid.length - 1, targetCol = grid[0].length - 1;
        if (grid[0][0] == 1 || grid[targetRow][targetCol] == 1) {
            return -1;
        }
        //Acts as visited set in BFS, required to put cost as the decisive factor on whether to continue this path
        // required because you can initially take a longer path to reach a node because of the heuristic
        // in case you reach the same node again but with a lower cost, you need to continue down this path, instead of skipping it
        // PS. I initially did not implement this and got a wrong answer
        int[][] costMatrix = new int[targetRow + 1][targetCol + 1];
        for (int i = 0; i < targetRow + 1; i++) {
            Arrays.fill(costMatrix[i], Integer.MAX_VALUE);
        }
        return AStar(grid, costMatrix, targetRow, targetCol);
    }

    private int AStar(int[][] grid, int[][] costMatrix, int targetRow, int targetCol) {
        //min heap with the smallest cost to reach target as the comparator
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.costToReachTarget));
        pq.add(new Node(0, 0, 1, 1 + approximateCostToReachTarget(0, 0, targetRow, targetCol)));
        while (!pq.isEmpty()) {
            Node head = pq.remove();
            if (head.row == targetRow && head.col == targetCol) {
                return head.costToReachCurrentNode;
            } else {
                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {
                        int new_x = x + head.row;
                        int new_y = y + head.col;
                        int costToReachNewNode = head.costToReachCurrentNode + 1;
                        if (new_x >= 0 && new_x <= targetRow && new_y >= 0 && new_y <= targetCol && grid[new_x][new_y] == 0
                                && costMatrix[new_x][new_y] > costToReachNewNode) { //only continue if we are getting a smaller cost to reach new node
                            int costToReachTargetFromNewNode = costToReachNewNode + approximateCostToReachTarget(new_x, new_y, targetRow, targetCol);
                            pq.add(new Node(new_x, new_y, costToReachNewNode, costToReachTargetFromNewNode));
                            costMatrix[new_x][new_y] = costToReachNewNode; //update the cost
                        }
                    }
                }
            }
        }
        return -1;
    }

    //heuristic function that takes into consideration that 8 possible moves can be made from current position
    private int approximateCostToReachTarget(int curRow, int curCol, int targetRow, int targetCol) {
        return Math.max(Math.abs(curRow - targetRow), Math.abs(targetCol - curCol));
    }

    public int shortestPathBinaryMatrixUsingBFS(int[][] grid) {
        if (grid[0][0] == 1 || grid[grid.length - 1][grid[0].length - 1] == 1) {
            return -1;
        }
        return BFS(grid);
    }

    private int BFS(int[][] grid) {
        ArrayDeque<Pair<Integer, Integer>> queue = new ArrayDeque<>();

        grid[0][0] = 2;
        queue.add(new Pair<>(0, 0));
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++; //level of the bfs traversal
            for (int i = 0; i < size; i++) {
                Pair<Integer, Integer> head = queue.remove();
                int row = head.getKey();
                int col = head.getValue();
                if (row == grid.length - 1 && col == grid[0].length - 1) { //end reached, shortcircuit as no other shortest path possible
                    return level;
                } else {
                    grid[row][col] = 2; //mark the current node as visited in order to not pick it up again
                    for (int j = 0; j < x_offset.length; j++) {
                        int new_row = row + x_offset[j];
                        int new_col = col + y_offset[j];
                        if (new_row >= 0 && new_row < grid.length && new_col >= 0 && new_col < grid[0].length && grid[new_row][new_col] == 0) {
                            queue.add(new Pair<>(new_row, new_col));
                            grid[new_row][new_col] = 3; //mark the new node with a temp mark so that it can't be added to the queue again by any other node
                        }
                    }
                }
            }
        }
        return -1;
    }

    private static class Node {
        int row;
        int col;
        int costToReachCurrentNode;
        int costToReachTarget; //cost to reach current node + approximate cost to reach target from current node

        public Node(int row, int col, int costToReachCurrentNode, int costToReachTarget) {
            this.row = row;
            this.col = col;
            this.costToReachCurrentNode = costToReachCurrentNode;
            this.costToReachTarget = costToReachTarget;
        }
    }
}
