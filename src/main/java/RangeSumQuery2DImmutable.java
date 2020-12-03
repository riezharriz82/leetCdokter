/**
 * https://leetcode.com/problems/range-sum-query-2d-immutable/
 * <p>
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 * <p>
 * Range Sum Query 2D
 * The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.
 * <p>
 * Example:
 * <pre>
 * Given matrix = [
 *   [3, 0, 1, 4, 2],
 *   [5, 6, 3, 2, 1],
 *   [1, 2, 0, 1, 5],
 *   [4, 1, 0, 1, 7],
 *   [1, 0, 3, 0, 5]
 * ]
 * </pre>
 * sumRegion(2, 1, 4, 3) -> 8
 * sumRegion(1, 1, 2, 2) -> 11
 * sumRegion(1, 2, 2, 4) -> 12
 * Note:
 * You may assume that the matrix does not change.
 * There are many calls to sumRegion function.
 * You may assume that row1 ≤ row2 and col1 ≤ col2.
 */
public class RangeSumQuery2DImmutable {
    int[][] cumulativeSum;

    /**
     * Approach: 2D prefix sum, keep a cumulative sum matrix where mat[i][j] denotes the sum of all elements from (0,0) till (i,j)
     * <p>
     * Now we have to find the cumulative sum from (r1,c1) to (r2,c2), we can't simply return mat[r2][c2] because it contains sum from (0,0)
     * So we remove some portion sum
     * <pre>
     * +---------------+   +--------------+   +---------------+   +--------------+   +--------------+
     * |               |   |         |    |   |   |           |   |         |    |   |   |          |
     * |   (r1,c1)     |   |         |    |   |   |           |   |         |    |   |   |          |
     * |   +------+    |   |         |    |   |   |           |   +---------+    |   +---+          |
     * |   |      |    | = |         |    | - |   |           | - |      (r1,c2) | + |   (r1,c1)    |
     * |   |      |    |   |         |    |   |   |           |   |              |   |              |
     * |   +------+    |   +---------+    |   +---+           |   |              |   |              |
     * |        (r2,c2)|   |       (r2,c2)|   |   (r2,c1)     |   |              |   |              |
     * +---------------+   +--------------+   +---------------+   +--------------+   +--------------+
     * </pre>
     */
    public RangeSumQuery2DImmutable(int[][] matrix) {
        cumulativeSum = matrix;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (j > 0) {
                    cumulativeSum[i][j] += cumulativeSum[i][j - 1];
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i > 0) {
                    cumulativeSum[i][j] += cumulativeSum[i - 1][j];
                }
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int result = cumulativeSum[row2][col2];
        if (row1 > 0) {
            result -= cumulativeSum[row1 - 1][col2];
        }
        if (col1 > 0) {
            result -= cumulativeSum[row2][col1 - 1];
        }
        if (row1 > 0 && col1 > 0) {
            result += cumulativeSum[row1 - 1][col1 - 1];
        }
        return result;
    }
}
