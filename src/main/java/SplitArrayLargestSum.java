import java.util.Arrays;

/**
 * https://leetcode.com/problems/split-array-largest-sum/
 * <p>
 * Given an array nums which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays.
 * <p>
 * Write an algorithm to minimize the largest sum among these m subarrays.
 * <p>
 * Input: nums = [7,2,5,10,8], m = 2
 * Output: 18
 * Explanation:
 * There are four ways to split nums into two subarrays.
 * The best way is to split it into [7,2,5] and [10,8],
 * where the largest sum among the two subarrays is only 18.
 */
public class SplitArrayLargestSum {
    /**
     * Approach: Simple recursive code modified into top down memoized dp
     * Make splits at all indices, keeping track of the left subarray sum and then recurse into the right subarray to get a possible candidate max sum
     * Compare the maximum of left and right, lets name it maxSum.
     * As we make multiple cuts at all the places, keep track of the smallest value of maxSum.
     * Smallest value of maxSum would be the result.
     * <p>
     * TODO It can be optimized by using binary search
     */
    public int splitArray(int[] nums, int m) {
        int[][] memoization = new int[nums.length + 1][m + 1];
        for (int[] ints : memoization) {
            Arrays.fill(ints, -1);
        }
        return recur(nums, m, 0, memoization);
    }

    private int recur(int[] nums, int splits, int index, int[][] memoization) {
        if (splits == 0) {
            if (index == nums.length) { //in case of a valid split, we need to cover all the indices
                return 0;
            } else { //if all the indices are not covered, i.e we did not consider all the input elements
                return Integer.MAX_VALUE; //return infinity, so max of left and right subarray sum would be infinity
            }
        }
        if (memoization[index][splits] != -1) {
            return memoization[index][splits];
        }
        int curSum = 0, minSum = Integer.MAX_VALUE;
        for (int i = index; i < nums.length; i++) {
            curSum += nums[i]; //left subarray sum
            int remaining = recur(nums, splits - 1, i + 1, memoization); //right subarray sum
            minSum = Math.min(minSum, Math.max(curSum, remaining)); //keep track of the smallest value of maximum of left and right subarray sum//
        }
        return memoization[index][splits] = minSum;
    }
}
