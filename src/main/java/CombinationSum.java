import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum/
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
 * <p>
 * The same repeated number may be chosen from candidates unlimited number of times.
 * <p>
 * Note:
 * <p>
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * <p>
 * Input: candidates = [2,3,5], target = 8,
 * A solution set is: <pre>
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ] </pre>
 */
public class CombinationSum {
    public List<List<Integer>> combinationSumUsingBacktracking(int[] candidates, int target) {
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
                if (curSum + candidates[i] <= target) { //only process this candidate if there is a chance of reaching target
                    curResult.add(candidates[i]);
                    //not incrementing the index, as same value can be reused
                    //in the recursion, previous indexes won't be used, this ensures no duplicate i.e. {2,3}, {3,2}
                    helper(candidates, res, curResult, i, curSum + candidates[i], target);
                    curResult.remove(curResult.size() - 1);
                }
            }
        }
    }

    //7 ms
    public List<List<Integer>> combinationSumUsingDP(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<List<Integer>>> dp = new ArrayList<>(); //stores the result for each subproblem <= target
        for (int i = 1; i <= target; i++) {
            List<List<Integer>> temp = new ArrayList<>();
            for (int candidate : candidates) {
                if (candidate == i) {
                    temp.add(Collections.singletonList(candidate));
                } else if (i > candidate) {
                    List<List<Integer>> previousResult = dp.get(i - candidate - 1);//get the previous computed result
                    for (List<Integer> res : previousResult) {
                        if (!res.isEmpty() && res.get(res.size() - 1) <= candidate) { // important to ensure no duplicates i.e {2,3},{3,2}
                            ArrayList<Integer> newResult = new ArrayList<>(res);
                            newResult.add(candidate);
                            temp.add(newResult);
                        }
                    }
                }
            }
            dp.add(temp);
        }
        return dp.get(target - 1);
    }
}
