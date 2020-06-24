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
public class ChangeOnlyOneElementToSortArray {
    public boolean checkPossibility(int[] nums) {
        int misMatch = -1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                if (misMatch == -1) {
                    misMatch = i;
                } else {
                    return false;
                }
            }
        }
        if (misMatch == -1) {
            return true;
        } else if (misMatch == 1 || misMatch == nums.length - 1) {
            return true;
        } else // [-1,4,2,3]
            if (nums[misMatch - 2] <= nums[misMatch]) {
                return true;
            } else return nums[misMatch - 1] <= nums[misMatch + 1];
    }
}
