package alternate;

/**
 * https://www.geeksforgeeks.org/number-of-ways-to-pair-people/
 * <p>
 * Given that there are p people in a party. Each person can either join dance as a single individual or as a pair with any other.
 * The task is to find the number of different ways in which p people can join the dance.
 * <p>
 * Input : p = 3
 * Output : 4
 * <p>
 * Let the three people be P1, P2 and P3
 * Different ways are: {P1, P2, P3}, {{P1, P2}, P3},
 * {{P1, P3}, P2} and {{P2, P3}, P1}.
 * <p>
 * Input : p = 2
 * Output : 2
 * The groups are: {P1, P2} and {{P1, P2}}.
 */
public class NumberOfWaysToPairPeople {
    /**
     * For each person there is two choices, either get paired or be alone
     * <p>
     * If alone, there is (n-1) people remaining, solution for which can be retrieved from bottom up DP
     * <p>
     * If paired up, there will be (n-2) people remaining, but there are (n-1) people to be paired up with, so (n-1)*dp[n-2]
     * <p>
     * To sum up dp[i] = 1 * dp[i-1] + (n-1) * dp[i-2]
     */
    public int findWaysToPair(int p) {
        // To store count of number of ways.
        int[] dp = new int[p + 1];

        dp[1] = 1;
        dp[2] = 2;

        // Using the recurrence defined find
        // count for different values of p.
        for (int i = 3; i <= p; i++) {
            dp[i] = dp[i - 1] + (i - 1) * dp[i - 2];
        }

        return dp[p];
    }
}
