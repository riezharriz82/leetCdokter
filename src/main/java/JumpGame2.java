import java.util.Arrays;

/**
 * https://leetcode.com/problems/jump-game-ii/
 * <p>
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * <p>
 * Each element in the array represents your maximum jump length at that position.
 * <p>
 * Your goal is to reach the last index in the minimum number of jumps.
 * <p>
 * Input: [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2.
 * Jump 1 step from index 0 to 1, then 3 steps to the last index.
 */
public class JumpGame2 {
    /**
     * Approach: Keep track of the min number of jumps required to reach a specific index i.e. x
     * From that index, update the no of jumps required to reach all the reachable indices to x + 1
     * <p>
     * Similar question was asked in my Uber coding interview
     * <p>
     * Similar to {@link JumpGame}
     */
    public int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            int curJumps = dp[i];
            if (curJumps != Integer.MAX_VALUE) {
                for (int low = i; low < n && low <= i + nums[i]; low++) { //try to update the jumps required to reach all the reachable indices
                    dp[low] = Math.min(dp[low], curJumps + 1);
                }
            }
        }
        return dp[n - 1] == Integer.MAX_VALUE ? 0 : dp[n - 1];
    }

    /**
     * Approach: Visualize the problem as BFS to find min steps required to reach end.
     * Keep track of the end index of the current level. Once that is reached, increment the level count and update the end of the next level.
     * for e.g. 2,3,1,1,4
     * 2 is root node
     * 3,1 is next level
     * 1, 4 is next level
     * <p>
     * When at first 1, update the end of the next level to the highest index currently reachable i.e. 4
     */
    public int jumpGreedy(int[] nums) {
        int levelEnd = 0, farthestReachableIndex = 0;
        int levels = 0;
        for (int i = 0; i < nums.length && i <= farthestReachableIndex; i++) {
            farthestReachableIndex = Math.max(farthestReachableIndex, i + nums[i]);
            if (i == nums.length - 1) { //if last index is reached, return the current level count
                return levels;
            }
            if (i == levelEnd) { //this level is done
                levels++;
                levelEnd = farthestReachableIndex; //update the end index of the next level
            }
        }
        return 0; //last index isn't reachable
    }
}
