/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
 * <p>
 * You are given an array prices where prices[i] is the price of a given stock on the ith day, and an integer fee representing a transaction fee.
 * <p>
 * Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.
 * <p>
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 * <p>
 * Input: prices = [1,3,2,8,4,9], fee = 2
 * Output: 8
 * Explanation: The maximum profit can be achieved by:
 * - Buying at prices[0] = 1
 * - Selling at prices[3] = 8
 * - Buying at prices[4] = 4
 * - Selling at prices[5] = 9
 * The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 * <p>
 * Input: prices = [1,3,7,5,10,3], fee = 3
 * Output: 6
 * <p>
 * Constraints:
 * 1 < prices.length <= 5 * 104
 * 0 < prices[i], fee < 5 * 104
 */
public class BestTimeToBuyAndSellStockWithTransactionFee {
    /**
     * Approach: DP, Keep track of the maxOffset found so far and use that to find max profit at each index
     * <p>
     * {@link BestTimeToBuyAndSellStock} {@link BestTimeToBuyAndSellStockWithCooldown}
     */
    public int maxProfitOptimized(int[] prices, int fee) {
        int n = prices.length;
        int[] profit = new int[n];
        int maxOffset = profit[0] - fee - prices[0];
        for (int i = 1; i < n; i++) {
            profit[i] = Math.max(profit[i], (prices[i] + maxOffset));
            profit[i] = Math.max(profit[i], profit[i - 1]);
            maxOffset = Math.max(maxOffset, profit[i] - fee - prices[i]);
        }
        return profit[n - 1];
    }

    public int maxProfitBruteForce(int[] prices, int fee) {
        int n = prices.length;
        int[] profit = new int[n];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (prices[j] < prices[i]) {
                    //instead of looping over all the past indices and finding the index which leads to the max profit
                    //we can keep track of max value of (profit[j] - prices[j] - fee) and directly compute the max profit in O(1) time
                    profit[i] = Math.max(profit[i], prices[i] + (profit[j] - prices[j] - fee));
                }
            }
            //remember to carry forward the past profit if current profit is lower.
            profit[i] = Math.max(profit[i], profit[i - 1]);
        }
        return profit[n - 1];
    }
}
