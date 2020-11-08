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
public class Subsets2 {
    /**
     * Approach: Presence of duplicate number complicate the solution a bit, because for duplicate numbers, there are 3 choices
     * 1. Either the first one can be present
     * 2. All of them can be present
     * 3. None of them can be present
     * <p>
     * So duplicate number must be added to a result set only if the previous duplicate element is added to the result set
     * we can use a visited[] array to track that, but since we are only interested in the previous number, we can use a boolean flag to denote just that
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        recur(nums, res, new ArrayList<>(), 0, false);
        return res;
    }

    private void recur(int[] nums, List<List<Integer>> res, ArrayList<Integer> current, int curIndex, boolean previousElementPresent) {
        if (curIndex == nums.length) {
            res.add(new ArrayList<>(current));
        } else {
            recur(nums, res, current, curIndex + 1, false); //skip currentIndex
            //Important: Skip duplicates if previous duplicate element is not present in the result
            //eg. {1,2,2}, for the last 2, add it to the result set only if the previous 2 was present
            //so in the second recursion stack, it will try to add 2 but since previous 2 was not added, it can't add the last 2
            //now in the fourth recursion stack, it will try to add 2 and since previous 2 was added, result set will now contain {2,2}
            if (curIndex > 0 && nums[curIndex] == nums[curIndex - 1] && !previousElementPresent) {
                return;
            }
            current.add(nums[curIndex]);
            recur(nums, res, current, curIndex + 1, true);
            current.remove(current.size() - 1);
        }
    }
}
