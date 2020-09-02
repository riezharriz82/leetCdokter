import java.util.Arrays;

/**
 * https://leetcode.com/problems/coin-change-2/
 * <p>
 * You are given coins of different denominations and a total amount of money. Write a function to compute the number of combinations that make up that amount.
 * You may assume that you have infinite number of each kind of coin.
 * <p>
 * Input: amount = 5, coins = [1, 2, 5]
 * Output: 4
 * Explanation: there are four ways to make up the amount:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 */
public class CoinChange2 {
    /**
     * If I consider current amount as 10 and current coin as 4, if I can reach 6 in 5 different ways, then I could reach 10 also in +5 different ways
     * Similar to {@link KnightDialer} problem
     */
    public int changeWithBottomUpDP(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                //increment by the no of ways to make (curAmount - coinValue)
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }

    /**
     * Recursion with memoization
     * This is not top down DP
     */
    public int changeWithMemoization(int amount, int[] coins) {
        if (coins.length == 0) {
            return amount == 0 ? 1 : 0;
        }
        Arrays.sort(coins); //sorting will help in pruning
        int[][] memo = new int[amount][coins.length];
        for (int[] ints : memo) {
            Arrays.fill(ints, -1); //filling it up with -1 is required because 0 is a valid answer
            //so if I fill it up with 0, need to recompute again
        }
        return recur(coins, 0, amount, 0, memo);
    }

    private int recur(int[] coins, int curAmount, int maxAmount, int index, int[][] memo) {
        if (curAmount == maxAmount) {
            return 1; //base condition
        }
        if (memo[curAmount][index] != -1) {
            return memo[curAmount][index];
        }
        int result = 0;
        for (int i = index; i < coins.length; i++) {
            if (curAmount + coins[i] > maxAmount) {
                //early exit since coins arrays is sorted, it won't help to continue because even larger coins will be present ahead
                break;
            }
            result += recur(coins, curAmount + coins[i], maxAmount, i, memo); //passing the same index again so that same coin can be repeated
        }
        return memo[curAmount][index] = result;
    }
}
