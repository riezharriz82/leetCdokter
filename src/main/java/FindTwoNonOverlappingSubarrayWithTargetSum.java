import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/find-two-non-overlapping-sub-arrays-each-with-target-sum/
 * Given an array of integers arr and an integer target.
 * <p>
 * You have to find two non-overlapping sub-arrays of arr each with sum equal target.
 * There can be multiple answers so you have to find an answer where the sum of the lengths of the two sub-arrays is minimum.
 * <p>
 * Return the minimum sum of the lengths of the two required sub-arrays, or return -1 if you cannot find such two sub-arrays.
 * <p>
 * Input: arr = [5,5,4,4,5], target = 3
 * Output: -1
 * Explanation: We cannot find a sub-array of sum = 3.
 * <p>
 * Input: arr = [3,1,1,1,5,1,2,1], target = 3
 * Output: 3
 * Explanation: Note that sub-arrays [1,2] and [2,1] cannot be an answer because they overlap.
 */
public class FindTwoNonOverlappingSubarrayWithTargetSum {
    public int minSumOfLengths(int[] arr, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        map.put(0, -1); // to take care of the subarray sum which directly equals target
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            map.put(sum, i);
        }
        sum = 0;
        int leftSize = Integer.MAX_VALUE, result = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            //take care of subarray ending at index i
            if (map.containsKey(sum - target)) {
                leftSize = Math.min(leftSize, i - map.get(sum - target));
            }
            //check if subarray sum equals target is present after index i
            //update result only if valid left subarray is found
            if (map.containsKey(sum + target) && leftSize != Integer.MAX_VALUE) {
                result = Math.min(result, leftSize + map.get(sum + target) - i);
            }
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }
}
