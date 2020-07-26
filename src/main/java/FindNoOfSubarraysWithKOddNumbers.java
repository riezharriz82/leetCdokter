import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/count-number-of-nice-subarrays/
 * <p>
 * Given an array of integers nums and an integer k. A subarray is called nice if there are k odd numbers on it.
 * <p>
 * Return the number of nice sub-arrays.
 * <p>
 * Input: nums = [1,1,2,1,1], k = 3
 * Output: 2
 * <p>
 * Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
 */
public class FindNoOfSubarraysWithKOddNumbers {

    /**
     * Sliding Window solution is a bit difficult to implement
     * https://leetcode.com/problems/count-number-of-nice-subarrays/discuss/508217/C++:-Visual-explanation.-O(1)-space.-Two-pointers
     * <p>
     * My approach: Replace even number with 0 and odd number with 1. Problem is now resolved to finding subarray with sum = k
     * See {@link NumberOfSubarraysWithOddSum} for related question
     */
    public int numberOfSubarrays(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); //to handle cases where prefix sum equals k
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] % 2;
            if (i > 0) {
                nums[i] += nums[i - 1]; //transform array into prefix sum
            }
        }
        int res = 0;
        for (int num : nums) {
            if (map.containsKey(num - k)) {
                res += map.get(num - k);
            }
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return res;
    }
}
