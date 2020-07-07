import java.util.HashMap;
import java.util.Map;

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
    int cnt = 0;

    public int findTargetSumWaysUsingDP(int[] nums, int S) {
        Map<Integer, Integer> mapping = new HashMap<>();
        for (int num : nums) {
            if (mapping.isEmpty()) {
                mapping.put(num, 1);
                // +0 and -0 are two different solutions
                mapping.put(-num, mapping.getOrDefault(-num, 0) + 1); //this special case is for handling 0
            } else {
                HashMap<Integer, Integer> newMapping = new HashMap<>();
                for (Map.Entry<Integer, Integer> entry : mapping.entrySet()) {
                    //if previously we could make a sum of x in y ways, now we can make {x+num}, {x-num} in y ways too
                    newMapping.put(entry.getKey() + num, newMapping.getOrDefault(entry.getKey() + num, 0) + entry.getValue());
                    newMapping.put(entry.getKey() - num, newMapping.getOrDefault(entry.getKey() - num, 0) + entry.getValue());
                }
                //we are only interested in the latest result, not partial result
                mapping = newMapping;
            }
        }
        return mapping.getOrDefault(S, 0);
    }

    public int findTargetSumWaysUsingBacktracking(int[] nums, int S) {
        helper(nums, S, 0, 0);
        return cnt;
    }

    private void helper(int[] nums, int targetSum, int currentSum, int index) {
        //index == nums.length ensures that we have covered all the indices, indicating it's not a partial sum
        if (currentSum == targetSum && index == nums.length) {
            cnt++;
        }
        if (index >= nums.length) {
            return;
        }
        int val = nums[index];
        helper(nums, targetSum, currentSum + val, index + 1);
        helper(nums, targetSum, currentSum - val, index + 1);
    }
}
