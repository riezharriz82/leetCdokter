/**
 * https://leetcode.com/problems/jump-game/
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * <p>
 * Each element in the array represents your maximum jump length at that position.
 * <p>
 * Determine if you are able to reach the last index.
 * <p>
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 */
public class JumpGame {
    /**
     * Approach: Greedy, Keep track of the maximum index you can reach.
     */
    public boolean canJumpOptimized(int[] nums) {
        int reach = 0;
        for (int i = 0; i <= reach && i < nums.length; i++) { //iterate till the maximum index reachable
            reach = Math.max(reach, nums[i] + i); //magical piece of code
            if (reach >= nums.length - 1) {
                return true;
            }
        }
        return reach >= nums.length - 1;
    }

    /**
     * Classical DP Problem: If an index is reachable, mark all the indices reachable from that index.
     * At the end, check if the last index is reachable
     */
    public boolean canJump(int[] nums) {
        if (nums[0] == 0) {
            return nums.length == 1;
        }
        boolean[] reachable = new boolean[nums.length];
        reachable[0] = true; //first index is now guaranteed to be reachable
        for (int i = 0; i < nums.length; i++) {
            if (reachable[i]) {
                int maxDistance = nums[i]; //mark all the nodes reachable from i
                for (int j = i + 1; j < nums.length && j <= maxDistance + i; j++) {
                    reachable[j] = true;
                }
                if (reachable[nums.length - 1]) { //early exit
                    return true;
                }
            }
        }
        return reachable[nums.length - 1];
    }
}
