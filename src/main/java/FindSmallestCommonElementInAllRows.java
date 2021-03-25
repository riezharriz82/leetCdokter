/**
 * https://leetcode.com/problems/find-smallest-common-element-in-all-rows/
 * <p>
 * Given an m x n matrix mat where every row is sorted in strictly increasing order, return the smallest common element in all rows.
 * <p>
 * If there is no common element, return -1.
 * <p>
 * Input: mat = [[1,2,3,4,5],[2,4,5,8,10],[3,5,7,9,11],[1,3,5,7,9]]
 * Output: 5
 * <p>
 * Input: mat = [[1,2,3],[2,3,4],[2,3,5]]
 * Output: 2
 * <p>
 * Constraints:
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 500
 * 1 <= mat[i][j] <= 104
 * mat[i] is sorted in strictly increasing order.
 */
public class FindSmallestCommonElementInAllRows {
    /**
     * Approach: As the problem statement mentions that the row is sorted in "strictly" increasing order, this means there is no duplicate.
     * So we can maintain a frequency counter for the elements and the smallest element with frequency == no of rows, will be the result
     * If the rows contained duplicate elements, then we would have to skip duplicates be checking whether previous element is similar to cur element
     * <p>
     * Initially I was going to solve it by performing binary search for each element in first row and checking whether the element exists
     * in all the rows but then lightning stuck and I thought of HashMap solution.
     */
    public int smallestCommonElement(int[][] mat) {
        int[] freqs = new int[10001];
        for (int[] row : mat) {
            for (int val : row) {
                freqs[val]++;
            }
        }
        for (int i = 1; i <= 10000; i++) {
            if (freqs[i] == mat.length) {
                return i;
            }
        }
        return -1;
    }
}
