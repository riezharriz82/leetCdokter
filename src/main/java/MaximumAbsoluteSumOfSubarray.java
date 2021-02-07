/**
 * https://leetcode.com/problems/maximum-absolute-sum-of-any-subarray/
 * <p>
 * You are given an integer array nums. The absolute sum of a subarray [numsl, numsl+1, ..., numsr-1, numsr] is abs(numsl + numsl+1 + ... + numsr-1 + numsr).
 * <p>
 * Return the maximum absolute sum of any (possibly empty) subarray of nums.
 * <p>
 * Input: nums = [1,-3,2,3,-4]
 * Output: 5
 * Explanation: The subarray [2,3] has absolute sum = abs(2+3) = abs(5) = 5.
 * <p>
 * Input: nums = [2,-5,1,-4,3,-2]
 * Output: 8
 * Explanation: The subarray [-5,1,-4] has absolute sum = abs(-5+1-4) = abs(-8) = 8.
 * <p>
 * Constraints:
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * Accepted
 */
public class MaximumAbsoluteSumOfSubarray {
    /**
     * Approach: Tricky Kadane Algorithm, max absolute sum will be max of (max subarray sum, -min subarray sum)
     * Use standard kadane algo to find max subarray sum, to calculate min subarray
     * <p>
     * {@link MaximumSumCircularSubarray} {@link SubarrayProductLessThanK} {@link SubarraysWithKDifferentIntegers}
     */
    public int maxAbsoluteSum(int[] nums) {
        int curSum = 0, maxSum = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            curSum += nums[i];
            if (curSum < 0) {
                curSum = 0;
            }
            maxSum = Math.max(maxSum, curSum);
            nums[i] *= -1;
        }
        //now maxSum holds the maximum positive subarray sum, since we already negated the nums[] array, now if we run
        //kadane algo, we will find the absolute value of min subarray sum
        curSum = 0;
        for (int i = 0; i < n; i++) {
            curSum += nums[i];
            if (curSum < 0) {
                curSum = 0;
            }
            maxSum = Math.max(maxSum, curSum);
        }
        return maxSum;
    }
}
