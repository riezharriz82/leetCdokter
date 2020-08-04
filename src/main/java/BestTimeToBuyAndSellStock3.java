/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 * <p>
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * <p>
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 * <p>
 * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
 * <p>
 * Input: [3,3,5,0,0,3,1,4]
 * Output: 6
 * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 * Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
 */
public class BestTimeToBuyAndSellStock3 {

    public int maxProfitInLinearTime(int[] prices) {
        int n = prices.length;
        if (n == 0) {
            return 0;
        }
        int[] oneTransaction = new int[n];
        int[] twoTransaction = new int[n];
        oneTransaction[0] = 0;
        twoTransaction[0] = 0;
        int minPrice = prices[0];
        int maxOffset = oneTransaction[0] - prices[0];
        for (int i = 1; i < n; i++) {
            oneTransaction[i] = Math.max(oneTransaction[i - 1], prices[i] - minPrice);
            twoTransaction[i] = Math.max(twoTransaction[i - 1], prices[i] + maxOffset);
            maxOffset = Math.max(maxOffset, oneTransaction[i] - prices[i]);
            minPrice = Math.min(minPrice, prices[i]);
        }
        return Math.max(oneTransaction[n - 1], twoTransaction[n - 1]);
    }

    /**
     * Approach: Every day has two option, either continue the previous transaction or start a new transaction at this day
     * When we want to continue the previous transaction, we need to add the result of previous transaction with this day's transaction
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0) {
            return 0;
        }
        int[] newTransaction = new int[n];
        int[] makeAnotherTransaction = new int[n];
        newTransaction[0] = 0;
        makeAnotherTransaction[0] = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (prices[i] > prices[j]) {
                    newTransaction[i] = Math.max(newTransaction[i], prices[i] - prices[j]); //Keep track of the min price so far to do it in linear time
                    //add previous transaction with current transaction
                    makeAnotherTransaction[i] = Math.max(makeAnotherTransaction[i], newTransaction[j] + (prices[i] - prices[j]));
                    //keep track of max value of newTransaction[j] - prices[j] to do it in linear time
                }
            }
            //carry forward the previous result, if this day didn't give better result
            newTransaction[i] = Math.max(newTransaction[i - 1], newTransaction[i]);
            makeAnotherTransaction[i] = Math.max(makeAnotherTransaction[i - 1], makeAnotherTransaction[i]);
        }
        return Math.max(newTransaction[n - 1], makeAnotherTransaction[n - 1]);
    }
}
