/**
 * https://leetcode.com/problems/minimum-size-subarray-sum/
 * <p>
 * Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s.
 * If there isn't one, return 0 instead.
 * <p>
 * Input: s = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: the subarray [4,3] has the minimal length under the problem constraint.
 */
public class MinimumSizeSubarraySum {
    /**
     * Approach: Sliding window, keep increasing the window until the current sum of the window >= target
     * Once such window is reached, keep decreasing the window until the window becomes invalid.
     * Keep track of the min window size.
     * <p>
     * {@link MinimumWindowSubstring} for related problem
     */
    public int minSubArrayLen(int target, int[] nums) {
        int begin = 0, end = 0, minLength = Integer.MAX_VALUE;
        int curSum = 0;
        while (end < nums.length) {
            curSum += nums[end];
            while (curSum >= target) { //once a valid window is reached, keep shortening the window to find a smaller valid subarray
                minLength = Math.min(minLength, end - begin + 1);
                curSum -= nums[begin];
                begin++;
            }
            end++;
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}
