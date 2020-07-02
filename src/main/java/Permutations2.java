import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/permutations-ii/
 * <p>
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 * <p>
 * Example:
 * <p>
 * Input: [1,1,2]
 * Output:
 * <pre>
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 * </pre>
 */
public class Permutations2 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        helper(nums, result, new ArrayList<>(), visited);
        return result;
    }

    private void helper(int[] nums, List<List<Integer>> result, ArrayList<Integer> currentList, boolean[] visited) {
        if (currentList.size() == nums.length) {
            result.add(new ArrayList<>(currentList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                //avoid processing duplicates, !visited[i-1] ensures that we don't skip the current element if previous element is under processing
                // previous element is still mark as visited
                if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                    continue;
                } else if (!visited[i]) {
                    visited[i] = true;
                    currentList.add(nums[i]);
                    helper(nums, result, currentList, visited);
                    currentList.remove(currentList.size() - 1);
                    visited[i] = false;
                }
            }
        }
    }
}
