/**
 * https://leetcode.com/problems/increasing-triplet-subsequence/
 * <p>
 * Given an integer array nums, return true if there exists a triple of indices (i, j, k) such that i < j < k and nums[i] < nums[j] < nums[k].
 * If no such indices exists, return false.
 * <p>
 * Input: nums = [1,2,3,4,5]
 * Output: true
 * Explanation: Any triplet where i < j < k is valid.
 * <p>
 * Input: nums = [5,4,3,2,1]
 * Output: false
 * Explanation: No triplet exists.
 * <p>
 * Input: nums = [2,1,5,0,4,6]
 * Output: true
 * Explanation: The triplet (3, 4, 5) is valid because nums[3] == 0 < nums[4] == 4 < nums[5] == 6.
 */
public class IncreasingTripletSubsequence {
    /**
     * Approach: Keep track of the smallest and second smallest number seen so far. If you see a number greater than second smallest
     * number, you have found a valid triplet
     * <p>
     * Time Complexity: O(n) Space complexity: O(1)
     */
    public boolean increasingTripletSimplified(int[] nums) {
        //Do a dry run for [1,0,2,0,-1,3]
        //first = -1, second = 2, third = 3
        //since second = 2, this indicates there was a number smaller than 2, so we can still return true
        //although this approach can't return the exact triplets
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num < first) {
                first = num;
            }
            if (num > first && num < second) {
                second = num;
            }
            if (num > second) {
                return true;
            }
        }
        return false;
    }

    /**
     * Approach: Keep track of smallest element on the left and maximum element on the right. If you find a number such that
     * min < num < max, you got the result
     * Time Complexity: O(n) Space Complexity O(n)
     * <p>
     * {@link OneThreeTwoPattern} related problem
     */
    public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }
        int[] min = new int[n];
        int[] max = new int[n];
        min[0] = nums[0];
        for (int i = 1; i < n; i++) {
            min[i] = Math.min(min[i - 1], nums[i]);
        }
        max[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            max[i] = Math.max(max[i + 1], nums[i]);
        }
        for (int i = 0; i < n; i++) {
            if (min[i] < nums[i] && nums[i] < max[i]) {
                return true;
            }
        }
        return false;
    }

}
