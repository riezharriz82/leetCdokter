import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/spiral-matrix/
 * <p>
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 * <p>
 * Input:
 * [[ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]]
 * Output: [1,2,3,6,9,8,7,4,5]
 */
public class SpiralMatrix {
    //directions are in right, down, left, top order
    int[] x_offset = new int[]{0, 1, 0, -1};
    int[] y_offset = new int[]{1, 0, -1, 0};

    /**
     * Approach: Recursive solution based on {@link RobotRoomCleaner}
     * keep moving in the current direction until possible, if not possible, turn right and try to move.
     * <p>
     * Need to use visited array because input can contain negative value.
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix.length == 0) {
            return result;
        }
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        recur(matrix, result, 0, 0, 0, visited);
        return result;
    }

    private void recur(int[][] matrix, List<Integer> result, int row, int col, int direction, boolean[][] visited) {
        result.add(matrix[row][col]);
        visited[row][col] = true;
        int new_row = row + x_offset[direction];
        int new_col = col + y_offset[direction];
        //check if moving in the current direction is possible
        if (new_row >= 0 && new_row < matrix.length && new_col >= 0 && new_col < matrix[0].length && !visited[new_row][new_col]) {
            recur(matrix, result, new_row, new_col, direction, visited);
        } else {
            //rotate right and try to move
            int new_direction = (direction + 1) % 4;
            new_row = row + x_offset[new_direction];
            new_col = col + y_offset[new_direction];
            //check if movement possible otherwise return
            if (new_row >= 0 && new_row < matrix.length && new_col >= 0 && new_col < matrix[0].length && !visited[new_row][new_col]) {
                recur(matrix, result, new_row, new_col, new_direction, visited);
            }
        }
    }
}
