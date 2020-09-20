import java.util.HashMap;

/**
 * https://leetcode.com/problems/subarray-sum-equals-k/
 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
 * <p>
 * Example 1:
 * <p>
 * Input:nums = [1,1,1], k = 2
 * Output: 2
 */
public class SubarraySumEqualsK {
    /**
     * Approach: Standard subarray problem dealing with prefix sum. Need a hashmap to store all the prefix sum.
     */
    public int subarraySum(int[] nums, int k) {
        int res = 0, curSum = 0;
        HashMap<Integer, Integer> map = new HashMap<>(); //key as prefix sum and value as number of occurrences
        map.put(0, 1); //to handle subarray sum starting from 0
        for (int num : nums) {
            curSum += num;
            if (map.containsKey(curSum - k)) {
                res += map.get(curSum - k);
            }
            map.put(curSum, map.getOrDefault(curSum, 0) + 1); //there can be multiple subarrays with same sum
        }
        return res;
    }
}
