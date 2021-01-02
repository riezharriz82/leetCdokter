import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/sort-the-matrix-diagonally/
 * <p>
 * A matrix diagonal is a diagonal line of cells starting from some cell in either the topmost row or leftmost column and going
 * in the bottom-right direction until reaching the matrix's end.
 * For example, the matrix diagonal starting from mat[2][0], where mat is a 6 x 3 matrix, includes cells mat[2][0], mat[3][1], and mat[4][2].
 * <p>
 * Given an m x n matrix mat of integers, sort each matrix diagonal in ascending order and return the resulting matrix.
 * <p>
 * Input: mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
 * Output: [[1,1,1,1],[1,2,2,2],[1,2,3,3]]
 */
public class SortMatrixDiagonally {
    /**
     * Approach: Maintain mapping of diagonalId to a priority queue of elements present in the diagonal id
     * <p>
     * {@link DiagonalTraverse} {@link LongestLineOfConsecutiveOneInMatrix}
     */
    public int[][] diagonalSort(int[][] mat) {
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>(); //mapping of diagonalId to elements present in the diagonal id
        int m = mat.length;
        int n = mat[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map.computeIfAbsent(i - j, __ -> new PriorityQueue<>()).add(mat[i][j]);
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = map.get(i - j).remove(); //one by one fix the smallest element belonging to a diagonal id
            }
        }
        return mat;
    }
}
