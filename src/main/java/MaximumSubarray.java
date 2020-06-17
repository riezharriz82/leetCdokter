/**
 * https://leetcode.com/problems/maximum-subarray/
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 * <p>
 * Example:
 * <p>
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 */
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int curSum = 0, maxSum = 0, cur_Max = Integer.MIN_VALUE;
        boolean isPositiveNumberPresent = false;
        for (int value : nums) {
            if (value >= 0) {
                isPositiveNumberPresent = true;
                break;
            } else {
                if (value > cur_Max) {
                    cur_Max = value;
                }
            }
        }
        if (!isPositiveNumberPresent) {
            return cur_Max;
        }

        for (int num : nums) {
            curSum += num;
            if (curSum > maxSum) {
                maxSum = curSum;
            } else if (curSum < 0) {
                curSum = 0;
            }
        }
        return maxSum;
    }
}
