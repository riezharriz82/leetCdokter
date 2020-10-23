/**
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * <p>
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
 * <p>
 * If target is not found in the array, return [-1, -1].
 * <p>
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * <p>
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 */
public class FindFirstAndLastPositionOfElementInSortedArray {
    /**
     * Approach: Two Binary Searches
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        int firstIndex = findFirstIndex(nums, target);
        if (firstIndex == -1) {
            return new int[]{-1, -1};
        }
        return new int[]{firstIndex, findLastIndex(nums, target)};
    }

    public int findFirstIndex(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        int ans = -1;
        //as errichto mentioned that this should be set to the value that needs to be returned in case any value with true condition isn't found
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {//lookup in the left to find the a smaller index, but first update the current answer
                ans = mid;
                high = mid - 1;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    public int findLastIndex(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) { //lookup in the right to find a higher index, but first update the current answer
                ans = mid;
                low = mid + 1;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
}
