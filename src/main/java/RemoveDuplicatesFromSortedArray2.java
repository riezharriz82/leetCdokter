/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
 * <p>
 * Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the new length.
 * <p>
 * Do not allocate extra space for another array; you must do this by modifying the input array in-place with O(1) extra memory.
 * <p>
 * Input: nums = [1,1,1,2,2,3]
 * Output: 5, nums = [1,1,2,2,3]
 * Explanation: Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
 * It doesn't matter what you leave beyond the returned length.
 * <p>
 * Input: nums = [0,0,1,1,1,1,2,3,3]
 * Output: 7, nums = [0,0,1,1,2,3,3]
 * Explanation: Your function should return length = 7, with the first seven elements of nums being modified to 0, 0, 1, 1, 2, 3 and 3 respectively.
 * It doesn't matter what values are set beyond the returned length.
 */
public class RemoveDuplicatesFromSortedArray2 {
    /**
     * Approach: In my initial Solution I overwrote the duplicates with Integer.MAX_VALUE and sorted the output
     * which caused the Integer.MAX_VALUE to get fixed at the end, instead of sorting we can use two pointers to swap/ignore
     * Integer.MAX_VALUE items
     * <p>
     * Can also be done in single pass by comparing the current num and the number at i - 2th index
     * Add to the array only if current num > num[i - 2]
     * <p>
     * Time Complexity: o(n)
     * Space Complexity: o(1)
     */
    public int removeDuplicates(int[] nums) {
        int index = 1;
        int deletedCount = 0;
        while (index < nums.length) {
            if (nums[index] == nums[index - 1]) {
                int temp = index + 1;
                while (temp < nums.length && nums[temp] == nums[index]) {
                    nums[temp] = Integer.MAX_VALUE;
                    deletedCount++;
                    temp++;
                }
                index = temp;
            } else {
                index++;
            }
        }
        index = 0;
        for (int num : nums) {
            if (num != Integer.MAX_VALUE) { //this condition ensures that we skip duplicates
                nums[index++] = num; //don't forget to update index
            }
        }
        return nums.length - deletedCount;
    }
}
