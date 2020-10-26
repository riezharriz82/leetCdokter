/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 * <p>
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * <p>
 * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 * <p>
 * Note that you cannot sell a stock before you buy one.
 * <p>
 * Input: [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Not 7-1 = 6, as selling price needs to be larger than buying price.
 */
public class BestTimeToBuyAndSellStock {
    /**
     * Approach: Keep track of the largest number from the right since we are allowed to do only one transaction, it must be
     * done between largest number on the right and the current number
     * <p>
     * Or consider the smallest number from the left and make a transaction today
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 0 || prices.length == 1) {
            return 0;
        }
        int n = prices.length;
        int curMax = prices[n - 1], maxProfit = 0;
        for (int i = n - 2; i >= 0; i--) {
            if (prices[i] > curMax) {
                curMax = prices[i];
            } else {
                maxProfit = Math.max(maxProfit, curMax - prices[i]);
            }
        }
        return maxProfit;
    }
}