import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/permutations/
 * <p>
 * Given a collection of distinct integers, return all possible permutations.
 * <p>
 * Example:
 * <p>
 * Input: [1,2,3]
 * Output:
 * <pre>
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 * </pre>
 */
public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] visited = new boolean[nums.length]; //can also use bitset
        helper(res, new ArrayList<>(), nums, visited);
        return res;
    }

    private void helper(List<List<Integer>> result, ArrayList<Integer> currentList, int[] nums, boolean[] visited) {
        if (currentList.size() == nums.length) {
            result.add(new ArrayList<>(currentList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (!visited[i]) { //this is required to keep track of all the visited indices, so that we don't add the same element added previously
                    visited[i] = true;
                    currentList.add(nums[i]);
                    helper(result, currentList, nums, visited);
                    currentList.remove(currentList.size() - 1);
                    visited[i] = false; //reset the visited indices so that it can be reused in a different combination
                }
            }
        }
    }
}
