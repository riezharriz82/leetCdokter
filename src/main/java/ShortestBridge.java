import java.util.ArrayDeque;

/**
 * https://leetcode.com/problems/shortest-bridge/
 * <p>
 * In a given 2D binary array A, there are two islands.  (An island is a 4-directionally connected group of 1s not connected to any other 1s.)
 * <p>
 * Now, we may change 0s to 1s so as to connect the two islands together to form 1 island.
 * <p>
 * Return the smallest number of 0s that must be flipped.  (It is guaranteed that the answer is at least 1.)
 * <p>
 * Input: A = [[0,1],[1,0]]
 * Output: 1
 * <p>
 * Input: A = [[0,1,0],[0,0,0],[0,0,1]]
 * Output: 2
 */
public class ShortestBridge {
    int[] x_offset = new int[]{0, 1, 0, -1};
    int[] y_offset = new int[]{1, 0, -1, 0};

    /**
     * Approach: Hidden Multi source BFS
     * <p>
     * Initially I went ahead with BFS from any cell of the first island keeping track of the minZeroesFlipped at any cell
     * It was kinda BFS with pruning because I visit a cell only if I can visit it with fewer zeroesFlipped so far
     * Whenever I visit a cell belonging to second island, I update the global minima of minZeroesFlipped
     * <p>
     * However the runtime was ~22 ms which can be improved if we use bidirectional bfs, ie. keep track of all cells belonging
     * to first island, use them as a starting point of BFS. Whenever you find a cell belonging to second island, return the
     * numZeroesFlipped so far
     * <p>
     * {@link MakingALargeIsland} {@link RottingOranges} {@link WallsAndGates} {@link CheapestFlightWithinKStop}
     * {@link ShortestPathVisitingAllNodes} related multi bfs problem
     */
    public int shortestBridge(int[][] A) {
        int m = A.length;
        int identifier = 2;
        ArrayDeque<Node> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (A[i][j] == 1) {
                    markConnectedNodes(A, identifier++, i, j);
                    //mark all the connected nodes belonging to the same island with same identifier
                }
            }
        }
        //first island has identifier 2 and second island has identifier 3
        boolean[][] visited = new boolean[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (A[i][j] == 2) {
                    queue.add(new Node(i, j, 0)); //push all nodes belonging to first island as the starting nodes
                    visited[i][j] = true;
                }
            }
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node head = queue.remove();
                if (A[head.i][head.j] == 3) { //second island reached, return
                    return head.zeroesFlipped;
                } else {
                    int newZeroesFlipped = head.zeroesFlipped;
                    if (A[head.i][head.j] == 0) {
                        newZeroesFlipped++; //update the zeroesFlipped if the cell is 0
                    }
                    for (int j = 0; j < 4; j++) {
                        int new_i = head.i + x_offset[j];
                        int new_j = head.j + y_offset[j];
                        if (new_i >= 0 && new_i < A.length && new_j >= 0 && new_j < A[0].length
                                && !visited[new_i][new_j]) {
                            visited[new_i][new_j] = true; //remember to mark the cell as visited before pushing to the queue
                            queue.add(new Node(new_i, new_j, newZeroesFlipped));
                        }
                    }
                }
            }
        }
        return -1; //not possible
    }

    private void markConnectedNodes(int[][] A, int identifier, int i, int j) {
        A[i][j] = identifier;
        for (int k = 0; k < 4; k++) {
            int new_i = i + x_offset[k];
            int new_j = j + y_offset[k];
            if (new_i >= 0 && new_i < A.length && new_j >= 0 && new_j < A[0].length && A[new_i][new_j] == 1) {
                markConnectedNodes(A, identifier, new_i, new_j);
            }
        }
    }

    private static class Node {
        int i;
        int j;
        int zeroesFlipped;

        public Node(int i, int j, int zeroesFlipped) {
            this.i = i;
            this.j = j;
            this.zeroesFlipped = zeroesFlipped;
        }
    }
}
