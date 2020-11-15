/**
 * https://leetcode.com/problems/sort-colors/
 * <p>
 * Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent,
 * with the colors in the order red, white and blue.
 * <p>
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * <p>
 * Input: [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 */
public class SortColors {
    public static void main(String[] args) {
        new SortColors().sortColors(new int[]{2, 0, 2, 1, 1, 0});
    }

    /**
     * Approach: Classic algorithm which leverages dutch 3 way sorting algorithm
     * <p>
     * Take 3 pointers, i, j, k, i is pointing to partition containing 0, and k is pointing to the partition containing 2
     * j is the unclassified partition
     * j should be moved ahead and element at j should be compared
     * if its 0, swap with ith location, increment both i and j
     * if its 1, increment j
     * if its 2, swap with kth location, decrement k
     */
    public void sortColors(int[] nums) {
        int i = 0, j = 0, k = nums.length - 1;
        while (j <= k) { // use <= instead of simple <, can use < if k is initialized from nums.length as in wiki article
            if (nums[j] == 0) {
                swap(nums, i, j);
                i++;
                j++; //remember to increase both i and j, in my initial implementation i incremented only i
                //j points to unclassified number, since we have inspected j and moved it to correct location
                // and the new number that came from ith index can either be 1 or 0
            } else if (nums[j] == 2) {
                swap(nums, j, k);
                k--;
                //Don't increment j because the new number that came from kth index needs to be inspected
            } else {
                j++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
