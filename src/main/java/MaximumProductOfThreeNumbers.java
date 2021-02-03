import java.util.Arrays;

/**
 * https://leetcode.com/problems/maximum-product-of-three-numbers/
 * <p>
 * Given an integer array nums, find three numbers whose product is maximum and return the maximum product.
 * <p>
 * Input: nums = [1,2,3]
 * Output: 6
 * <p>
 * Input: nums = [1,2,3,4]
 * Output: 24
 * <p>
 * Input: nums = [-1,-2,-3]
 * Output: -6
 * <p>
 * Constraints:
 * 3 <= nums.length <= 104
 * -1000 <= nums[i] <= 1000
 */
public class MaximumProductOfThreeNumbers {
    /**
     * Approach: If all the numbers are positive, then the result would be the product of largest 3 numbers, however if the array
     * contains negative numbers as well and two smallest negative numbers when multiplied can result a larger positive value, we can multiply
     * it with the largest positive value and see if it results in a even greater value
     * e.g [-100,-98,-1,2,3,4]
     * <p>
     * We can avoid sorting by keeping track of 5 variables, 3 variables for tracking the largest 3 numbers. See {@link DiameterOfNAryTree}
     * and 2 variables for tracking the smallest 2 numbers
     * <p>
     * {@link MaximumProductSubarray} related problem
     */
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        return Math.max(nums[0] * nums[1] * nums[n - 1], nums[n - 1] * nums[n - 2] * nums[n - 3]);
    }
}
