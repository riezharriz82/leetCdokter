import java.util.ArrayDeque;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/
 * <p>
 * Given an array of integers nums and an integer limit, return the size of the longest non-empty subarray such that the
 * absolute difference between any two elements of this subarray is less than or equal to limit.
 * <p>
 * Input: nums = [10,1,2,4,7,2], limit = 5
 * Output: 4
 * Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute diff is |2-7| = 5 <= 5.
 */
public class LongestContiguousSubarrayWithValidDiff {

    /**
     * Approach: Sliding window to track min and max number, linear time complexity using deque
     * Keep polling from the queue if this number is of no use ie. in case of minQueue, if the number is smaller than the last element of queue
     * In case of maxQueue, if the number is greater than the last number of the queue
     * <p>
     * Once elements are inserted in the queue this means the end pointer is fixed, now check whether the diff of min and max lies within limit
     * If not, keep popping elements present at being index until constraints are satisfied.
     * <p>
     * Keep track of the max length of window
     * <p>
     * {@link MaxValueOfEquation} {@link SlidingWindowMaximum} {@link ConstrainedSubsequenceSum}
     */
    public int longestSubarrayUsingDeque(int[] nums, int limit) {
        ArrayDeque<Integer> minSlidingQueue = new ArrayDeque<>();
        ArrayDeque<Integer> maxSlidingQueue = new ArrayDeque<>();
        int begin = 0, res = 0;
        for (int end = 0; end < nums.length; end++) {
            while (!minSlidingQueue.isEmpty() && minSlidingQueue.peekLast() > nums[end]) {
                minSlidingQueue.pollLast(); //min queue
            }
            while (!maxSlidingQueue.isEmpty() && maxSlidingQueue.peekLast() < nums[end]) {
                maxSlidingQueue.pollLast(); //max queue
            }
            minSlidingQueue.add(nums[end]);
            maxSlidingQueue.add(nums[end]);
            while (!minSlidingQueue.isEmpty() && !maxSlidingQueue.isEmpty() && Math.abs(minSlidingQueue.peekFirst() - maxSlidingQueue.peekFirst()) > limit) {
                //compare the begin element with min and max window, if present, remove it, so that min and max gets updated.
                if (nums[begin] == minSlidingQueue.peekFirst()) {
                    minSlidingQueue.pollFirst();
                }
                if (nums[begin] == maxSlidingQueue.peekFirst()) {
                    maxSlidingQueue.pollFirst();
                }
                begin++;
            }
            res = Math.max(res, end - begin + 1);
        }
        return res;
    }

    /**
     * nLogN code but simple to implement
     * Keep track of min and max of sliding window using treeMap. Why no treeSet? Because input can contain duplicate.
     * Increase the window only if diff of current element with min and max lies within limit
     * else start popping out elements from window
     */
    public int longestSubarray(int[] nums, int limit) {
        int begin = 0, end = 0, maxLength = 0, curLength = 0;
        //Value is the count of the element as duplicate exists
        TreeMap<Integer, Integer> map = new TreeMap<>();
        while (end < nums.length) {
            map.put(nums[end], map.getOrDefault(nums[end], 0) + 1);
            //standard sliding window template for finding longest valid substring
            //keep increasing the window until the window becomes invalid
            //then decrement the window until it becomes valid again
            //too much fun
            while (!map.isEmpty() && Math.abs(map.lastKey() - map.firstKey()) > limit) {
                map.put(nums[begin], map.get(nums[begin]) - 1);
                map.remove(nums[begin], 0);
                begin++;
            }
            maxLength = Math.max(maxLength, end - begin + 1);
            end++;
        }
        return maxLength;
    }
}
