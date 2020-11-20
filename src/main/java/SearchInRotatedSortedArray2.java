/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
 * <p>
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * <p>
 * (i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).
 * <p>
 * You are given a target value to search. If found in the array return true, otherwise return false.
 * <p>
 * Input: nums = [2,5,6,0,0,1,2], target = 0
 * Output: true
 * <p>
 * Input: nums = [2,5,6,0,0,1,2], target = 3
 * Output: false
 */
public class SearchInRotatedSortedArray2 {
    /**
     * Approach: Code is extension of finding min in sorted rotated array with duplicates + search in sorted rotated array without duplicates
     * <p>
     * Remove the elements in second half that overlaps with first half ie [1,1,3,1] => [1,1,3]
     * Min element is 1, apply binary search in [1,1,3]
     * Do note that the high is not the last element in the array but the last non overlapping element in second half
     * <p>
     * {@link FindMinimumInRotatedSortedArray2} {@link SearchInRotatedSortedArray} related problem
     */
    public boolean search(int[] nums, int target) {
        if (nums.length == 0) {
            return false;
        }
        int low = 0, high = nums.length - 1, smallest_idx = -1;
        while (nums[high] == nums[low] && high > low) { //remove the overlapping part
            high--;
        }
        int last_idx = high;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] <= nums[last_idx]) {
                smallest_idx = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        if (nums[smallest_idx] <= target && target <= nums[last_idx]) {
            //do note that we are using the last non overlapping index instead of nums.length - 1
            //if target lies in the second half
            low = smallest_idx;
            high = last_idx;
        } else {
            //else target lies in the first half
            low = 0;
            high = smallest_idx - 1;
        }
        //standard binary search
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }
}
