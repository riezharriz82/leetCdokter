import java.util.HashMap;

/**
 * https://leetcode.com/problems/contiguous-array/
 * <p>
 * Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
 * <p>
 * Input: [0,1,0]
 * Output: 2
 * Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 */
public class MaxSubarrayWithEqualOnesAndZeroes {
    /**
     * Approach: Convert 0 to -1 and keep track of the prefix sum and the index of first occurrence.
     * When the subarray has equal no of zeroes and ones, the prefix sum would repeat. Compute the length.
     */
    public int findMaxLength(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int curSum = 0, res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                curSum += -1; //tricky part
            } else {
                curSum += 1;
            }
            if (map.containsKey(curSum)) { //subarray with sum 0 found, need to find the start index
                //don't update the curSum with this index because updating will shorten the total length of even longer subarray
                res = Math.max(res, i - map.get(curSum));
            } else {
                map.put(curSum, i);
            }
        }
        return res;
    }
}
