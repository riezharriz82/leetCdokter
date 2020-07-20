/**
 * https://leetcode.com/problems/product-of-array-except-self/description/
 * <p>
 * Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
 * <p>
 * Input:  [1,2,3,4]
 * Output: [24,12,8,6]
 * Constraint: It's guaranteed that the product of the elements of any prefix or suffix of the array (including the whole array) fits in a 32 bit integer.
 * <p>
 * Note: Please solve it without division and in O(n).
 * <p>
 * Follow up:
 * Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
 */
public class ProductofArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        result[0] = 1; //running product from the left, excluding the current element
        for (int i = 1; i < n; i++) {
            result[i] = (result[i - 1] * nums[i - 1]);
        }
        //instead of creating an explicit array containing the running product from the right, just store it in variable and update the result
        int rightProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= rightProduct;
            rightProduct *= nums[i];
        }

        return result;
    }

    public int[] productExceptSelfUsingExtraSpace(int[] nums) {
        int n = nums.length;
        int[] leftProduct = new int[n]; //running product from the left, including the current element
        leftProduct[0] = nums[0];
        for (int i = 1; i < n; i++) {
            leftProduct[i] = (leftProduct[i - 1] * nums[i]);
        }
        int[] rightProduct = new int[n]; //running product from the right, including the current element
        rightProduct[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightProduct[i] = (rightProduct[i + 1] * nums[i]);
        }

        int[] res = new int[n];
        res[0] = rightProduct[1];
        res[n - 1] = leftProduct[n - 2];
        for (int i = 1; i < n - 1; i++) {
            res[i] = leftProduct[i - 1] * rightProduct[i + 1]; //left product of the adjacent left element * right product of the adjacent right element
        }
        return res;
    }
}
