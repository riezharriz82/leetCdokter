import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * https://leetcode.com/problems/132-pattern/
 * <p>
 * Given an array of n integers nums, a 132 pattern is a subsequence of three integers nums[i], nums[j] and nums[k] such that i < j < k and nums[i] < nums[k] < nums[j].
 * <p>
 * Return true if there is a 132 pattern in nums, otherwise, return false.
 * <p>
 * Follow up: The O(n^2) is trivial, could you come up with the O(n logn) or the O(n) solution?
 * <p>
 * Input: nums = [1,2,3,4]
 * Output: false
 * Explanation: There is no 132 pattern in the sequence.
 * <p>
 * Input: nums = [3,1,4,2]
 * Output: true
 * Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
 * <p>
 * Input: nums = [-1,3,2,0]
 * Output: true
 * Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
 */
public class OneThreeTwoPattern {
    /**
     * Approach: This is my original solution, went through a lot of different approaches before I finalized this.
     * Approaches I discarded, two stacks solution, one for considering smaller elements on the left and one for smaller elements on the right
     * Keeping the smallest element on the left and largest element on the right for each indices but it won't work as largest element on the right
     * will not give the candidate required, we need the largest element on the right smaller than current element {10, 50, 9}
     * we are interested in finding 9 for 10, not 50
     * <p>
     * We can find the largest element on the right smaller than current element using decreasing stack.
     * so if we know the smallest element on the left for each element and the largest element smaller than current element on the right, we can then find if we
     * have 132 pattern, why only smallest element?
     * <p>
     * because consider input {1,8,10,9} -> {8,10,9} and {1,10,9} are valid answers if we just know that smallest element on left of 10 is 1 and the largest element
     * on the right smaller than 10 is 9, we can find the pattern. Other solutions may exist but we are interested only in finding one pattern.
     * <p>
     * {@link LargestRectangleInHistogram} is similar problem. This problem can be done without taking any additional array apart from stack but since
     * this is my original solution, I find it easy to understand.
     */
    public boolean find132pattern(int[] nums) {
        ArrayDeque<Integer> maxStack = new ArrayDeque<>(); //decreasing stack of indices
        int[] minValues = new int[nums.length]; //smallest element on the left of ith index
        int[] maxValues = new int[nums.length]; //largest element on the right smaller than value at current index
        minValues[0] = Integer.MAX_VALUE;
        Arrays.fill(maxValues, Integer.MAX_VALUE);
        int curMin = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < curMin) { //all elements on left are bigger than current element, update the curMin
                minValues[i] = Integer.MAX_VALUE;
                curMin = nums[i];
            } else {
                //found a smaller element on the left, carry forward the curMin
                minValues[i] = curMin;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            while (!maxStack.isEmpty() && nums[maxStack.peek()] <= nums[i]) { //we are interested only in finding smaller element on the right
                maxStack.pop();
            }
            if (maxStack.isEmpty()) {
                //largest element found, do nothing
            } else {
                //current element is smaller than the index at the top of the stack, update the maxValues[]
                //do dry run for {7,3,1,4,8}
                maxValues[maxStack.peek()] = nums[i];
            }
            maxStack.push(i);
        }
        for (int i = 0; i < nums.length; i++) {
            //check if minValue[i] < maxValue[i] < nums[i] i.e. 132 pattern
            if (minValues[i] != Integer.MAX_VALUE && maxValues[i] != Integer.MAX_VALUE && minValues[i] < nums[i] && maxValues[i] < nums[i] && minValues[i] < maxValues[i]) {
                return true;
            }
        }
        return false;
    }
}
