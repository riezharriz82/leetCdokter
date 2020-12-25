import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/diagonal-traverse/
 * <p>
 * Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.
 * <p>
 * Input:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * <p>
 * Output:  [1,2,4,7,5,3,6,8,9]
 */
public class DiagonalTraverse {
    /**
     * Approach: All elements of a diagonal have the same value of row+col (/ diagonals) or row-col (\ diagonals)
     * Maintain a mapping of all elements part of the same diagonal and then reverse the even diagonals
     * <p>
     * {@link SpiralMatrix} {@link alternate.DiagonalTraversalOfBinaryTree} {@link alternate.ToeplitzMatrix} related problems
     */
    public int[] findDiagonalOrder(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return new int[]{};
        }
        int n = matrix[0].length;
        int totalDiagonals = m - 1 + n - 1 + 1;
        List<List<Integer>> list = new ArrayList<>(totalDiagonals);
        for (int i = 0; i < totalDiagonals; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int diagonalIndex = i + j;
                list.get(diagonalIndex).add(matrix[i][j]); //add current element to it's respective diagonalIndex
            }
        }
        int[] result = new int[m * n];
        int index = 0;
        for (int i = 0; i < totalDiagonals; i++) {
            if (i % 2 == 0) {
                //make sure to reverse the even indexes
                Collections.reverse(list.get(i));
            }
            for (int j = 0; j < list.get(i).size(); j++) {
                result[index++] = list.get(i).get(j);
            }
        }
        return result;
    }
}
