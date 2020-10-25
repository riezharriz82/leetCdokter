import java.util.Arrays;
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

    //Another way would be https://leetcode.com/problems/find-two-non-overlapping-sub-arrays-each-with-target-sum/discuss/685356/Google-Onsite-Question-or-Very-Easy-Java-O(n)-with-explanation
    // Find size of subarray that equals target sum and ends at index i for all i
    // Reverse the array and do the same
    // Update the left array to be minimum of previous index or current
    // Now for each indices, in order to find non overlapping results, calculate left[i-1] + right[i]
    public int minSumOfLengths(int[] arr, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        map.put(0, -1); // to take care of the subarray sum which directly equals target
        for (int i = 0; i < arr.length; i++) { //this traversal is required to directly check if any target subarray exists on the right
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
            //this is done outside of previous if block because its not necessarily to be the right block to immediately start after
            //e.g {5,10,1,15}, 15 -- At value 10, prefix sum is 15, so we would look for a subarray on right with sum 30 which does not exist
            //however we still set left size in hope of finding non adjacent subarray which equals target sum
            if (map.containsKey(sum + target) && leftSize != Integer.MAX_VALUE) {
                result = Math.min(result, leftSize + map.get(sum + target) - i);
            }
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    /**
     * Approach: This seems to be the standard way of dealing with non-overlapping subarray problems.
     * Store the min subarray length for index i, starting from 0 (left) and starting from end (right)
     * The end result would be left[i] + right[i+1] so that we satisfy the non-overlapping constraint.
     */
    public int minSumOfLengthsUsingLeftAndRightArray(int[] arr, int target) {
        int n = arr.length;
        int[] left = new int[n]; //min subarray length starting from 0 till index i
        int[] right = new int[n]; //min subarray length starting from end till index i
        Arrays.fill(left, Integer.MAX_VALUE);
        Arrays.fill(right, Integer.MAX_VALUE);

        HashMap<Integer, Integer> map = new HashMap<>();
        int curSum = 0;
        map.put(0, -1); //to handle the case when the arr[i] equals target
        for (int i = 0; i < n; i++) {
            curSum += arr[i];
            if (map.containsKey(curSum - target)) {
                left[i] = i - map.get(curSum - target);
            }
            if (i != 0) {
                left[i] = Math.min(left[i - 1], left[i]); //carry forward the previous result, if previous result is smaller
            }
            map.put(curSum, i);
        }
        map.clear();
        curSum = 0;
        map.put(0, n); // because of iterating from right
        for (int i = n - 1; i >= 0; i--) {
            curSum += arr[i];
            if (map.containsKey(curSum - target)) {
                right[i] = map.get(curSum - target) - i;
            }
            if (i != n - 1) {
                right[i] = Math.min(right[i], right[i + 1]); //carry forward the previous result, it it's smaller
            }
            map.put(curSum, i);
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < n - 1; i++) {
            //handle non-overlapping condition
            if (left[i] != Integer.MAX_VALUE && right[i + 1] != Integer.MAX_VALUE) {
                //keep updating the minimum non overlapping length
                result = Math.min(result, left[i] + right[i + 1]);
            }
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }
}
