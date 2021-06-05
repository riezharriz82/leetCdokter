import java.util.Arrays;

/**
 * <pre>
 * https://leetcode.com/problems/array-partition-i/
 *
 * Given an integer array nums of 2n integers, group these integers into n pairs (a1, b1), (a2, b2), ..., (an, bn) such that the sum of min(ai, bi) for all i is maximized.
 * Return the maximized sum.
 *
 * Input: nums = [1,4,3,2]
 * Output: 4
 * Explanation: All possible pairings (ignoring the ordering of elements) are:
 * 1. (1, 4), (2, 3) -> min(1, 4) + min(2, 3) = 1 + 2 = 3
 * 2. (1, 3), (2, 4) -> min(1, 3) + min(2, 4) = 1 + 2 = 3
 * 3. (1, 2), (3, 4) -> min(1, 2) + min(3, 4) = 1 + 3 = 4
 * So the maximum possible sum is 4.
 *
 * Input: nums = [6,2,6,5,1,2]
 * Output: 9
 * Explanation: The optimal pairing is (2, 1), (2, 5), (6, 6). min(2, 1) + min(2, 5) + min(6, 6) = 1 + 2 + 6 = 9.
 *
 * Constraints:
 * 1 <= n <= 10^4
 * nums.length == 2 * n
 * -104 <= nums[i] <= 10^4
 * </pre>
 */
public class ArrayPartition1 {
    /**
     * Approach: Greedy, sum of minimum of pairs needs to be maximized which can happen if the wastage between ai and bi is minimal
     * If we pair ai with a very large number, we would not be able to achieve a higher sum as the larger value will not be able to contribute anything to the answer.
     * Instead we should pair ai with a value >= ai or immediate adjacent value in a sorted array
     * This would ensure we "waste" the smallest number possible as we are only interested in the minimum value of the pair.
     * <p>
     * {@link BoatsToSavePeople}
     */
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length; i += 2) {
            res += nums[i];
        }
        return res;
    }
}
