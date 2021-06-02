import javafx.util.Pair;

import java.util.ArrayDeque;

/**
 * <pre>
 * https://leetcode.com/problems/01-matrix/
 *
 * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
 *
 * The distance between two adjacent cells is 1.
 *
 * Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
 * Output: [[0,0,0],[0,1,0],[0,0,0]]
 *
 * Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
 * Output: [[0,0,0],[0,1,0],[1,2,1]]
 *
 * Constraints:
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 10^4
 * 1 <= m * n <= 10^4
 * mat[i][j] is either 0 or 1.
 * There is at least one 0 in mat.
 * </pre>
 */
public class ZeroOneMatrix {
    /**
     * Approach: Multi Source BFS, push all the O's in BFS queue and start marking distance of neighbours.
     * <p>
     * Since the graph has cycles, Recursion with memoization cannot be applied directly.
     * <p>
     * However it can be solved using DP via 2 passes. This is quite tricky and ad-hoc approach.
     * In first pass, compute the shortest distance in top and left direction.
     * In second pass, compute the shortest distance in bottom and right direction.
     * <p>
     * Instead of 2 passes, 4 separate passes can be applied as well to solve this problem.
     * In each pass, compute the shortest distance in a specific direction.
     * https://leetcode.com/problems/01-matrix/discuss/101102/Short-solution-Each-path-needs-at-most-one-turn
     * This is quite similar to {@link LargestPlusSign}
     * <p>
     * {@link ShortestBridge} {@link RottingOranges} {@link WallsAndGates} related multi BFS problem
     */
    public int[][] updateMatrix(int[][] mat) {
        ArrayDeque<Pair<Integer, Integer>> queue = new ArrayDeque<>();
        int m = mat.length;
        int n = mat[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) { //push all the 0's in BFS Queue
                    queue.add(new Pair<>(i, j));
                }
            }
        }
        int[][] res = new int[m][n];
        int distance = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            distance++;
            for (int i = 0; i < size; i++) {
                Pair<Integer, Integer> head = queue.remove();
                for (int j = 0; j < 4; j++) {
                    int new_r = head.getKey() + x_offsets[j];
                    int new_c = head.getValue() + y_offsets[j];
                    if (new_r >= 0 && new_r < m && new_c >= 0 && new_c < n && mat[new_r][new_c] == 1) { //if new cell is not yet visited
                        mat[new_r][new_c] = 0; //mark the cell as visited
                        res[new_r][new_c] = distance; //update the distance
                        queue.add(new Pair<>(new_r, new_c));
                    }
                }
            }
        }
        return res;
    }

    private final int[] x_offsets = new int[]{-1, 0, 1, 0};
    private final int[] y_offsets = new int[]{0, 1, 0, -1};
}
