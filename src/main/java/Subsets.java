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
public class Subsets {
    /**
     * Approach: Every number has two choices, either be a part of the result or not be a part of the result
     * Where there are choices, there is recursion to simulate all the choices
     * So first we try skipping the current number and check what results we can get
     * Then we try to add the current number and check what results we can get
     * <p>
     * Another approach would be to leverage alternate approach of leveraging bitset similar to {@link CombinationIteratorRecursive}
     * e.g consider abc, generate all binary numbers of fixed size 3 i.e. 000, 001, 010, 011.... 111
     * For each binary number, add the number present at the set index to the result list
     * <p>
     * For generating fixed size binary number we can loop from 2^3 (1000) till 2^4 (10000) and remove the first 1 using substring.
     * Use Integer.toBinaryString() to convert int quickly to binary string
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        helper(nums, res, new ArrayList<>(), 0);
        return res;
    }

    private void helper(int[] nums, List<List<Integer>> result, ArrayList<Integer> currentList, int curIndex) {
        if (curIndex == nums.length) {
            result.add(new ArrayList<>(currentList));
        } else {
            helper(nums, result, currentList, curIndex + 1); //skip the curIndex and increment the index in order to not consider the same index again
            currentList.add(nums[curIndex]);
            helper(nums, result, currentList, curIndex + 1); //add the curIndex to result and increment the index in order to not consider the same index again
            currentList.remove(currentList.size() - 1);
        }
    }
}
