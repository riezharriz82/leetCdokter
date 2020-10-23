import java.util.List;

/**
 * https://leetcode.com/problems/leftmost-column-with-at-least-a-one/
 * <p>
 * A binary matrix means that all elements are 0 or 1. For each individual row of the matrix, this row is sorted in non-decreasing order.
 * <p>
 * Given a row-sorted binary matrix binaryMatrix, return leftmost column index(0-indexed) with at least a 1 in it. If such index doesn't exist, return -1.
 * <p>
 * You can't access the Binary Matrix directly.  You may only access the matrix using a BinaryMatrix interface:
 * <p>
 * BinaryMatrix.get(row, col) returns the element of the matrix at index (row, col) (0-indexed).
 * BinaryMatrix.dimensions() returns a list of 2 elements [rows, cols], which means the matrix is rows * cols.
 * Submissions making more than 1000 calls to BinaryMatrix.get will be judged Wrong Answer.
 */
public class LeftMostColumnWithAtleastAOne {
    /**
     * Approach: Perform binary search on each row to find the first occurrence of 1 in that row. Keep updating the leftMostColumn
     * <p>
     * A linear time complexity is also possible, if we start at top right and check if it's 0, then move down.
     * If 1, then move left
     * Repeat the process until we are out bounds.
     */
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dimensions = binaryMatrix.dimensions();
        int rows = dimensions.get(0);
        int cols = dimensions.get(1);
        int leftMostColumn = Integer.MAX_VALUE;
        for (int i = 0; i < rows; i++) {
            int low = 0, high = cols - 1;
            int ans = Integer.MAX_VALUE;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (binaryMatrix.get(i, mid) == 1) {
                    ans = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            leftMostColumn = Math.min(leftMostColumn, ans);
        }
        return leftMostColumn == Integer.MAX_VALUE ? -1 : leftMostColumn;
    }

    private interface BinaryMatrix {
        int get(int row, int col);

        List<Integer> dimensions();
    }
}
