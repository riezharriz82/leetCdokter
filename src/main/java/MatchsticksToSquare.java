/**
 * <pre>
 * https://leetcode.com/problems/matchsticks-to-square/
 *
 * You are given an integer array matchsticks where matchsticks[i] is the length of the ith matchstick. You want to use all the matchsticks to make one square.
 * You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.
 *
 * Return true if you can make this square and false otherwise.
 *
 * Input: matchsticks = [1,1,2,2,2]
 * Output: true
 * Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
 *
 * Input: matchsticks = [3,3,3,3,4]
 * Output: false
 * Explanation: You cannot find a way to form a square with all the matchsticks.
 *
 * Constraints:
 * 1 <= matchsticks.length <= 15
 * 0 <= matchsticks[i] <= 10^9
 * </pre>
 */
public class MatchsticksToSquare {
    /**
     * Approach: Backtracking, need to divide the array into 4 equal sum subsets
     * TimeComplexity: 4*(2^n)
     *
     * {@link PartitionEqualSubsetSum} {@link PartitionKEqualSumSubsets} {@link TargetSum}
     */
    public boolean makesquare(int[] matchsticks) {
        long totalSum = 0;
        for (int len : matchsticks) {
            totalSum += len;
        }
        if (totalSum % 4 != 0) { //not possible to divide into 4 subsets
            return false;
        } else {
            return recur(matchsticks, totalSum / 4, 0, 0, new boolean[matchsticks.length], 0);
        }
    }

    private boolean recur(int[] matchsticks, long targetSum, int index, long curSum, boolean[] visited, int subsetsFound) {
        if (subsetsFound == 4) { //all 4 subsets found
            return true;
        } else if (curSum == targetSum) { //a valid subset found, recur to find remaining subsets
            return recur(matchsticks, targetSum, 0, 0, visited, subsetsFound + 1);
        } else if (curSum > targetSum) { //current subset is invalid
            return false;
        }
        if (!visited[index]) { //if current index is not part of any other subset
            visited[index] = true; //marking the index as visited is required so that it does not get picked up in another subset
            if (recur(matchsticks, targetSum, index + 1, curSum + matchsticks[index], visited, subsetsFound)) {
                //all 4 subsets have been found
                return true;
            }
            //backtrack, do not pick current index
            visited[index] = false;
        }
        //do not pick current index in this subset
        return recur(matchsticks, targetSum, index + 1, curSum, visited, subsetsFound);
    }
}
