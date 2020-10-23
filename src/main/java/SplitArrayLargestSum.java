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

    /**
     * Approach: Greedy + Binary Search
     * if the target subarray is of size = 1, what can be the maximum subarray sum, it will be the max(array elements)
     * if the target subarray is of size = n, what can be the maximum subarray sum, it wil be sum of all array elements
     * <p>
     * So we do binary search between those two bounds, and check for each mid, how many splits can be performed on the input array
     * Consider a target sum that is the largest possible subarray sum and then try to split the array and see how many splits can be performed
     * e.g. {1,2,3,4,5,6,7} if target sum is 10, splits are {1,2,3,4} {5} {6} {7} total 4 splits
     * if we have to split the array into 3 splits, we can increase our target sum because greater the target sum lesser the splits
     * similarly if required splits is 5, we have to reduce target sum
     */
    public int splitArrayGreedy(int[] nums, int m) {
        int sum = 0, maxValue = Integer.MIN_VALUE;
        for (int num : nums) {
            sum += num;
            maxValue = Math.max(maxValue, num);
        }
        if (m == 1) {
            return sum;
        }
        int low = maxValue, high = sum;
        int ans = 0; //erichtos' binary search template
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (splitArrayPartitions(nums, mid) < m) { //target is high, need to reduce target sum so that we can get more splits
                high = mid - 1;
                ans = mid; //erichto's template of storing result in ans, avoids pesky conditions
            } else if (splitArrayPartitions(nums, mid) >= m) {
                low = mid + 1;
            }
        }
        return ans;
    }

    //return how many splits are required to divide array given target subarray sum i.e. the max subarray sum
    private int splitArrayPartitions(int[] nums, int target) {
        int curSplits = 0;
        int curSum = 0;
        for (int num : nums) {
            curSum += num;
            if (curSum > target) { //need to split it
                curSplits++;
                curSum = num; //current number can't be part of previous split, needs to be part of new split
            }
        }
        return curSplits;
    }
}
