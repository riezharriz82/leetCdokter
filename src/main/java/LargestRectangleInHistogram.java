import java.util.ArrayDeque;

/**
 * https://leetcode.com/problems/largest-rectangle-in-histogram
 * <p>
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
 */
public class LargestRectangleInHistogram {
    //n^2 approach would be to consider each bar and for each bar, find the first smallest element on the left and right side
    // this will allow us to find the area which this bar can contribute to
    // keep track of max area obtained so far
    // if we can find the next and previous smallest element in constant time than time complexity can be reduced to o(n)
    // this is where monotonic stack comes into play
    public int largestRectangleArea(int[] heights) {
        ArrayDeque<Integer> stack = new ArrayDeque<>(); //stack of indices
        int index = 0, maxArea = 0;
        while (index < heights.length) {
            if (stack.isEmpty() || heights[stack.peek()] <= heights[index]) {
                //only push bar greater than or equal to current height so that you can identify bar which is smaller than current bar in constant time
                stack.push(index++);
            } else {
                int poppedIndex = stack.pop();
                int curHeight = heights[poppedIndex];
                //this is the most important part
                //if stack is empty than height[poppedIndex] is smaller than all the previous indices e.g. {5,4,3,2,6}
                //so the width should be the currentIndex e.g. {10,8,5}
                // otherwise width should be the index of smaller element on right side - index of smaller element on left side (which is top of the stack) - 1
                // -1 is because we need to exclude the index of smaller element on right side as well
                // index of smaller element on left side is the current top because we are pushing elements to the stack only if they are >= top of the stack
                int curWidth = stack.isEmpty() ? index : index - stack.peek() - 1;
                int curArea = curHeight * curWidth;
                maxArea = Math.max(curArea, maxArea);
            }
        }
        while (!stack.isEmpty()) {
            //repeat the same process as above
            int poppedIndex = stack.pop();
            int curHeight = heights[poppedIndex];
            //here index == length of the array which provides the rightmost boundary
            int curWidth = stack.isEmpty() ? index : index - stack.peek() - 1;
            int curArea = curHeight * curWidth;
            maxArea = Math.max(curArea, maxArea);
        }
        return maxArea;
    }
}
