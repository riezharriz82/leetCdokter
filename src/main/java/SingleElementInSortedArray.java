/**
 * https://leetcode.com/problems/single-element-in-a-sorted-array/
 * <p>
 * You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element
 * which appears exactly once. Find this single element that appears only once.
 * <p>
 * Follow up: Your solution should run in O(log n) time and O(1) space.
 * <p>
 * Input: nums = [1,1,2,3,3,4,4,8,8]
 * Output: 2
 * <p>
 * Input: nums = [3,3,7,7,10,11,11]
 * Output: 10
 * <p>
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^5
 */
public class SingleElementInSortedArray {
    /**
     * Approach: Use binary search to find the single element, the tricky thing to note here is to note that
     * i (even) and i + 1 (odd) indices contains the same value on the left side of the target
     * whereas i (odd) and i+1 (even) indices contains the same value on the right side of the target
     * 2,2,3,4,4,5,5
     * E,0,E,O,E,O,E
     * T,T,T,F,F,F,F
     * Need to find the last true
     * <p>
     * There are a lot of edge cases and only way to uncover them is to consider each index as the mid and try to solve it
     * Consider cases like T,T,T,T,T and T,F,F,F,F
     * <p>
     * {@link SearchInRotatedSortedArray2} {@link SearchInSortedArrayOfUnknownSize} {@link FindInMountainArray} related problems
     */
    public int singleNonDuplicate(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (mid % 2 == 0) {
                if (mid + 1 < nums.length && nums[mid] == nums[mid + 1]) { //if mid is a valid even index on the left of the target
                    low = mid + 1;
                } else if (mid == 0 && nums[mid] != nums[mid + 1]) { //T,F,F (handle cases were first index is the answer)
                    return nums[mid];
                } else if (mid != nums.length - 1 && nums[mid] != nums[mid + 1] && nums[mid] != nums[mid - 1]) {
                    //handles the case when mid is the target i.e mid points to 3 [2,2,3,4,4,5,5]
                    return nums[mid];
                } else if (mid == nums.length - 1 && nums[mid] != nums[mid - 1]) {
                    //when target is the last element of the array
                    return nums[mid];
                } else if (mid + 1 < nums.length && nums[mid] != nums[mid + 1]) {
                    //when mid is an even index on the right of the target
                    high = mid - 1;
                }
            } else {
                if (mid - 1 >= 0 && nums[mid] == nums[mid - 1]) { //when mid is an odd index on the left of the target
                    low = mid + 1;
                } else if (mid - 1 >= 0 && nums[mid] != nums[mid - 1]) { //when mid is an odd index on the right of the target
                    high = mid - 1;
                }
            }
        }
        return -1;
    }
}
