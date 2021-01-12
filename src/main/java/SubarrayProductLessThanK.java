/**
 * https://leetcode.com/problems/subarray-product-less-than-k/
 * <p>
 * Your are given an array of positive integers nums.
 * <p>
 * Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than k.
 * <p>
 * Input: nums = [10, 5, 2, 6], k = 100
 * Output: 8
 * Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
 * Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
 */
public class SubarrayProductLessThanK {
    /**
     * Approach: Sliding window, keep increasing the window until current product < k. If not, decrement the window from the start
     * Only trick is how to count the number of subarrays ? if it would have been length, it would be trivial.
     * <p>
     * To count the no of subarrays after increasing or decreasing the window use (end-start+1)
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) {
            return 0;
        }
        int begin = 0, end = 0, res = 0;
        long curProduct = 1;
        while (end < nums.length) {
            curProduct *= nums[end];
            while (curProduct >= k) {
                curProduct /= nums[begin];
                begin++;
            }
            //explanation behind this sorcery is that consider begin as 0 and keep on increasing the end, each end can now
            //start its own (end + 1) subarrays, now if begin != 0, we have to subtract begin to discount subarrays < begin
            res += (end - begin + 1);
            end++;
        }
        return res;
    }
}
