/**
 * https://leetcode.com/problems/house-robber/
 */
public class HouseRobber {
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
