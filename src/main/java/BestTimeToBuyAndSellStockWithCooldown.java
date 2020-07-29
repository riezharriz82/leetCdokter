/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 * <p>
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * <p>
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like
 * (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
 * <p>
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
 */
public class BestTimeToBuyAndSellStockWithCooldown {
    public int maxProfitInLinearTime(int[] prices) {
        int n = prices.length;
        if (n == 0) {
            return 0;
        }
        int[] res = new int[n];
        res[0] = 0;
        int maxDiffSoFar = -prices[0];
        for (int i = 1; i < n; i++) {
            if (i == 1) {
                res[i] = Math.max(prices[1] - prices[0], 0);
                maxDiffSoFar = Math.max(maxDiffSoFar, -prices[1]);
            } else {
                res[i] = Math.max(res[i - 1], prices[i] + maxDiffSoFar);
                maxDiffSoFar = Math.max(maxDiffSoFar, res[i - 2] - prices[i]);
            }
        }
        return res[n - 1];
    }

    /**
     * My initial brute force approach, inspired by longest increasing subsequence
     * For each index, we can compare it with previous indices and check if it's greater than the previous indices
     * If yes, we can update the profit of the ith index with the difference in the price + profit before buying the previous index
     * <p>
     * Profit before buying the jth index would be value at result[j-2] since [j-1] would be a cooldown day
     * <p>
     * An optimization to this approach would be to change this condition (prices[i] - prices[j]) + profitBeforeBuyingJ)
     * to prices[i] + (res[j-2] - prices[j])
     * If we keep track of the largest value of (res[j-2] - prices[j]) we can easily calculate profit[i] without
     * looping through all the previous indices
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0) {
            return 0;
        }
        int[] res = new int[n];
        res[0] = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) { //compare it with all previous days
                if (prices[j] < prices[i]) {
                    int profitBeforeBuyingJ = 0;
                    if (j - 2 > 0) {
                        profitBeforeBuyingJ = res[j - 2];
                    }
                    res[i] = Math.max(res[i], (prices[i] - prices[j]) + profitBeforeBuyingJ);
                }
            }
            res[i] = Math.max(res[i], res[i - 1]); //critical to update it with max profit so far, if this day does not increases the profit
        }
        return res[n - 1];
    }
}
