/**
 * https://leetcode.com/problems/partition-array-for-maximum-sum/
 * <p>
 * Given an integer array arr, you should partition the array into (contiguous) subarrays of length at most k.
 * After partitioning, each subarray has their values changed to become the maximum value of that subarray.
 * <p>
 * Return the largest sum of the given array after partitioning.
 * <p>
 * Input: arr = [1,15,7,9,2,5,10], k = 3
 * Output: 84
 * Explanation: arr becomes [15,15,15,9,10,10,10]
 */
public class PartitionArrayForMaximumSum {
    /**
     * Approach: Every number has two options, be part of the current subarray or start a new subarray.
     * Caveat is that current subarray size <= k
     * <p>
     * {@link FillingBookCaseShelves} {@link SplitArrayLargestSum}
     */
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int[] memoized = new int[arr.length];
        return recur(arr, k, 0, memoized);
    }

    private int recur(int[] arr, int k, int index, int[] memoized) {
        if (index == arr.length) {
            return 0;
        }
        if (memoized[index] != 0) {
            return memoized[index];
        }
        int curMax = Integer.MIN_VALUE;
        int maxTotalSum = Integer.MIN_VALUE;
        for (int i = index; i < index + k && i < arr.length; i++) { //consider only k elements from current index as part of current window.
            curMax = Math.max(curMax, arr[i]);
            int remain = recur(arr, k, i + 1, memoized); //if next index is part of next subarray, what is the sum I could get?
            maxTotalSum = Math.max(maxTotalSum, remain + (curMax * (i - index + 1)));
            //current subarray sum would be the max element of subarray * size of current subarray
            //totalSum would be current subarray sum + remaining sum
            //goal is to maximize the totalSum
        }
        return memoized[index] = maxTotalSum;
    }
}
