/**
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii
 * <p>
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * <p>
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 * <p>
 * Find the minimum element.
 * <p>
 * The array may contain duplicates.
 * <p>
 * Input: [3,4,5,1,2]
 * Output: 1
 * <p>
 * Input: [2,2,2,0,1]
 * Output: 0
 */
public class FindMinimumInRotatedSortedArray2 {
    /**
     * Worst case is O(n), Errichto's method will work here only after removing the overlapping part, consider {3,3,1,3}
     * second half {1,3} overlaps with first half {3,3} if you remove 3 from second half it will fall into the standard pattern
     * as solving without duplicates
     */
    public int findMinDuplicatesAllowed(int[] nums) {
        int low = 0, high = nums.length - 1, smallest_idx = -1;
        //remove the overlapping part i.e. [3,3,1,3] becomes [3,3,1]
        //second half should be smaller than first half
        while (nums[high] == nums[low] && high > low) {
            high--;
        }
        int last_idx = high; //last element is now high
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] <= nums[last_idx]) {
                smallest_idx = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return nums[smallest_idx];
    }

    /**
     * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array
     * No duplicates present, much simpler logic
     * It's not sufficient to just compare adjacent elements with the mid, need to compare with the last element (comparison with first element also works)
     * [3,4,5,1,2] -> [F,F,F,T,T]
     * [1,2,3,4,5] -> [T,T,T,T,T]
     * [5,4,3,2,1] -> [F,F,F,F,T]
     * <p>
     * Need to find the first true index
     */
    public int findMinErrichto(int[] nums) {
        int low = 0, high = nums.length - 1, ans = -1;
        int last_index = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] <= nums[last_index]) { //True found, update the ans, and recur left to find a even smaller True
                ans = mid;
                high = mid - 1;
            } else { //False found, recur right to find True
                low = mid + 1;
            }
        }
        return nums[ans];
    }
}
