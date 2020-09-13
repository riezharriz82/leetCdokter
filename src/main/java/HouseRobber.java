/**
 * https://leetcode.com/problems/house-robber/
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that
 * adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * <p>
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of
 * money you can rob tonight without alerting the police.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 */
public class HouseRobber {
    /**
     * Approach: Every house has two options, either exclude it or include it.
     * If included, the previous house has to be excluded
     * If excluded, the previous house can either be included or excluded.
     */
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        } else {
            int[] include = new int[nums.length];
            int[] exclude = new int[nums.length];
            include[0] = nums[0];
            exclude[0] = 0;
            for (int i = 1; i < nums.length; i++) {
                include[i] = exclude[i - 1] + nums[i];
                exclude[i] = Math.max(include[i - 1], exclude[i - 1]);
            }
            return Math.max(include[nums.length - 1], exclude[nums.length - 1]);
        }
    }
}
