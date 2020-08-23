import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/subsets-ii/
 * <p>
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
 * <p>
 * Note: The solution set must not contain duplicate subsets.
 * <pre>
 * Input: [1,2,2]
 * Output:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 * </pre>
 */
public class PowerSet2 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        recur(nums, res, new ArrayList<Integer>(), 0);
        return res;
    }

    private void recur(int[] nums, List<List<Integer>> res, ArrayList<Integer> current, int curIndex) {
        res.add(new ArrayList<>(current));
        for (int i = curIndex; i < nums.length; i++) {
            //Important: Skip duplicates if they are part of the same candidate index in the result set
            // eg. we are done with 2 (1st index) at 2nd place, now if we see 2 again as a candidate in the same loop, we need to skip it
            if (i != curIndex && nums[i] == nums[i - 1]) {
                continue;
            }
            current.add(nums[i]);
            recur(nums, res, current, i + 1);
            current.remove(current.size() - 1);
        }
    }
}
