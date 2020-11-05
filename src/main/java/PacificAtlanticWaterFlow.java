import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/pacific-atlantic-water-flow/
 * <p>
 * Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean"
 * touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.
 * <p>
 * Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.
 * <p>
 * Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
 * <p>
 * The order of returned grid coordinates does not matter.
 * <p>
 * Given the following 5x5 matrix:
 * <pre>
 *   Pacific ~   ~   ~   ~   ~
 *        ~  1   2   2   3  (5) *
 *        ~  3   2   3  (4) (4) *
 *        ~  2   4  (5)  3   1  *
 *        ~ (6) (7)  1   4   5  *
 *        ~ (5)  1   1   2   4  *
 *           *   *   *   *   * Atlantic
 * </pre>
 * Return:
 * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
 */
public class PacificAtlanticWaterFlow {
    int[] x_offsets = new int[]{0, 1, 0, -1};
    int[] y_offsets = new int[]{1, 0, -1, 0};

    /**
     * Approach: Not gonna lie, initially I implemented it by doing DFS from the inner cells and trying to check whether it can reach the border cells or not
     * But it gave WA on the sample input, then I realized about the {@link SurroundedRegions} problem and the issue I faced earlier
     * <p>
     * The trick is to do reverse DFS from the borders and see which inner nodes it can touch. Obviously condition needs to be reversed but it works well.
     */
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        if (matrix.length == 0) {
            return new ArrayList<>();
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] canReachPacificOcean = new int[m][n]; //two visited arrays to represent two states
        int[][] canReachAtlanticOcean = new int[m][n];
        for (int i = 0; i < n; i++) {
            markReachableNodes(0, i, canReachPacificOcean, matrix); //first row is pacific ocean
            markReachableNodes(m - 1, i, canReachAtlanticOcean, matrix); //last row is atlantic ocean
        }
        for (int i = 0; i < m; i++) {
            markReachableNodes(i, 0, canReachPacificOcean, matrix); //first col is pacific ocean
            markReachableNodes(i, n - 1, canReachAtlanticOcean, matrix); //last col is atlantic ocean
        }
        ArrayList<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (canReachAtlanticOcean[i][j] == 1 && canReachPacificOcean[i][j] == 1) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }
        return result;
    }

    private void markReachableNodes(int i, int j, int[][] oceanCanReachNode, int[][] matrix) {
        if (oceanCanReachNode[i][j] == 0) { //if this node is unvisited, visit this node
            oceanCanReachNode[i][j] = 1; //avoid circular loops
            for (int k = 0; k < 4; k++) {
                int new_i = i + x_offsets[k];
                int new_j = j + y_offsets[k];
                if (new_i >= 0 && new_i < oceanCanReachNode.length && new_j >= 0 && new_j < oceanCanReachNode[0].length && matrix[new_i][new_j] >= matrix[i][j]) {
                    markReachableNodes(new_i, new_j, oceanCanReachNode, matrix);
                }
            }
        }
    }
}
