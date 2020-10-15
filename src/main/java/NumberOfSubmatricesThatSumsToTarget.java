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
     * Need to generate all the submatrix sum by utilizing the already computed submatrix sum
     * e.g for input:
     * [0,1,0]
     * [1,1,1]
     * [0,1,0]
     * we would first try col1= 0 and col2 = 0, this will give current submatrix as
     * [0]
     * [1]
     * [0]
     * Now apply logic similar to {@link SubarraySumEqualsK} to find subarray with target sum and update the result
     * Now col1 = 0 and col2 = 1
     * [1]
     * [2]
     * [1]
     * Again find subarray with target sum and update the result
     * <p>
     * Trick to visualize this as expanding the matrix by considering a different base (col1) every time
     * Initially we picked first column as the base
     * Next we try out second col as the base and so on.
     * <p>
     * Tushar Roy's excellent simple video on the approach: https://www.youtube.com/watch?v=yCQN096CwWM
     * Related problem: Maximum sum rectangular submatrix in a matrix
     * {@link MaxSumOfRectangleNoLargerThanK}
     */
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int result = 0;
        for (int col1 = 0; col1 < matrix[0].length; col1++) {
            int[] currentSubMatrixSum = new int[matrix.length]; //init the submatrix after considering a new base
            for (int col2 = col1; col2 < matrix[0].length; col2++) { //expand on the current base
                for (int row = 0; row < matrix.length; row++) {
                    currentSubMatrixSum[row] += matrix[row][col2]; //keep on updating to utilize previous results
                }
                //process the current submatrix sum similar to SubarraySumEqualsK
                HashMap<Integer, Integer> map = new HashMap<>();
                map.put(0, 1);
                int currentPrefixSum = 0;
                for (int i = 0; i < currentSubMatrixSum.length; i++) {
                    currentPrefixSum += currentSubMatrixSum[i];
                    if (map.containsKey(currentPrefixSum - target)) {
                        result += map.get(currentPrefixSum - target);
                    }
                    map.put(currentPrefixSum, map.getOrDefault(currentPrefixSum, 0) + 1);
                }
            }
        }
        return result;
    }
}
