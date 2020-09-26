import java.util.Arrays;

/**
 * https://leetcode.com/problems/combination-sum-iv/
 * <p>
 * Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.
 * <p>
 * nums = [1, 2, 3]
 * target = 4
 * <p>
 * The possible combination ways are:
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * <p>
 * Note that different sequences are counted as different combinations.
 * <p>
 * Therefore the output is 7.
 */
public class CombinationSum4 {
    /**
     * Approach: First write recursive solution, then move on to memoization
     * Think about what information of previous state is useful i.e. if I know the total no of ways to make sum 10 and current num is 2, i can use it to make sum 12
     * <p>
     * {@link CoinChange2} is similar but it asks for combinations and this problem asks for permutations
     */
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        Arrays.fill(dp, -1);
        return recur(nums, target, dp); //there are two approaches, either start from 0 and reach target or start from target and reach 0
        //former is not suitable for memoization because we can't reuse intermediate results
    }

    private int recur(int[] nums, int target, int[] dp) {
        if (target < 0) {
            return 0;
        } else if (target == 0) {
            return 1;
        } else if (dp[target] != -1) {
            return dp[target];
        } else {
            int res = 0;
            for (int num : nums) {
                res += recur(nums, target - num, dp);
            }
            return dp[target] = res;
        }
    }
}
