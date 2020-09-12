import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/maximum-number-of-non-overlapping-subarrays-with-sum-equals-target/
 * <p>
 * Given an array nums and an integer target.
 * <p>
 * Return the maximum number of non-empty non-overlapping subarrays such that the sum of values in each subarray is equal to target.
 * <p>
 * Input: nums = [1,1,1,1,1], target = 2
 * Output: 2
 * Explanation: There are 2 non-overlapping subarrays [1,1,1,1,1] with sum equals to target(2).
 * <p>
 * {@link FindTwoNonOverlappingSubarrayWithTargetSum} for related non overlapping subarray problems
 */
public class MaximumNumberOfNonOverlappingSubarraysWithTargetSum {
    /**
     * Keep track of the last index where subarray sum equals target, whenever you find a new subarray whose sum equals target
     * check the starting point of that subarray, if it lies after lastIndex, then increment result
     * <p>
     * This is a greedy solution, in which we try to pick the first valid subarray and then check if we can add another subarray to the result
     * <p>
     * This is similar to {@link EraseNonOverlappingIntervals} wherein we found maximum no of non overlapping intervals
     */
    public int maxNonOverlapping(int[] arr, int target) {
        //key is the prefix sum and value is the starting index of the prefix sum
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // to take care of the subarray sum which directly equals target
        //PS -1 is important, if you initialize it with 0, it will give wrong result e.g input {3,0}, 3
        int prefixsum = 0, result = 0, previousEnd = -1;
        for (int i = 0; i < arr.length; i++) {
            prefixsum += arr[i];
            if (map.containsKey(prefixsum - target) && previousEnd <= map.get(prefixsum - target)) {
                previousEnd = i;
                result++;
            }
            map.put(prefixsum, i);
        }
        return result;
    }
}
