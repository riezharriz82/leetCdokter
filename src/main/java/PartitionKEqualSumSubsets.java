/**
 * https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
 * <p>
 * Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.
 * <p>
 * Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * Output: True
 * Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 */
public class PartitionKEqualSumSubsets {
    /**
     * Approach: Recursion with backtracking
     * Need to find k subsets each with sum = totalSum/k
     * Keep a visited set and try adding elements to a set in order to reach target sum.
     * If target sum is reached, we need to find k-1 such subsets.
     * <p>
     * Trick was to use visited set to perform backtracking.
     * <p>
     * I was unable to think about backtracking solution on my own.
     * Followup for {@link PartitionEqualSubsetSum}
     */
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        if (totalSum % k != 0) {
            return false;
        }
        boolean[] visited = new boolean[nums.length];
        return recur(nums, visited, 0, totalSum / k, 0, k);
    }

    private boolean recur(int[] nums, boolean[] visited, int index, int targetSum, int currentSum, int count) {
        if (count == 0) {
            return true;
        } else if (currentSum == targetSum) {
            return recur(nums, visited, 0, targetSum, 0, count - 1);
            // tricky, once a subset is found, start from beginning to add unvisited elements to a new set to reach target sum
        } else if (currentSum > targetSum) {
            return false;
        } else {
            for (int i = index; i < nums.length; i++) {
                if (!visited[i]) { //only consider unvisited elements to avoid duplicate counts
                    visited[i] = true;
                    if (recur(nums, visited, i + 1, targetSum, currentSum + nums[i], count)) {
                        return true;
                    }
                    visited[i] = false;
                }
            }
            return false;
        }
    }
}
