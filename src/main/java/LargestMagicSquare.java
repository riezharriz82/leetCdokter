/**
 * <pre>
 * https://leetcode.com/problems/largest-magic-square/
 *
 * A k x k magic square is a k x k grid filled with integers such that every row sum, every column sum, and both diagonal sums are all equal.
 * The integers in the magic square do not have to be distinct. Every 1 x 1 grid is trivially a magic square.
 *
 * Given an m x n integer grid, return the size (i.e., the side length k) of the largest magic square that can be found within this grid.
 *
 * Input: grid = [[7,1,4,5,6],[2,5,1,6,4],[1,5,4,3,2],[1,2,7,3,4]]
 * Output: 3
 * Explanation: The largest magic square has a size of 3.
 * Every row sum, column sum, and diagonal sum of this magic square is equal to 12.
 * - Row sums: 5+1+6 = 5+4+3 = 2+7+3 = 12
 * - Column sums: 5+5+2 = 1+4+7 = 6+3+3 = 12
 * - Diagonal sums: 5+4+3 = 6+4+2 = 12
 *
 * Input: grid = [[5,1,3,1],[9,3,3,1],[1,3,3,8]]
 * Output: 2
 *
 * Constraints:
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * 1 <= grid[i][j] <= 10^6
 * </pre>
 */
public class LargestMagicSquare {
    /**
     * Approach: Brute force, for all squares in a matrix, check whether rowSum == colSum == diagSum == antiDiagSum
     * TimeComplexity: k*m*n*(2*m*n (rowSum+colSum) + 2*k (diagSum)) ~27 ms
     * <p>
     * We can reduce the time complexity to k*m*n*(2*k (rowSum+colSum) + 2*k (diagSum)) by using prefix sum for row and col ~4 ms
     * If we use prefix sum for diag and anti-diagonal time complexity will be reduced to k*m*n(2*k + constant time (diagSum))
     *
     * Was not able to solve this during the contest because I estimated the time complexity to be n^4 ~= 6.25*10^6 which should ideally
     * time out but I think the test cases are weak
     * {@link RangeSumQuery2DImmutable}
     */
    public int largestMagicSquare(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] rowPrefix = new int[m + 1][n + 1]; //+1 size to avoid if else checks while calculating prefix sum of a range
        int[][] colPrefix = new int[m + 1][n + 1];
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                rowPrefix[r + 1][c + 1] += (rowPrefix[r + 1][c] + matrix[r][c]); //[r,c] is offsetted by [r+1, c+1]
                colPrefix[r + 1][c + 1] += (colPrefix[r][c + 1] + matrix[r][c]);
            }
        }
        for (int k = Math.min(m, n); k >= 2; k--) {
            for (int r = 0; r <= m - k; r++) {
                for (int c = 0; c <= n - k; c++) {
                    int rowSum = rowSum(matrix, r, c, k, rowPrefix);
                    if (rowSum != -1 && colSum(matrix, r, c, k, rowSum, colPrefix) && diagonal(matrix, r, c, k, rowSum) && antiDiagonal(matrix, r, c, k, rowSum)) {
                        //magic square found with rowSum == colSum == diagonalSum == antiDiagonalSum
                        return k;
                    }
                }
            }
        }
        return 1;
    }

    private boolean antiDiagonal(int[][] matrix, int start_r, int start_c, int k, int expectedSum) {
        start_c = start_c + k - 1;
        int curDiagSum = 0;
        for (int i = 0; i < k; i++) {
            curDiagSum += matrix[start_r + i][start_c - i];
        }
        return curDiagSum == expectedSum;
    }

    private boolean diagonal(int[][] matrix, int start_r, int start_c, int k, int expectedSum) {
        int curDiagSum = 0;
        for (int i = 0; i < k; i++) {
            curDiagSum += matrix[start_r + i][start_c + i];
        }
        return curDiagSum == expectedSum;
    }

    private boolean colSum(int[][] matrix, int start_r, int start_c, int k, int expectedSum, int[][] colPrefix) {
        int end_r = start_r + k - 1;
        for (int c = start_c; c < start_c + k; c++) {
            int curColSum = colPrefix[end_r + 1][c + 1] - colPrefix[end_r - k + 1][c + 1]; //very important to handle indices correctly
            if (curColSum != expectedSum) {
                return false;
            }
        }
        return true;
        /*
        O(m*n) solution to compute column sum of size k starting from [start_r, start_c]
        for (int c = start_c; c < start_c + k; c++) {
            int curColSum = 0;
            for (int r = start_r; r < start_r + k; r++) {
                curColSum += matrix[r][c];
            }
            if (curColSum != expectedSum) {
                return false;
            }
        }
        return true;
        */
    }

    private int rowSum(int[][] matrix, int start_r, int start_c, int k, int[][] rowPrefix) {
        int end_c = start_c + k - 1;
        int prevRowSum = -1;
        for (int r = start_r; r < start_r + k; r++) {
            int curRowSum = rowPrefix[r + 1][end_c + 1] - rowPrefix[r + 1][end_c - k + 1];
            if (prevRowSum == -1 || prevRowSum == curRowSum) {
                prevRowSum = curRowSum;
            } else {
                return -1;
            }
        }
        return prevRowSum;
        /*
        O(m*n) solution to compute row sum of size k starting from [start_r, start_c]
        int prevRowSum = -1;
        for (int r = start_r; r < start_r + k; r++) {
            int curRowSum = 0;
            for (int c = start_c; c < start_c + k; c++) {
                curRowSum += matrix[r][c];
            }
            if (prevRowSum == -1 || prevRowSum == curRowSum) {
                prevRowSum = curRowSum;
            } else {
                return -1;
            }
        }
        return prevRowSum;
        */
    }
}
