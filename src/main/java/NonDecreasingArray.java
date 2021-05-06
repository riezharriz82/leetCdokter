/**
 * https://leetcode.com/problems/non-decreasing-array/
 * <p>
 * Given an array nums with n integers, your task is to check if it could become non-decreasing by modifying at most 1 element.
 * <p>
 * We define an array is non-decreasing if nums[i] <= nums[i + 1] holds for every i (0-based) such that (0 <= i <= n - 2).
 * <p>
 * Input: nums = [4,2,3]
 * Output: true
 * Explanation: You could modify the first 4 to 1 to get a non-decreasing array.
 * <p>
 * Input: nums = [4,2,1]
 * Output: false
 * Explanation: You can't get a non-decreasing array by modify at most one element.
 */
public class NonDecreasingArray {
    /**
     * Greedy approach: When we encounter a mismatched pair i.e. nums[i-1] > nums[i] there are two options to correct it
     * 1. Change nums[i-1] to nums[i] e.g {7,10,8,9,12} -> {7,8,8,11,12} Why not nums[i] to nums[i-1] because increasing it might cause more conflict for the next range.
     * 2. But if nums[i-2] > nums[i] previous step won't work e.g {8,10,7} -> {8,7,10} would be invalid, so we need to change nums[i] to nums[i-1] e.g {8,10,10}
     * <p>
     * {@link SmallestRange2} related tricky problem
     */
    public boolean checkPossibility(int[] nums) {
        int misMatch = -1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                if (misMatch == -1) {
                    misMatch = i;
                    if (i - 2 >= 0 && nums[i - 2] > nums[i]) {
                        nums[i] = nums[i - 1]; //increase the current number only if it can't be decreased
                    } else {
                        nums[i - 1] = nums[i]; //decrease the previous number
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
