/**
 * https://leetcode.com/problems/rotate-image/
 * <p>
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 * <p>
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
 * <p>
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [[7,4,1],[8,5,2],[9,6,3]]
 * <p>
 * Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 * <p>
 * Input: matrix = [[1]]
 * Output: [[1]]
 * <p>
 * Input: matrix = [[1,2],[3,4]]
 * Output: [[3,1],[4,2]]
 */
public class RotateImage {
    /**
     * Approach: Tricky ad-hoc problem, solved using swapping groups of 4 elements using recursion. Could also use iteration to swap them.
     * Trick was to note that (2,1) will get shifted to (1, (n-1)-2) index where n-1 is the bounds of the matrix
     * ie. (row,col) will get shifted to (col, bounds - row)
     * This shift has to be done recursively in pairs of 4.
     * <p>
     * Also be careful in rotating a cell only once i.e set the outer bounds carefully not to visit a cell twice.
     * <p>
     * An alternate simpler way to solve this would be to first transpose (swap the rows with the columns) and then reverse along the rows
     * Code is much easier to implement this way.
     * <p>
     * Anyways, am very proud to get AC in first attempt using my own recursive solution :D
     * <p>
     * {@link SpiralMatrix} {@link alternate.ToeplitzMatrix} {@link RotatingTheBox}
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int low = 0, high = n - 1;
        while (low < high) { //this loop ensures we don't swap a cell twice, it kinda visits the cell in a tapered down manner
            //pattern of visiting cells
            //------
            // ----
            //  --
            for (int col = low; col < high; col++) {
                int row = low; //fix the row and visit all the valid columns under the bound
                swap(row, col, -9999, row, col, n - 1, matrix);
            }
            low++;
            high--;
        }
    }

    private void swap(int start_row, int start_col, int prev_val, int cur_row, int cur_col, int n, int[][] matrix) {
        if (prev_val == -9999) {
            //we are at the starting cell
            swap(start_row, start_col, matrix[cur_row][cur_col], cur_col, n - cur_row, n, matrix);
        } else if (cur_row == start_row && cur_col == start_col) {
            //we are now back at the starting cell, loop is completed
            //put the previous cell value in this cell
            matrix[cur_row][cur_col] = prev_val;
        } else {
            //do a postorder traversal and then swap out previous cell value in current cell
            swap(start_row, start_col, matrix[cur_row][cur_col], cur_col, n - cur_row, n, matrix);
            matrix[cur_row][cur_col] = prev_val;
        }
    }
}
