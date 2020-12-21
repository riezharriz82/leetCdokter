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
     * So we do binary search between those two bounds, and check for each mid, how many chunks/subarrays can be formed from the input array
     * Consider a target sum that is the largest possible subarray sum and then try to split the array and see how many chunks can be formed
     * e.g. {1,2,3,4,5,6,7} if target sum is 10, splits are {1,2,3,4} {5} {6} {7} total 4 splits
     * Our binary search range is [7,28]
     * Target sum   [7,8,9,10,11,12...................25,26,27,28]
     * No of chunks [7,5,4,4,3,3.....................2,2,2,1]
     * chunks <= 2  [F,F,F,F,F,F.....................T,T,T,T]
     * <p>
     * As errichto mentioned in his binary search tutorial https://www.youtube.com/watch?v=GU7DpgHINWQ we need to transform our input range into
     * prefix/suffix of T/F in order to find the required value with first F or last T
     * Here we need the first T, so if we see any index which returns T, then we try to recur left and save the potential answer
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
            if (chunksSplittedInto(nums, mid) <= m) { //target is high, need to reduce target sum so that we can get more splits
                high = mid - 1;
                ans = mid;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    //return how many chunks can the array be divided into, given the largest subarray sum permissible
    //[1,2,3,4], 6 -> returns 2
    //[1,2,3], 6 returns 1
    private int chunksSplittedInto(int[] nums, int target) {
        int splits = 0;
        int curSum = 0;
        for (int num : nums) {
            curSum += num;
            if (curSum > target) { //need to split it
                splits++;
                curSum = num; //current number can't be part of previous chunk, needs to be part of new split
            }
        }
        return splits + 1;
    }
}
