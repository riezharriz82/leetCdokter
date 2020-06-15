/**
 * https://leetcode.com/problems/maximum-subarray/
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
