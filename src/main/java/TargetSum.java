import javafx.util.Pair;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/target-sum/
 * <p>
 * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -.
 * For each integer, you should choose one from + and - as its new symbol.
 * <p>
 * Find out how many ways to assign symbols to make sum of integers equal to target S.
 * <p>
 * Input: nums is [1, 1, 1, 1, 1], S is 3.
 * Output: 5
 * Explanation:
 * <p>
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * <p>
 * There are 5 ways to assign symbols to make the sum of nums be target 3.
 */
public class TargetSum {
    /**
     * Approach: Recursion with memoization, try both adding and subtracting the current number and then recurring for remaining indices
     * See {@link DecodeWays} for related problem
     */
    public int findTargetSumWays(int[] nums, int S) {
        HashMap<Pair<Integer, Integer>, Integer> map = new HashMap<>();
        return helper(nums, S, 0, 0, map);
    }

    private int helper(int[] nums, int targetSum, int currentSum, int index, HashMap<Pair<Integer, Integer>, Integer> map) {
        //index == nums.length ensures that we have covered all the indices, indicating it's not a partial sum
        if (index == nums.length) {
            if (currentSum == targetSum) {
                return 1;
            } else {
                return 0;
            }
        }
        Pair<Integer, Integer> key = new Pair<>(index, currentSum);
        if (map.containsKey(key)) {
            return map.get(key);
        }
        int res = helper(nums, targetSum, currentSum + nums[index], index + 1, map);
        res += helper(nums, targetSum, currentSum - nums[index], index + 1, map);
        map.put(key, res);
        return res;
    }
}
