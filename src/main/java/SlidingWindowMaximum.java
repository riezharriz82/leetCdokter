import java.util.ArrayDeque;

/**
 * https://leetcode.com/problems/sliding-window-maximum/
 * <p>
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
 * You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.
 * <p>
 * Follow up:
 * Could you solve it in linear time?
 * <p>
 * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * <pre>
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *  </pre>
 */
public class SlidingWindowMaximum {
    /**
     * See {@link LongestContiguousSubarrayWithValidDiff} for similar sliding window problem using deque
     * Keep track of the useful numbers in the queue i.e. pop all numbers smaller than current number from the queue as they can't be maximum
     * till the window ending at current index.
     * However if you see a number smaller than the last element in queue, you need to add it, as it's possible that it can become the maximum
     * once the window slides.
     * <p>
     * {@link MaxValueOfEquation} {@link LongestContiguousSubarrayWithValidDiff} {@link ConstrainedSubsequenceSum}
     */
    public int[] maxSlidingWindowPushIndices(int[] nums, int k) {
        ArrayDeque<Integer> queue = new ArrayDeque<>(); //store the index, not the number to easily track whether that index has fallen out of window
        for (int i = 0; i < k; i++) { //for the first k elements
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                //remove number smaller than current element, duplicates are also pushed to the queue
                queue.pollLast();
            }
            queue.add(i);
        }
        int[] res = new int[nums.length - k + 1]; //target result
        res[0] = nums[queue.peekFirst()]; //result of the first window
        int resultIndex = 1;
        for (int i = k; i < nums.length; i++) {
            if (queue.peekFirst() + k - 1 < i) { //if the index at the start of the queue has fallen out of the window
                queue.pollFirst();
            }
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) { //remove all elements smaller than current element
                queue.pollLast();
            }
            queue.add(i);
            res[resultIndex++] = nums[queue.peekFirst()]; //index at the head of the queue denotes the result for this window
        }
        return res;
    }

    /**
     * Approach: Push actual numbers into the queue instead of just indices, wanted to make sure logic of pushing numbers in the queue
     * used in {@link ConstrainedSubsequenceSum} works
     */
    public int[] maxSlidingWindowPushNumbers(int[] nums, int k) {
        ArrayDeque<Integer> queue = new ArrayDeque<>(); //store the number
        for (int i = 0; i < k; i++) { //for the first k elements
            while (!queue.isEmpty() && queue.peekLast() < nums[i]) {
                //remove number smaller than current element
                queue.pollLast();
            }
            queue.add(nums[i]);
        }
        int[] res = new int[nums.length - k + 1]; //target result
        res[0] = queue.peekFirst(); //result of the first window
        int resultIndex = 1;
        for (int i = k; i < nums.length; i++) {
            if (queue.peekFirst() == nums[i - k]) { //if the number at the front of the queue matches the number present at the front of the window, remove it
                queue.pollFirst();
            }
            while (!queue.isEmpty() && queue.peekLast() < nums[i]) { //remove all elements smaller than current element
                queue.pollLast();
            }
            queue.add(nums[i]);
            res[resultIndex++] = queue.peekFirst(); //number at the head of the queue denotes the result for this window
        }
        return res;
    }
}
