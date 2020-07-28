/**
 * https://leetcode.com/problems/set-matrix-zeroes/
 * <p>
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.
 * <p>
 * Input:
 * <pre>
 * [
 *   [1,1,1],
 *   [1,0,1],
 *   [1,1,1]
 * ]
 * Output:
 * [
 *   [1,0,1],
 *   [0,0,0],
 *   [1,0,1]
 * ]
 * </pre>
 * Follow up:
 * <p>
 * A straight forward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 */
public class SetMatrixZeroes {
    /**
     * In order to reset the indices optimally, we need to know whether this row or column is cursed or not.
     * We can do this by creating additional space of O(m+n) and just set the bit of cursed row/column
     * But the challenge is to do in constant space, so we can leverage first row and first column as the space for setting flags.
     * <p>
     * What about the first row and first column you ask? It can be handled by just tracking two variables.
     */
    public void setZeroes(int[][] matrix) {
        int resetFirstColumn = 0, resetFirstRow = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0 && j == 0) {
                        resetFirstRow = 1;
                        resetFirstColumn = 0;
                    } else if (i == 0) {
                        resetFirstRow = 1;
                    } else if (j == 0) {
                        resetFirstColumn = 1;
                    } else {
                        matrix[i][0] = 0;
                        matrix[0][j] = 0;
                    }
                }
            }
        }
        //handles all numbers except first row and first column
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] != 0 && matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (resetFirstRow == 1) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }
        if (resetFirstColumn == 1) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
