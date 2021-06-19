/**
 * https://leetcode.com/problems/max-consecutive-ones-iii/
 * <p>
 * Given an array A of 0s and 1s, we may change up to K values from 0 to 1.
 * <p>
 * Return the length of the longest (contiguous) subarray that contains only 1s.
 * <p>
 * Input: A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
 * Output: 6
 * Explanation:
 * [1,1,1,0,0,1,1,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.
 * <p>
 * Input: A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
 * Output: 10
 * Explanation:
 * [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.
 */
public class MaxConsecutiveOnes3 {
    /**
     * Approach: Need to find the longest subarray with num of zeroes <= K.
     * Follow the standard sliding window paradigm for longest window problem, keep expanding the window until it becomes invalid
     * After that, keep shrinking it until it becomes valid again
     * <p>
     * {@link LongestRepeatingCharacterReplacement} {@link SubarrayProductLessThanK} {@link LongestSubarrayOfOnesAfterDeletingOneElement}
     */
    public int longestOnes(int[] A, int K) {
        int begin = 0, end = 0, result = 0;
        int numZeroes = 0;
        while (end < A.length) {
            numZeroes += (A[end] == 0 ? 1 : 0);
            while (numZeroes > K) {
                numZeroes -= (A[begin] == 0 ? 1 : 0);
                begin++;
            }
            result = Math.max(result, end - begin + 1);
            end++;
        }
        return result;
    }
}
