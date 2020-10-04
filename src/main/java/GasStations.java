/**
 * https://leetcode.com/problems/gas-station/
 * <p>
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 * <p>
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1).
 * You begin the journey with an empty tank at one of the gas stations.
 * <p>
 * Return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1.
 * <p>
 * Input:
 * gas  = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 * <p>
 * Output: 3
 * Explanation:
 * Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 * Travel to station 4. Your tank = 4 - 1 + 5 = 8
 * Travel to station 0. Your tank = 8 - 2 + 1 = 7
 * Travel to station 1. Your tank = 7 - 3 + 2 = 6
 * Travel to station 2. Your tank = 6 - 4 + 3 = 5
 * Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
 * Therefore, return 3 as the starting index.
 */
public class GasStations {
    /**
     * n^2 code is trivial, my first accepted solution was n^2, start at all indices that have positive diff (gas > cost) and find if circular sum >= 0
     * short circuit if intermediate sum < 0
     * <p>
     * Linear code is based on greedy solution, and similar to {@link JumpGame}
     * As ericto mentioned earlier that yes/no type of problems can be solved via greedy and this problem falls into yes/no category
     * <p>
     * At each point check the current tank, if tank < 0, you can't reach that index from the start index (not 0th index)
     * reset the start to the next index and repeat the process
     * <p>
     * Since the problem statement guarantees that the solution would be unique, greedy works here.
     *
     * Awesome explanation video: https://www.youtube.com/watch?v=nTKdYm_5-ZY&list=PLupD_xFct8mETlGFlLVrwbLwcxczbgWRM&index=8
     * Keep track of surplus and deficit at each index, any node with surplus < deficit can't be starting node.
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0, totalCost = 0;
        int n = gas.length;
        for (int i = 0; i < n; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
        }
        if (totalGas < totalCost) { //can't reach from any point
            return -1;
        }
        int tank = 0, start = 0;
        for (int i = 0; i < n; i++) {
            tank += (gas[i] - cost[i]);
            if (tank < 0) {
                tank = 0;
                start = i + 1;
            }
        }
        return start;
    }
}
