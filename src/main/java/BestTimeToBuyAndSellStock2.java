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
