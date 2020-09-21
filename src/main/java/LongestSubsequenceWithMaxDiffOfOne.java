import java.util.HashMap;

/**
 * https://leetcode.com/problems/longest-harmonious-subsequence/
 * <p>
 * We define a harmonious array as an array where the difference between its maximum value and its minimum value is exactly 1.
 * <p>
 * Given an integer array nums, return the length of its longest harmonious subsequence among all its possible subsequences.
 * <p>
 * A subsequence of array is a sequence that can be derived from the array by deleting some or no elements without changing the order of the remaining elements.
 * <p>
 * Input: nums = [1,3,2,2,5,2,3,7]
 * Output: 5
 * Explanation: The longest harmonious subsequence is [3,2,2,2,3].
 */
public class LongestSubsequenceWithMaxDiffOfOne {
    /**
     * Approach: The problem asks to find subsequence where max diff between two elements is 1
     * i.e relationship between min and max should be max = min + 1
     * consider each element as min and see if you can find corresponding max element.
     * size of the subsequence would be the number of occurrences of min + no of occurrences of max
     */
    public int findLHS(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>(); //number -> no of occurrences
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int result = 0;
        for (int num : nums) {
            if (map.containsKey(num + 1)) { //check for max element by fixing min element of the subsequence
                result = Math.max(result, map.get(num) + map.get(num + 1));
            }
        }
        return result;
    }
}
