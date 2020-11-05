/**
 * https://leetcode.com/problems/minimum-cost-to-move-chips-to-the-same-position/
 * <p>
 * We have n chips, where the position of the ith chip is position[i].
 * <p>
 * We need to move all the chips to the same position. In one step, we can change the position of the ith chip from position[i] to:
 * <p>
 * position[i] + 2 or position[i] - 2 with cost = 0.
 * position[i] + 1 or position[i] - 1 with cost = 1.
 * Return the minimum cost needed to move all the chips to the same position.
 * <p>
 * Input: position = [1,2,3]
 * Output: 1
 * Explanation: First step: Move the chip at position 3 to position 1 with cost = 0.
 * Second step: Move the chip at position 2 to position 1 with cost = 1.
 * Total cost is 1.
 * <p>
 * Input: position = [2,2,2,3,3]
 * Output: 2
 * Explanation: We can move the two chips at position 3 to position 2. Each move has cost = 1. The total cost = 2.
 * Example 3:
 * <p>
 * Input: position = [1,1000000000]
 * Output: 1
 */
public class MinCostToMoveChipsToTheSamePosition {
    /**
     * Approach: Greedy, n^2, min cost can be done if one of the initial state is in the target state, so choose all of the initial states
     * as the target state and find the min cost to bring the remaining chips to that state
     * <p>
     * If the chips are separated by odd distance, cost is 1 else 0
     * {@link MinDominoRotationsForEqualRow} {@link alternate.CombinationLock}
     * <p>
     * Can also be done in linear time but I am kinda biased towards my original thinking of fixing each initial state as the target state
     */
    public int minCostToMoveChips(int[] position) {
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < position.length; i++) {
            int targetPosition = position[i];
            int cost = 0;
            for (int j = 0; j < position.length; j++) {
                int diff = Math.abs(position[i] - position[j]);
                if (diff % 2 == 1) {
                    cost += 1;
                }
            }
            result = Math.min(result, cost);
        }
        return result;
    }
}
