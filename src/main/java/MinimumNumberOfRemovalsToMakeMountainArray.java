import java.util.Arrays;

/**
 * https://leetcode.com/problems/minimum-number-of-removals-to-make-mountain-array/
 * <p>
 * You may recall that an array arr is a mountain array if and only if:
 * <p>
 * arr.length >= 3
 * There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * Given an integer array nums, return the minimum number of elements to remove to make nums a mountain array.
 * <p>
 * Input: nums = [1,3,1]
 * Output: 0
 * Explanation: The array itself is a mountain array so we do not need to remove any elements.
 * <p>
 * Input: nums = [2,1,1,5,6,2,3,1]
 * Output: 3
 * Explanation: One solution is to remove the elements at indices 0, 1, and 5, making the array nums = [1,5,6,3,1].
 * <p>
 * Input: nums = [4,3,2,1,1,2,3,1]
 * Output: 4
 * <p>
 * Input: nums = [1,2,3,4,4,3,2,1]
 * Output: 1
 * <p>
 * Constraints:
 * 3 <= nums.length <= 1000
 * 1 <= nums[i] <= 109
 * It is guaranteed that you can make a mountain array out of nums.
 */
public class MinimumNumberOfRemovalsToMakeMountainArray {
    /**
     * Approach: Need to find the longest bitonic subsequence in the array using LIS (Longest Increasing Subsequences)
     * <p>
     * Similar question was asked to me during DirectI interview, initially I tried to solve the problem by keeping an ordered map
     * to keep track of all numbers smaller than current number, but then interviewer gave [4,3,2,5,3] which would give me WA
     * as I would consider left[i] for 5 to 4 which is wrong. Then lightning stuck and I thought of solving this problem using LIS
     * <p>
     * {@link Directi.txt}
     * <p>
     * {@link FindInMountainArray} {@link LongestMountainInArray} {@link RussianDollEnvelopes} {@link NumberOfLongestIncreasingSubsequences}
     * {@link FindShortestSubarrayToBeRemovedToMakeArraySorted}
     */
    public int minimumMountainRemovals(int[] nums) {
        int[] left = lis(nums);
        //reverse the input and run lis again
        reverse(nums);
        int[] right = lis(nums);
        //reverse the right array otherwise the indices needs to be offsetted, forgot to reverse the array during actual coding interview
        //interviewer was kind enough to point this out
        reverse(right);
        int longestMountain = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            if (left[i] > 1 && right[i] > 1) { //edge case, only consider mountains of valid non zero left and non zero right length
                int curMountain = left[i] + right[i] - 1; //subtract 1 to avoid double counting of nums[i]
                longestMountain = Math.max(longestMountain, curMountain);
            }
        }
        return n - longestMountain;
    }

    private void reverse(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int temp = nums[low];
            nums[low] = nums[high];
            nums[high] = temp;
            low++;
            high--;
        }
    }

    private int[] lis(int[] nums) {
        //standard n^2 lis algorithm
        int n = nums.length;
        int[] lis = new int[n];
        Arrays.fill(lis, 1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    lis[i] = Math.max(lis[j] + 1, lis[i]);
                }
            }
        }
        return lis;
    }
}
