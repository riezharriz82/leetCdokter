import java.util.Arrays;

/**
 * https://leetcode.com/problems/two-city-scheduling/
 * <p>
 * There are 2N people a company is planning to interview. The cost of flying the i-th person to city A is costs[i][0],
 * and the cost of flying the i-th person to city B is costs[i][1].
 * <p>
 * Return the minimum cost to fly every person to a city such that exactly N people arrive in each city.
 * <p>
 * Example 1:
 * <p>
 * Input: [[10,20],[30,200],[400,50],[30,20]]
 * <p>
 * Output: 110
 * Explanation:
 * The first person goes to city A for a cost of 10.
 * The second person goes to city A for a cost of 30.
 * The third person goes to city B for a cost of 50.
 * The fourth person goes to city B for a cost of 20.
 * <p>
 * The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people interviewing in each city.
 */
public class TwoCityScheduling {
    public int twoCitySchedCost(int[][] costs) {
        //send all the people to city A
        int cost = 0;
        for (int i = 0; i < costs.length; i++) {
            cost += costs[i][0];
        }

        //find the people which can get the highest refund on their ticket if they are send to city B instead
        int[] refunds = new int[costs.length];
        for (int i = 0; i < costs.length; i++) {
            refunds[i] = costs[i][1] - costs[i][0];
        }
        Arrays.sort(refunds);

        //pick the top n people with the highest refunds and update the total cost
        for (int i = 0; i < refunds.length / 2; i++) {
            cost += refunds[i];
        }
        return cost;
    }
}
