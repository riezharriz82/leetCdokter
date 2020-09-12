import java.util.Arrays;

/**
 * https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
 * Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order,
 * then the whole array will be sorted in ascending order, too.
 * <p>
 * You need to find the shortest such subarray and output its length.
 * <p>
 * Example 1:
 * Input: [2, 6, 4, 8, 10, 9, 15]
 * Output: 5
 * Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
 */
public class ShortestUnsortedContinuousSubarray {
    /**
     * Approach: Sort the array and then start comparing the sorted array with the input array from left and right.
     * First index from the left that has a mismatch will provide the left boundary
     * First index from the right that has a mismatch will provide the right boundary
     * Time Complexity: nlogn
     */
    public int findUnsortedSubarray(int[] nums) {
        int[] copy = Arrays.copyOf(nums, nums.length);
        Arrays.sort(copy);
        if (Arrays.equals(copy, nums)) {
            return 0;
        }

        int leftIndex = 0, rightIndex = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            if (copy[i] != nums[i]) {
                leftIndex = i;
                break;
            }
        }
        for (int j = nums.length - 1; j > 0; j--) {
            if (copy[j] != nums[j]) {
                rightIndex = j;
                break;
            }
        }
        return rightIndex - leftIndex + 1;
    }

    /**
     * Approach: Keep track of the smallest and largest mismatch because they will determine the boundary of the subarray that needs to be sorted.
     * Reiterate the array, the first number > min will mark the left boundary because the min needs to be placed at that index in order to make the result sorted
     * Similarly the first number from the right < max will mark the right boundary because the max needs to be placed at that index.
     */
    public int findUnsortedSubarrayInLinearTime(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] < nums[i]) {
                min = Math.min(min, nums[i + 1]); //keep track of the smallest mismatch
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i - 1] > nums[i]) {
                max = Math.max(max, nums[i - 1]); //largest mismatch
            }
        }
        if (min == Integer.MAX_VALUE && max == Integer.MIN_VALUE) { //no mismatch
            return 0;
        }
        int l = 0, r = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > min) { //min needs to be placed at index i
                l = i;
                break;
            }
        }
        for (int j = nums.length - 1; j > 0; j--) {
            if (nums[j] < max) { //max needs to be placed at index j
                r = j;
                break;
            }
        }
        return r - l + 1;
    }

}
