import java.util.ArrayDeque;

/**
 * https://leetcode.com/problems/find-the-most-competitive-subsequence/
 * <p>
 * Given an integer array nums and a positive integer k, return the most competitive subsequence of nums of size k.
 * <p>
 * An array's subsequence is a resulting sequence obtained by erasing some (possibly zero) elements from the array.
 * <p>
 * We define that a subsequence a is more competitive than a subsequence b (of the same length) if in the first position where a and b differ,
 * subsequence a has a number less than the corresponding number in b.
 * For example, [1,3,4] is more competitive than [1,3,5] because the first position they differ is at the final number, and 4 is less than 5.
 * <p>
 * Input: nums = [3,5,2,6], k = 2
 * Output: [2,6]
 * Explanation: Among the set of every possible subsequence: {[3,5], [3,2], [3,6], [5,2], [5,6], [2,6]}, [2,6] is the most competitive.
 * <p>
 * Input: nums = [2,4,3,3,5,4,9,6], k = 4
 * Output: [2,3,3,4]
 * <p>
 * Constraints:
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 109
 * 1 <= k <= nums.length
 */
public class FindTheMostCompetitiveSubsequence {
    /**
     * Approach: Greedy, This is very similar to finding smallest subsequence with all distinct characters but here we
     * have to worry about keeping the subsequence in an increasing order ie. if currently we have 1,3 in subsequence and we now see 2
     * we can discard values in the candidate subsequence which are > 2.
     * <p>
     * Every index has two options, either get picked or be skipped. We discard an index if we see a value smaller than nums[index]
     * on the right but there is a slight caveat. We can't discard beyond a certain limit, we need to ensure we pick at least k items
     * e.g if array is 10,9,8,5 and k = 2, we have to return 8,5 and not accidentally pop 8
     * <p>
     * This is handled by limiting the amount of values that can be popped
     * <p>
     * {@link SmallestSubsequenceOfDistinctCharacters} {@link FurthestBuildingYouCanReach} {@link MaximumScoreFromRemovingSubstrings}
     */
    public int[] mostCompetitive(int[] nums, int k) {
        if (nums.length == k) {
            return nums;
        }
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int maxPopPossible = nums.length - k; //this is important as it ensures we have at least k items in the stack
        for (int num : nums) {
            while (!stack.isEmpty() && stack.peek() > num && maxPopPossible > 0) { //notice that we don't pop when stack.peek() == num
                stack.pop();
                maxPopPossible--;
            }
            stack.push(num);
        }
        //if array is 1,2,3,4,5 and k = 3, we will have {1,2,3,4,5} as a candidate subsequence, but we need to return only {1,2,3}
        while (stack.size() > k) {
            stack.pop();
        }
        int[] res = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }
        return res;
    }
}
