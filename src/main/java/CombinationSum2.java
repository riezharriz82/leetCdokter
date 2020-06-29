import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum-ii/
 * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
 * <p>
 * Each number in candidates may only be used once in the combination.
 * <p>
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 * <p>
 * Input: candidates = [10,1,2,7,6,1,5], target = 8,
 * A solution set is:
 * [
 * [1, 7],
 * [1, 2, 5],
 * [2, 6],
 * [1, 1, 6]
 * ]
 */
public class CombinationSum2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> curResult = new ArrayList<>();
        helper(candidates, res, curResult, 0, 0, target);
        return res;
    }

    //time complexity n^target (considering each candidate and going as deep as target) took 2ms
    private void helper(int[] candidates, List<List<Integer>> res, List<Integer> curResult, int curIndex, int curSum, int target) {
        if (curSum == target) {
            res.add(new ArrayList<>(curResult)); //need to create a new copy of curResult as curResult will be eventually empty
        } else {
            for (int i = curIndex; i < candidates.length; i++) {
                // skip duplicate elements, as the duplicate elements are already processed in previous iteration
                if (i > curIndex && candidates[i] == candidates[i - 1]) {
                    continue;
                } else if (curSum + candidates[i] <= target) { //only process this candidate if there is a chance of reaching target
                    curResult.add(candidates[i]);
                    //incrementing the index, as same value can't be reused
                    //in the recursion, previous indexes won't be used, this ensures no duplicate i.e. {2,3}, {3,2}
                    helper(candidates, res, curResult, i + 1, curSum + candidates[i], target);
                    curResult.remove(curResult.size() - 1);
                }
            }
        }
    }
}
