import java.util.Arrays;

/**
 * https://leetcode.com/problems/the-k-weakest-rows-in-a-matrix/
 * <p>
 * Given a m * n matrix mat of ones (representing soldiers) and zeros (representing civilians),
 * return the indexes of the k weakest rows in the matrix ordered from the weakest to the strongest.
 * <p>
 * A row i is weaker than row j, if the number of soldiers in row i is less than the number of soldiers in row j,
 * or they have the same number of soldiers but i is less than j. Soldiers are always stand in the frontier of a row,
 * i.e always ones may appear first and then zeros.
 * <p>
 * Input: mat =
 * [[1,1,0,0,0],
 * [1,1,1,1,0],
 * [1,0,0,0,0],
 * [1,1,0,0,0],
 * [1,1,1,1,1]],
 * k = 3
 * Output: [2,0,3]
 * Explanation:
 * The number of soldiers for each row is:
 * row 0 -> 2
 * row 1 -> 4
 * row 2 -> 1
 * row 3 -> 2
 * row 4 -> 5
 * Rows ordered from the weakest to the strongest are [2,0,3,1,4]
 * <p>
 * Input: mat =
 * [[1,0,0,0],
 * [1,1,1,1],
 * [1,0,0,0],
 * [1,0,0,0]],
 * k = 2
 * Output: [0,2]
 * Explanation:
 * The number of soldiers for each row is:
 * row 0 -> 1
 * row 1 -> 4
 * row 2 -> 1
 * row 3 -> 1
 * Rows ordered from the weakest to the strongest are [0,2,3,1]
 * <p>
 * Constraints:
 * m == mat.length
 * n == mat[i].length
 * 2 <= n, m <= 100
 * 1 <= k <= m
 * matrix[i][j] is either 0 or 1.
 */
public class KWeakestRowsInMatrix {
    /**
     * Approach: Perform a columnar traversal to find first 0 in each row. Keep track of first k indices with the smallest no of 1's
     * <p>
     * Time Complexity: O(mn) Space Complexity: O(k)
     * <p>
     * {@link Search2DMatrixII}
     */
    public int[] kWeakestRowsOptimized(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        int[] res = new int[k];
        int index = 0;
        for (int col = 0; col < n; col++) {
            for (int row = 0; row < m; row++) {
                if (mat[row][col] == 0) {
                    if (col == 0 || mat[row][col - 1] == 1) { //make sure to verify whether the current 0 is the first 0 in the current row
                        res[index++] = row;
                    }
                }
                if (index == k) {
                    //if k weakest rows have been found, return the result
                    return res;
                }
            }
        }
        //edge case to add rows with all 1's
        for (int i = 0; i < m && index < k; i++) {
            if (mat[i][n - 1] == 1) {
                res[index++] = i;
            }
        }
        return res;
    }

    /**
     * Approach: Count no of 1's in each row using linear search and sort them based on their index
     * <p>
     * Instead of linear search, binary search can be applied to count no of 1's as the row is already sorted
     * and instead of sorting the entire array, we could use priority queue
     * <p>
     * {@link RelativeRanks}
     */
    public int[] kWeakestRows(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        int[] soldiers = new int[m];
        for (int i = 0; i < m; i++) {
            int ones = 0;
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    ones++;
                } else {
                    break;
                }
            }
            soldiers[i] = ones;
        }
        Integer[] indices = new Integer[m];
        for (int i = 0; i < m; i++) {
            indices[i] = i;
        }
        Arrays.sort(indices, (o1, o2) -> { //sort the indices array based on the no of soldiers present at that index
            if (soldiers[o1] == soldiers[o2]) {
                return Integer.compare(o1, o2);
            } else {
                return Integer.compare(soldiers[o1], soldiers[o2]);
            }
        });
        int[] res = new int[k];
        for (int i = 0; i < k; i++) { //get the weakest k rows
            res[i] = indices[i];
        }
        return res;
    }
}
