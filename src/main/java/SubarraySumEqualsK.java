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
    public int subarraySumWithoutHashMap(int[] nums, int k) {
        int min = nums[0], max = nums[0], curSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            curSum += nums[i];
            min = Math.min(min, curSum);
            max = Math.max(max, curSum);
        }
        int[] reverseLookUp = new int[max - min + 1]; // if the min is 1 and the max is 3, there can be at max 3 elements
        curSum = 0;
        int res = 0;
        for (int num : nums) {
            curSum += num;
            if (curSum == k) {
                res++;
            }
            if (curSum - k >= min && curSum - k <= max) {
                res += reverseLookUp[curSum - k - min];
            }
            reverseLookUp[curSum - min]++;
        }
        return res;
    }


    public int subarraySum(int[] nums, int k) {
        int res = 0, curSum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            curSum += num;
            if (curSum == k) {
                res++;
            }
            if (map.containsKey(curSum - k)) {
                res += map.get(curSum - k);
            }
            map.put(curSum, map.getOrDefault(curSum, 0) + 1);
        }
        return res;
    }
}
