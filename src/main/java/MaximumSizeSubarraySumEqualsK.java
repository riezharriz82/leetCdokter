import java.util.HashMap;

/**
 * https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/ Premium
 * <p>
 * Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.
 * <p>
 * Note:
 * The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.
 * <p>
 * Input: nums = [1, -1, 5, -2, 3], k = 3
 * Output: 4
 * Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.
 * <p>
 * Input: nums = [-2, -1, 2, 1], k = 1
 * Output: 2
 * Explanation: The subarray [-1, 2] sums to 1 and is the longest.
 * <p>
 * Follow Up:
 * Can you do it in O(n) time?
 */
public class MaximumSizeSubarraySumEqualsK {
    /**
     * Approach: Prefix sum
     * <p>
     * {@link SubarraySumEqualsK} {@link SubarraySumsDivisibleByK}
     */
    public int maxSubArrayLen(int[] nums, int k) {
        int ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        int prefixSum = 0;
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            if (map.containsKey(prefixSum - k)) {
                ans = Math.max(ans, i - map.get(prefixSum - k));
            }
            if (!map.containsKey(prefixSum)) { //don't update the index of the prefixSum to a newer value as we want the leftmost index
                map.put(prefixSum, i);
            }
        }
        return ans;
    }
}
