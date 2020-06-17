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

    public int findUnsortedSubarrayInLinearTime(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] < nums[i]) {
                min = Math.min(min, nums[i + 1]);
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i - 1] > nums[i]) {
                max = Math.max(max, nums[i - 1]);
            }
        }
        if (min == Integer.MAX_VALUE && max == Integer.MIN_VALUE) {
            return 0;
        }
        int l = 0, r = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > min) {
                l = i;
                break;
            }
        }
        for (int j = nums.length - 1; j > 0; j--) {
            if (nums[j] < max) {
                r = j;
                break;
            }
        }
        return r - l + 1;
    }

}
