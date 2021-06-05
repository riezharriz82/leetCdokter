/**
 * <pre>
 * https://leetcode.com/problems/maximum-average-subarray-i/
 *
 * You are given an integer array nums consisting of n elements, and an integer k.
 *
 * Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value. Any answer with a calculation error less than 10^-5 will be accepted.
 *
 * Input: nums = [1,12,-5,-6,50,3], k = 4
 * Output: 12.75000
 * Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
 *
 * Input: nums = [5], k = 1
 * Output: 5.00000
 *
 * Constraints:
 * n == nums.length
 * 1 <= k <= n <= 10^5
 * -104 <= nums[i] <= 10^4
 * </pre>
 */
public class MaximumAverageSubarray1 {
    /**
     * Approach: Sliding window, Maintain a sliding window of size k and keep track of largest average found so far.
     * How to find the smallest negative double value: https://stackoverflow.com/questions/2389613/correct-way-to-obtain-the-most-negative-double
     * <p>
     * {@link MinimumWindowSubstring}
     */
    public double findMaxAverage(int[] nums, int k) {
        int curSum = 0;
        double maxAverage = -Double.MAX_VALUE; //or Double.NEGATIVE_INFINITY
        //very interesting to note that Double.MIN_VALUE is actually the smallest positive double value
        for (int i = 0; i < nums.length; i++) {
            if (i < k) {
                curSum += nums[i];
            } else {
                double curAverage = (double) curSum / k;
                maxAverage = Math.max(maxAverage, curAverage);
                curSum += nums[i];
                curSum -= nums[i - k];
            }
        }
        //last window
        double curAverage = ((double) curSum) / k;
        maxAverage = Math.max(maxAverage, curAverage);
        return maxAverage;
    }
}
