import java.util.Arrays;
import java.util.HashMap;

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
 * <p>
 * Constraints:
 * 2 * n == costs.length
 * 2 <= costs.length <= 100
 * costs.length is even.
 * 1 <= aCosti, bCosti <= 1000
 */
public class TwoCityScheduling {
    /**
     * Approach: Similar to knapsack problem, every person has two options, go to city a or city b
     * In knapsack the constraint is weight, here the constraint is count of people in either of the city
     * <p>
     * It works because of low constraints of n=100 (100*50*50=2.5*10^5)
     */
    public int twoCitySchedCostRecursive(int[][] costs) {
        HashMap<String, Integer> memoized = new HashMap<>();
        return recur(costs, 0, 0, 0, memoized);
    }

    private int recur(int[][] costs, int index, int a_count, int b_count, HashMap<String, Integer> memoized) {
        if (index == costs.length) {
            return 0;
        }
        String key = index + ":" + a_count + ":" + b_count;
        if (memoized.containsKey(key)) {
            return memoized.get(key);
        }
        int goToCityA = Integer.MAX_VALUE, goToCityB = Integer.MAX_VALUE;
        if (a_count < costs.length / 2) { //if it's possible to send this person to city a
            goToCityA = costs[index][0] + recur(costs, index + 1, a_count + 1, b_count, memoized);
        }
        if (b_count < costs.length / 2) { //if it's possible to send this person to city b
            goToCityB = costs[index][1] + recur(costs, index + 1, a_count, b_count + 1, memoized);
        }
        int result = Math.min(goToCityA, goToCityB);
        memoized.put(key, result);
        return result;
    }

    /**
     * Approach: Greedy, first send all of the people to city a, then see which people can get the highest refund if sent to city b
     * Send top half of the people sorted by refund to city b
     */
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
