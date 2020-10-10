import java.util.HashMap;

/**
 * https://leetcode.com/problems/number-of-submatrices-that-sum-to-target/
 * <p>
 * Given a matrix and a target, return the number of non-empty submatrices that sum to target.
 * <p>
 * A submatrix x1, y1, x2, y2 is the set of all cells matrix[x][y] with x1 <= x <= x2 and y1 <= y <= y2.
 * <p>
 * Two submatrices (x1, y1, x2, y2) and (x1', y1', x2', y2') are different if they have some coordinate that is different: for example, if x1 != x1'.
 * <p>
 * Input: matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
 * Output: 4
 * Explanation: The four 1x1 submatrices that only contain 0.
 */
public class NumberOfSubmatricesThatSumsToTarget {
    /**
     * Approach: Harder version of {@link SubarraySumEqualsK}
     * Need to create a 2D prefix sum to calculate all submatrices sum in constant time
     * Once we have all the submatrices sum, we can leverage hashmap to find a submatrix with target sum
     * e.g for input:
     * [0,1,0]
     * [1,1,1]
     * [0,1,0]
     * 2d prefix sum is:
     * [0,1,1]
     * [1,3,4]
     * [1,4,5]
     * <p>
     * Now consider submatrices sum if we consider row1 as 2 and row2 as 2 it would be 1-1 = 0 , 4-3=1, 5-4=1
     * so [0,1,1] would be all submatrices prefix sum that spans across row2
     * if we have to find submatrices with target sum as 0, we can apply logic similar to 1D prefix sum and find the count of target subarray counts
     * <p>
     * So in short, generate all submatrices sum in constant time using 2d prefix sum and then extend the submatrices fixing the sizes of row
     * so that it gets reduced to 1d prefix sum and we find target subarray count
     */
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] prefixSum2D = new int[m + 1][n]; //rows are incremented by +1 to easily calculate submatrices sum
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                prefixSum2D[i + 1][j] = (j == 0 ? matrix[i][j] : prefixSum2D[i + 1][j - 1] + matrix[i][j]); //row prefix sum
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                prefixSum2D[i + 1][j] += prefixSum2D[i][j]; //column prefix sum
            }
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int result = 0;
        for (int row1 = 1; row1 <= m; row1++) { //generate all possible submatrices that starts from row1 and ends at row2
            for (int row2 = row1; row2 <= m; row2++) {
                int matrixSum;
                map.clear();
                map.put(0, 1);
                for (int col = 0; col < n; col++) { //extend the current submatrix to include more columns similar to the way we extend 1D prefix sum in right direction
                    matrixSum = (prefixSum2D[row2][col] - prefixSum2D[row1 - 1][col]);
                    if (map.containsKey(matrixSum - target)) {
                        result += map.get(matrixSum - target);
                    }
                    map.put(matrixSum, map.getOrDefault(matrixSum, 0) + 1);
                }
            }
        }
        return result;
    }
}
