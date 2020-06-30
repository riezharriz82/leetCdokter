import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum-iii/
 * <p>
 * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used
 * and each combination should be a unique set of numbers.
 * <p>
 * All numbers will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 * <p>
 * Input: k = 3, n = 7
 * Output: [[1,2,4]]
 * <p>
 * Example 2:
 * <p>
 * Input: k = 3, n = 9
 * Output: [[1,2,6], [1,3,5], [2,3,4]]
 */
public class CombinationSum3 {
    int[] candidates = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        helper(res, new ArrayList<>(), 0, k, 0, n, 0);
        return res;
    }

    private void helper(List<List<Integer>> res, ArrayList<Integer> current, int curSize, int maxSize, int curSum, int targetSum, int curIndex) {
        if (curSize == maxSize && curSum == targetSum) { // if current length matches k and current sum matches n
            res.add(new ArrayList<>(current)); // create a copy and add it to the result
        } else {
            for (int i = curIndex; i < candidates.length; i++) {
                if (curSum + candidates[i] <= targetSum && curSize + 1 <= maxSize) { //pruning conditions
                    if (candidates[i] == 2) {
                        int j = 0;
                    }
                    current.add(candidates[i]);
                    //increment the current index as same element can't be used
                    helper(res, current, curSize + 1, maxSize, curSum + candidates[i], targetSum, i + 1);
                    current.remove(current.size() - 1);
                }
            }
        }
    }
}
