/**
 * https://leetcode.com/problems/minimum-number-of-increments-on-subarrays-to-form-a-target-array/
 * <p>
 * Given an array of positive integers target and an array initial of same size with all zeros.
 * <p>
 * Return the minimum number of operations to form a target array from initial if you are allowed to do the following operation:
 * <p>
 * Choose any subarray from initial and increment each value by one.
 * The answer is guaranteed to fit within the range of a 32-bit signed integer.
 * <p>
 * <p>
 * Input: target = [1,2,3,2,1]
 * Output: 3
 * <p>
 * Explanation: We need at least 3 operations to form the target array from the initial array.
 * <p>
 * [0,0,0,0,0] increment 1 from index 0 to 4 (inclusive).
 * <p>
 * [1,1,1,1,1] increment 1 from index 1 to 3 (inclusive).
 * <p>
 * [1,2,2,2,1] increment 1 at index 2.
 * <p>
 * [1,2,3,2,1] target array is formed.
 */
public class MinimumNumberOfOperationsToReachTargetArray {
    /**
     * Learning from this question: Try to build your solution from 1 sized array, then 2 sized array. See how you can reach the solution
     * <p>
     * Initially you need to do target[0] operations for 0th element.
     * <p>
     * If a[1] > a[0] , you need to perform additional a[1] - a[0] operations to reach target state. a[1] operations is now available for reuse
     * <p>
     * else if a[1] < a[0], you need not perform any additional operations, a[1] operations are now available to reuse
     */
    public int minNumberOperations(int[] target) {
        int totalOperationsDone = target[0];
        for (int i = 1; i < target.length; i++) {
            if (target[i] > target[i - 1]) {
                totalOperationsDone += (target[i - 1] - target[i]);
            }
        }
        return totalOperationsDone;
    }
}
