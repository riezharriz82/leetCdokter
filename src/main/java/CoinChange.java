import java.util.Arrays;

/**
 * https://leetcode.com/problems/coin-change/
 * <p>
 * You are given coins of different denominations and a total amount of money amount.
 * Write a function to compute the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 * <p>
 * Input: coins = [1, 2, 5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * <p>
 * Input: coins = [2], amount = 3
 * Output: -1
 */
public class CoinChange {
    /**
     * If current coin is 10 and you can make 5 from min 3 coins, you can make 15 from 3+1 coins
     */
    public int coinChangeBottomUp(int[] coins, int amount) {
        int[] dp = new int[amount + 1]; //dp[i] is the min no of coins required to make sum = i
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                if (dp[i - coin] != Integer.MAX_VALUE) {
                    //if i is the amount, minimum no of coins required to reach (i - coin) amount is dp[i-coin]
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    /**
     * Top down recursion with memoization
     * Care must be taken to actually recurse in a top down manner, in my initial implementation I started from 0 till I reach the max amount
     * but that is not top down recursion, because you can't properly reuse the intermediate results, because for input {1,2,5} , 11
     * dp[11] == 11 after first iteration which is not the correct definition of top down dp
     * <p>
     * Instead the recursion should start from 11 and go deep down till 0
     */
    public int coinChangeTopDown(int[] coins, int amount) {
        Arrays.sort(coins);
        int[] memoization = new int[amount + 1];
        int result = recur(coins, amount, memoization);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private int recur(int[] coins, int currentAmount, int[] memoization) {
        if (currentAmount == 0) {
            return 0;
        }
        if (memoization[currentAmount] > 0) {
            return memoization[currentAmount];
        }
        int result = Integer.MAX_VALUE;
        for (int coin : coins) {
            if ((long) currentAmount - coin < 0) { //to handle INT_MIN
                break;
            }
            int recur = recur(coins, currentAmount - coin, memoization);
            if (recur != Integer.MAX_VALUE) {
                //if result is valid
                result = Math.min(result, 1 + recur);
            }
        }
        return memoization[currentAmount] = result;
    }
}
