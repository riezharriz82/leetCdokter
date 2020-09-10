package alternate;

import java.util.HashMap;
import java.util.Map;

/**
 * https://binarysearch.io/problems/Toeplitz-Matrix
 * <p>
 * Given a two-dimensional matrix of integers matrix, determine whether it's a Toeplitz matrix.
 * A Toeplitz is one where every diagonal descending from left to right has the same value.
 * <p>
 * For example, this is a Toeplitz matrix:
 * <p>
 * [0, 1, 2]
 * [3, 0, 1]
 * [4, 3, 0]
 * [5, 4, 3]
 * This is not a Toeplitz matrix:
 * <p>
 * [0, 1, 2]
 * [3, 0, 7]
 * [4, 3, 0]
 * Since matrix[0][1] = 1 but matrix[1][2] = 7
 */
public class ToeplitzMatrix {
    /**
     * Approach: All the elements present in the same left diagonal shares the same (i-j).
     * So I stored the diff as a key and the value as the starting element of the diagonal
     * Iterate the elements and check the leader of their diagonal, if it's not the same then the matrix is invalid
     */
    public boolean solve(int[][] matrix) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                Integer expected = map.get(i - j);
                if (expected == null) {
                    //leader of diagonal found
                    map.put(i - j, matrix[i][j]);
                } else if (expected != matrix[i][j]) {
                    //current element does not matches the leader
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * No need of storing the leader, just directly compare each element with it's adjacent left diagonal element
     */
    public boolean solveSimplified(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i > 0 && j > 0 && matrix[i][j] != matrix[i - 1][j - 1]) {
                    return false;
                }
            }
        }
        return true;
    }
}
