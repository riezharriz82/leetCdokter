/**
 * https://leetcode.com/problems/partition-equal-subset-sum/
 * <p>
 * Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 * <p>
 * Input: [1, 5, 11, 5]
 * Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 */
public class PartitionEqualSubsetSum {
    /**
     * Approach: The crux of the problem is to find a subset with sum = totalSum/2
     * So every element has two options, either be a part of the left subset or right subset.
     * <p>
     * It can be simplified further by not considering the right sum at all, just consider whether an element is picked, increment the sum and index and recurse
     * or an element is skipped, increment the index and recurse
     */
    public boolean canPartition(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        if (totalSum % 2 == 1) { //odd sum can't be divided into two
            return false;
        }
        int[][] memo = new int[totalSum / 2][nums.length];
        return recur(nums, 0, totalSum / 2, 0, memo);
    }

    //returns true if you can find a subset with targetSum
    private boolean recur(int[] nums, int curSum, int targetSum, int index, int[][] memo) {
        if (curSum > targetSum || index > nums.length) {
            return false;
        } else if (curSum == targetSum) {
            return true;
        } else if (memo[curSum][index] != 0) {
            return memo[curSum][index] == 1;
        } else if (recur(nums, curSum + nums[index], targetSum, index + 1, memo)) { //include the current index in the target subset
            memo[curSum][index] = 1;
            return true;
        } else if (recur(nums, curSum, targetSum, index + 1, memo)) { //exclude the current index from the target subset
            memo[curSum][index] = 1;
            return true;
        } else {
            //this index can't be part of the target subset
            memo[curSum][index] = -1;
            return false;
        }
    }
}
