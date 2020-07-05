/**
 * https://leetcode.com/problems/majority-element/
 * <p>
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 * <p>
 * You may assume that the array is non-empty and the majority element always exist in the array.
 * <p>
 * Example 1:
 * <p>
 * Input: [3,2,3]
 * Output: 3
 * Example 2:
 * <p>
 * Input: [2,2,1,1,1,2,2]
 * Output: 2
 */
public class MajorityElement {
    /**
     * Intuition
     * <p>
     * If we had some way of counting instances of the majority element as +1 and instances of any other element as -1,
     * summing them would make it obvious that the majority element is indeed the majority element.
     */
    public int majorityElement(int[] nums) {
        int majorityElement = nums[0];
        int majorityCount = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != majorityElement) {
                majorityCount--;
            } else {
                majorityCount++;
            }
            if (majorityCount == 0) {
                majorityElement = nums[i];
            }
        }
        return majorityElement;
    }
}
