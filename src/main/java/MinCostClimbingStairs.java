/**
 * https://leetcode.com/problems/min-cost-climbing-stairs/
 * <p>
 * On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).
 * <p>
 * Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of the floor,
 * and you can either start from the step with index 0, or the step with index 1.
 * <p>
 * Input: cost = [10, 15, 20]
 * Output: 15
 * Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
 * <p>
 * Example 2:
 * Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
 * Output: 6
 * Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].
 */
public class MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;
        int[] dp = new int[len];
        dp[0] = cost[0];
        dp[1] = cost[1];

        for (int i = 2; i < len; i++) {
            //min cost to reach the current stairs would be the cost to reach (i - 2)th or (i - 1)th stair plus the current stair cost
            dp[i] = Math.min(dp[i - 2], dp[i - 1]) + cost[i];
        }

        //cost to reach the top stair is 0, so repeat the same thing as above but without adding cost
        return Math.min(dp[len - 2], dp[len - 1]);
    }
}
