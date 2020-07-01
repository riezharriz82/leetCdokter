/**
 * https://leetcode.com/problems/count-square-submatrices-with-all-ones/
 * <p>
 * Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.
 * <p>
 * Input: matrix =
 * <pre>
 * [
 *   [0,1,1,1],
 *   [1,1,1,1],
 *   [0,1,1,1]
 * ]
 * </pre>
 * Output: 15
 * Explanation:
 * There are 10 squares of side 1.
 * There are 4 squares of side 2.
 * There is  1 square of side 3.
 * Total number of squares = 10 + 4 + 1 = 15.
 */
public class CountSquareSubmatricesWithAllOnes {
    public int countSquares(int[][] matrix) {
        int res = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i != 0 && j != 0) {
                    //only proceed if there is a chance of getting a bigger square
                    if (matrix[i][j] >= 1) {
                        //get the smallest size of the square matrices currently being formed at the 3 other corners
                        //e.g if its {{2,1}, {1,1}}, you can only get a new matrix of size 2 at [i,j]
                        // if it's {{2,2},{2,1} you can get a new matrix of size 3
                        matrix[i][j] = Math.min(matrix[i - 1][j], Math.min(matrix[i - 1][j - 1], matrix[i][j - 1])) + 1;
                    }
                }
                res += matrix[i][j];
            }
        }
        return res;
    }
}
