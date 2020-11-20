/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 * <p>
 * You are given an integer array nums sorted in ascending order, and an integer target.
 * <p>
 * Suppose that nums is rotated at some pivot unknown to you beforehand (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 * <p>
 * If target is found in the array return its index, otherwise, return -1.
 * <p>
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * <p>
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 */
public class SearchInRotatedSortedArray {
    /**
     * Approach: First identify the smallest number in the array using {@link FindMinimumInRotatedSortedArray2}
     * Then you have two sorted array partitioned by the smallest number
     * Check which half the target lies and perform binary search in that half
     * <p>
     * Crux of the solution lies in identifying the minimum value in sorted rotated array.
     * An important gotcha is to compare with the last element in the array not the "high" element
     * Consider [5,1,3] After picking 1, you will move on to the left partition where if you compare 5 with the high element
     * it will update the smallest_index
     */
    public int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1, smallest_idx = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] <= nums[nums.length - 1]) { //COMPARE WITH THE LAST ELEMENT
                smallest_idx = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        if (nums[smallest_idx] <= target && target <= nums[nums.length - 1]) {
            //if target lies in the second half
            low = smallest_idx;
            high = nums.length - 1;
        } else {
            //else target lies in the first half
            low = 0;
            high = smallest_idx - 1;
        }
        //standard binary search
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
