import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/degree-of-an-array/
 * <p>
 * Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.
 * <p>
 * Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.
 * <p>
 * Input: nums = [1,2,2,3,1]
 * Output: 2
 * Explanation:
 * The input array has a degree of 2 because both elements 1 and 2 appear twice.
 * Of the subarrays that have the same degree:
 * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 * The shortest length is 2. So return 2.
 * <p>
 * Input: nums = [1,2,2,3,1,4,2]
 * Output: 6
 * Explanation:
 * The degree is 3 because the element 2 is repeated 3 times.
 * So [2,2,3,1,4,2] is the shortest subarray, therefore returning 6.
 * <p>
 * Constraints:
 * nums.length will be between 1 and 50,000.
 * nums[i] will be an integer between 0 and 49,999.
 */
public class DegreeOfAnArray {
    /**
     * Approach: Sliding window, find the degree of the entire array and then use sliding window to decrement the window if the frequency
     * of nums[left] == target degree, because there can be only one element in the current window with the required degree, so now the curDegree
     * will be decremented by 1
     * <p>
     * This is exactly similar to standard sliding window problems for finding smallest valid window, keep increasing the window on right until we can
     * and then keep on decrementing the window from left, keeping track of valid window length. Break if the window becomes invalid.
     * <p>
     * {@link MinimumWindowSubstring} {@link CountSubstringsWithOnlyOneDistinctLetter}
     */
    public int findShortestSubArraySlidingWindow(int[] nums) {
        int requiredDegree = 0;
        int[] freqs = new int[50_000];
        for (int num : nums) {
            freqs[num]++;
            requiredDegree = Math.max(requiredDegree, freqs[num]);
        }
        Arrays.fill(freqs, 0);
        int left = 0, right = 0, curDegree = 0, shortestLength = Integer.MAX_VALUE;
        while (right < nums.length) {
            int val = nums[right];
            freqs[val]++;
            curDegree = Math.max(curDegree, freqs[val]);
            while (curDegree == requiredDegree) { //current window is a valid window
                int curSize = right - left + 1;
                shortestLength = Math.min(shortestLength, curSize); //keep track of smallest window length
                int slidingElement = nums[left];
                if (freqs[slidingElement] == requiredDegree) {
                    //current window becomes invalid as there can only be one element in the window whose frequency == requiredDegree
                    //now as we are rolling over, the current degree will be decremented by 1, because the new degree will now be at least (curDegree - 1)
                    //there might be several elements with (curDegree - 1) frequency
                    curDegree--;
                }
                freqs[slidingElement]--;
                left++;
            }
            right++;
        }
        return shortestLength;
    }

    /**
     * Approach: Instead of using a sliding window approach, we can keep track of frequency, first and last occurrence of each number.
     * Shortest subarray will contain a number that occurs the required number of time also with the shortest distance between it's first and last occurrence.
     */
    public int findShortestSubArrayTrackOccurrences(int[] nums) {
        Map<Integer, int[]> map = new HashMap<>(); //value -> int[3] {frequency, first_occurrence, last_occurrence}
        int n = nums.length, requiredDegree = 0;
        for (int i = 0; i < n; i++) {
            int finalI = i;
            int[] val = map.computeIfAbsent(nums[i], __ -> new int[]{0, finalI, finalI});
            val[0]++; //update frequency
            val[2] = i; //update last_occurrence
            requiredDegree = Math.max(requiredDegree, val[0]);
        }
        int shortestLength = Integer.MAX_VALUE;
        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            if (entry.getValue()[0] == requiredDegree) {
                shortestLength = Math.min(shortestLength, entry.getValue()[2] - entry.getValue()[1] + 1);
            }
        }
        return shortestLength;
    }
}
