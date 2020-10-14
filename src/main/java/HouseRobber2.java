import java.util.Arrays;

/**
 * https://leetcode.com/problems/house-robber-ii/
 * <p>
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed.
 * All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one.
 * Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent
 * houses were broken into on the same night.
 * <p>
 * Given a list of non-negative integers nums representing the amount of money of each house,
 * return the maximum amount of money you can rob tonight without alerting the police.
 * <p>
 * Input: nums = [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
 * Example 2:
 * <p>
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 */
public class HouseRobber2 {
    /**
     * Approach: I was able to solve it using recursion but that timed out, memoizing it gave WA because of inconsistent states being memoized.
     * Trick for to build a recursive solution was to consider that first and last indices can never coexist together,
     * so if you have to pick first, you have to skip last or if you have to pick last you have to skip first.
     * Need to consider the max of both the options.
     * <p>
     * Fuck man, how will I crack google?
     */
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int[] memoized = new int[n];
        Arrays.fill(memoized, -1);
        int chooseFirstHouse = recur(nums, 0, n - 2, memoized);
        Arrays.fill(memoized, -1);
        int chooseLastHouse = recur(nums, 1, n - 1, memoized);
        return Math.max(chooseFirstHouse, chooseLastHouse);
    }

    private int recur(int[] nums, int low, int index, int[] memoized) {
        if (index < low) {
            return 0;
        }
        if (memoized[index] != 0) {
            return memoized[index];
        }
        int robIndex = nums[index] + recur(nums, low, index - 2, memoized); //skip next index as it can't contribute anything to result
        int skipIndex = recur(nums, low, index - 1, memoized);
        return memoized[index] = Math.max(robIndex, skipIndex);
    }
}
