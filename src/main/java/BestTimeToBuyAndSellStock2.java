/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 * <p>
 * Say you have an array prices for which the ith element is the price of a given stock on day i.
 * <p>
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
 * <p>
 * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
 * <p>
 * Input: [7,1,5,3,6,4]
 * Output: 7
 * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 * Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
 * <p>
 * Input: [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
 * engaging multiple transactions at the same time. You must sell before buying again.
 * <p>
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 * <p>
 * Constraints:
 * 1 <= prices.length <= 3 * 10 ^ 4
 * 0 <= prices[i] <= 10 ^ 4
 */
public class BestTimeToBuyAndSellStock2 {
    /**
     * dp[i] = prices[i] + Max (dp[j] - prices[j])
     * dp[j] - prices[j] needs to be max for all the previous indexes, we can keep track of it after updating dp[i]
     * {@link BestTimeToBuyAndSellStockWithCooldown} has similar optimization
     */
    public int maxProfitInLinear(int[] prices) {
        int n = prices.length;
        int[] dp = new int[n];
        dp[0] = 0;
        int maxOffset = dp[0] - prices[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], prices[i] + maxOffset);
            maxOffset = Math.max(maxOffset, dp[i] - prices[i]);
        }
        return dp[n - 1];
    }

    public int maxProfitInNSquare(int[] prices) {
        int n = prices.length;
        int[] dp = new int[n];
        dp[0] = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (prices[i] > prices[j]) {
                    dp[i] = Math.max(dp[i], prices[i] - prices[j] + dp[j]);
                }
            }
            dp[i] = Math.max(dp[i - 1], dp[i]);
        }
        return dp[n - 1];
    }
}
