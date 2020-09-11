/**
 * https://leetcode.com/problems/maximum-product-subarray/
 * <p>
 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
 * <p>
 * Input: [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * <p>
 * Input: [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */
public class MaximumProductSubarray {
    /**
     * Approach: https://leetcode.com/problems/maximum-product-subarray/discuss/183483/JavaC%2B%2BPython-it-can-be-more-simple
     * <p>
     * The thing to understand is the maximum product subarray can only exist either from the left, including the first element
     * or from the right, including the last element (assuming no zeroes)
     * In case of zeroes, we have to consider it as a new start/end
     * So compute the prefix and suffix subarray product, keep track of the max so far
     */
    public int maxProduct(int[] nums) {
        int n = nums.length, res = Integer.MIN_VALUE, curProduct = 0;
        for (int i = 0; i < n; i++) { //prefix product
            if (curProduct == 0) {
                curProduct = nums[i];
            } else {
                curProduct *= nums[i];
            }
            res = Math.max(res, curProduct);
        }
        curProduct = 0;
        for (int i = n - 1; i >= 0; i--) { //suffix product
            if (curProduct == 0) {
                curProduct = nums[i];
            } else {
                curProduct *= nums[i];
            }
            res = Math.max(res, curProduct);
        }
        return res;
    }
}
