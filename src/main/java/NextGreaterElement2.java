import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * https://leetcode.com/problems/next-greater-element-ii/
 * <p>
 * Given a circular array (the next element of the last element is the first element of the array),
 * print the Next Greater Number for every element. The Next Greater Number of a number x is the first greater number
 * to its traversing-order next in the array, which means you could search circularly to find its next greater number.
 * If it doesn't exist, output -1 for this number.
 * <p>
 * Input: [1,2,1]
 * Output: [2,-1,2]
 * <p>
 * Explanation: The first 1's next greater number is 2;
 * The number 2 can't find next greater number;
 * The second 1's next greater number needs to search circularly, which is also 2.
 */
public class NextGreaterElement2 {
    // A typical way of dealing with circular array is to expand the input array, or to scan the array twice (my original code)
    // But scanning code twice leads to duplicate code and can be simplified
    public int[] nextGreaterElements(int[] nums) {
        ArrayDeque<Integer> stack = new ArrayDeque<>(); //stack will contain the indices
        int[] res = new int[nums.length];
        Arrays.fill(res, -1); // need to return -1 for indices with no next greater element

        //after one loop is done, we still need to check for elements still present in the array, hence double length loop
        for (int i = 0; i < nums.length * 2; i++) {
            int index = i % nums.length;
            while (!stack.isEmpty() && nums[stack.peek()] < nums[index]) {
                res[stack.pop()] = nums[index];
            }
            if (i < nums.length) {
                stack.push(i); // you can push into stack number after looping around but that would be redundant
            }
        }
        return res;
    }
}
