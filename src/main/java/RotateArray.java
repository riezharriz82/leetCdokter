/**
 * https://leetcode.com/problems/rotate-array/
 */
public class RotateArray {
    /**
     * Original List                   : 1 2 3 4 5 6 7
     * After reversing all numbers     : 7 6 5 4 3 2 1
     * After reversing first k numbers : 5 6 7 4 3 2 1
     * After revering last n-k numbers : 5 6 7 1 2 3 4 --> Result
     */
    public void inplaceUsingReverse(int[] nums, int k) {
        reverse(nums, 0, nums.length);
        k %= nums.length;
        reverse(nums, 0, k);
        reverse(nums, k, nums.length);
    }

    private void reverse(int[] nums, int start, int end) {
        //alternate way of reversing would be to increment start and decrement end after every swap
        for (int i = start; i < start + (end - start) / 2; i++) {
            int temp = nums[i];
            nums[i] = nums[end - 1 - i + start];
            nums[end - 1 - i + start] = temp;
        }
    }

    public void rotate(int[] nums, int k) {
        if (k != 0) {
            int[] res = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                res[(i + k) % nums.length] = nums[i];
            }
            System.arraycopy(res, 0, nums, 0, nums.length);
        }
    }
}
