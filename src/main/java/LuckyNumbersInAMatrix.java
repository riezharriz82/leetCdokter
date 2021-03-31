import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/lucky-numbers-in-a-matrix/
 * <p>
 * Given a m * n matrix of distinct numbers, return all lucky numbers in the matrix in any order.
 * <p>
 * A lucky number is an element of the matrix such that it is the minimum element in its row and maximum in its column.
 * <p>
 * Input: matrix = [[3,7,8],[9,11,13],[15,16,17]]
 * Output: [15]
 * Explanation: 15 is the only lucky number since it is the minimum in its row and the maximum in its column
 * <p>
 * Input: matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
 * Output: [12]
 * Explanation: 12 is the only lucky number since it is the minimum in its row and the maximum in its column.
 * <p>
 * Input: matrix = [[7,8],[1,2]]
 * Output: [7]
 * <p>
 * Constraints:
 * m == mat.length
 * n == mat[i].length
 * 1 <= n, m <= 50
 * 1 <= matrix[i][j] <= 10^5.
 * All elements in the matrix are distinct.
 */
public class LuckyNumbersInAMatrix {
    /**
     * Approach: 2 pass solution, keep track of smallest number in every row and largest number in every column
     * Then do a second pass to see whether the current number equals the smallest number in current row and largest number in current col
     * If yes, it's a lucky number
     */
    public List<Integer> luckyNumbers(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] minElementPerRow = new int[rows];
        int[] maxElementPerCol = new int[cols];
        Arrays.fill(maxElementPerCol, Integer.MIN_VALUE);
        Arrays.fill(minElementPerRow, Integer.MAX_VALUE);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                minElementPerRow[i] = Math.min(minElementPerRow[i], matrix[i][j]);
                maxElementPerCol[j] = Math.max(maxElementPerCol[j], matrix[i][j]);
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (minElementPerRow[i] == matrix[i][j] && maxElementPerCol[j] == matrix[i][j]) {
                    //lucky number found
                    res.add(matrix[i][j]);
                }
            }
        }
        return res;
    }
}
