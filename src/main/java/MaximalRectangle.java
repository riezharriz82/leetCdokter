import java.util.ArrayDeque;

/**
 * https://leetcode.com/problems/maximal-rectangle/
 * <p>
 * Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 * <p>
 * Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * Output: 6
 * Explanation: The maximal rectangle is shown in the above picture.
 */
public class MaximalRectangle {
    /**
     * Classical Problem, Can be reduced to finding largest rectangle in histogram if you visualize each row as an continuation to the next row
     * in terms of histogram height e.g.
     * {1,1,0}
     * {1,1,1}
     * {0,1,0}
     * Histogram for first row  {1,1,0}
     * Histogram for second row {2,2,1}
     * Histogram for third row  {0,3,0}
     * <p>
     * We can calculate max rectangle in histogram after generating histogram for each row.
     *
     * Can be also solved using the "compression" technique used in other submatrices related problem but the time complexity would be n^3
     */
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int rows = matrix.length, cols = matrix[0].length;
        int[] histogram = new int[cols];
        int result = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    histogram[j]++;
                } else {
                    histogram[j] = 0;
                    //PS: This is not prefix sum, we are interested only in length of continuous 1's. If this col can't extend 1's, need to reset it to 0
                    //if we don't reset and the column value is {1,1,0,1}, then the prefix sum would be {1,2,2,3}
                    //3 will indicate the height of the histogram as 3, which will give wrong results.
                }
            }
            result = Math.max(result, maximumRectangleInHistogram(histogram));
        }
        return result;
    }

    /**
     * Similar to the logic in {@link LargestRectangleInHistogram}
     */
    private int maximumRectangleInHistogram(int[] histogram) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int index = 0;
        int maxArea = 0;
        while (index < histogram.length) {
            if (stack.isEmpty() || histogram[stack.peek()] <= histogram[index]) {
                stack.push(index++);
            } else {
                int poppedIndex = stack.pop();
                int height = histogram[poppedIndex];
                int width = stack.isEmpty() ? index : index - stack.peek() - 1;
                int currentArea = height * width;
                maxArea = Math.max(currentArea, maxArea);
            }
        }
        while (!stack.isEmpty()) {
            int poppedIndex = stack.pop();
            int height = histogram[poppedIndex];
            int width = stack.isEmpty() ? index : index - stack.peek() - 1;
            int currentArea = height * width;
            maxArea = Math.max(currentArea, maxArea);
        }
        return maxArea;
    }
}
