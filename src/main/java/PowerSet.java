import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/subsets/
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 * <p>
 * Note: The solution set must not contain duplicate subsets.
 * <p>
 * Example:
 * <p>
 * Input: nums = [1,2,3]
 * <p>
 * Output:
 * <pre>
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 * </pre>
 */
public class PowerSet {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        helper(nums, res, new ArrayList<>(), 0);
        return res;
    }

    private void helper(int[] nums, List<List<Integer>> result, ArrayList<Integer> currentList, int curIndex) {
        result.add(new ArrayList<>(currentList));
        for (int i = curIndex; i < nums.length; i++) {
            currentList.add(nums[i]);
            helper(nums, result, currentList, i + 1); //increment the index in order to not consider the same value again
            currentList.remove(currentList.size() - 1);
        }
    }
}
