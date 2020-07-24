import java.util.ArrayDeque;
import java.util.HashMap;

/**
 * https://leetcode.com/problems/next-greater-element-i/
 * <p>
 * You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2.
 * Find all the next greater numbers for nums1's elements in the corresponding places of nums2.
 * <p>
 * The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not exist, output -1 for this number.
 * <p>
 * Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
 * Output: [-1,3,-1]
 * <p>
 * For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
 * For number 1 in the first array, the next greater number for it in the second array is 3.
 * For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
 */
public class NextGreaterElement1 {
    /**
     * Exactly similar to finding next greater element {@link NextGreaterElement}
     * but only need to print result for elements present in nums1, hence need to create a map to store intermediate results
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        } else {
            ArrayDeque<Integer> stack = new ArrayDeque<>();
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int value : nums2) {
                while (!stack.isEmpty() && stack.peek() < value) {
                    int pop = stack.pop();
                    map.put(pop, value);
                }
                stack.push(value);
            }
            while (!stack.isEmpty()) {
                map.put(stack.pop(), -1); //no next greater element present
            }

            int[] res = new int[nums1.length];
            for (int i = 0; i < nums1.length; i++) {
                res[i] = map.get(nums1[i]); //nums1 is a subset of nums2, hence element is guaranteed to be present
            }
            return res;
        }
    }
}
