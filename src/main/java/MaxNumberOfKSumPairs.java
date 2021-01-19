import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/max-number-of-k-sum-pairs/
 * <p>
 * You are given an integer array nums and an integer k.
 * <p>
 * In one operation, you can pick two numbers from the array whose sum equals k and remove them from the array.
 * <p>
 * Return the maximum number of operations you can perform on the array.
 * <p>
 * Input: nums = [1,2,3,4], k = 5
 * Output: 2
 * Explanation: Starting with nums = [1,2,3,4]:
 * - Remove numbers 1 and 4, then nums = [2,3]
 * - Remove numbers 2 and 3, then nums = []
 * There are no more pairs that sum up to 5, hence a total of 2 operations.
 * <p>
 * Input: nums = [3,1,3,4,3], k = 6
 * Output: 1
 * Explanation: Starting with nums = [3,1,3,4,3]:
 * - Remove the first two 3's, then nums = [1,4,3]
 * There are no more pairs that sum up to 6, hence a total of 1 operation.
 * <p>
 * Constraints:
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 109
 * 1 <= k <= 109
 */
public class MaxNumberOfKSumPairs {
    /**
     * Approach: Remember prior learnings, whenever asked to find something, either use binary search or hashmap
     * Here we can either sort the array and use 2 pointers or build the hashmap and then search for (k - curVal) in the map
     * Need to handle edge cases when curVal == k / 2 ie. k is 6 and curVal is 3
     * <p>
     * {@link TwoSum}
     */
    public int maxOperations(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int pairs = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int key = entry.getKey();
            int val = entry.getValue();
            if (k % 2 == 0 && (key == k / 2)) { //special case when key is half of k, then we are looking for the same value
                pairs += (val / 2);
            } else if ((k - key) > key && map.containsKey(k - key)) {
                pairs += Math.min(val, map.get(k - key));
            }
        }
        return pairs;
    }
}
