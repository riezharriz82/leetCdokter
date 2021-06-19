/**
 * <pre>
 * https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/
 *
 * Given a binary array nums, you should delete one element from it.
 *
 * Return the size of the longest non-empty subarray containing only 1's in the resulting array.
 *
 * Return 0 if there is no such subarray.
 *
 * Input: nums = [1,1,0,1]
 * Output: 3
 * Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.
 *
 * Input: nums = [0,1,1,1,0,1,1,0,1]
 * Output: 5
 * Explanation: After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with value of 1's is [1,1,1,1,1].
 *
 * Input: nums = [1,1,1]
 * Output: 2
 * Explanation: You must delete one element.
 *
 * Input: nums = [1,1,0,0,1,1,1,0,1]
 * Output: 4
 *
 * Input: nums = [0,0,0]
 * Output: 0
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * nums[i] is either 0 or 1.
 * </pre>
 */
public class LongestSubarrayOfOnesAfterDeletingOneElement {
    /**
     * Approach: Sliding Window, Keep increasing the window till you find two zeroes. Then try to shrink until the window becomes invalid.
     * Keep track of max length of valid window found so far.
     *
     * {@link LongestSubstringWithAtLeastKRepeatingCharacters} {@link MaxConsecutiveOnes3}
     */
    public int longestSubarray(int[] nums) {
        int numZeroes = 0, maxLength = 0;
        int low = 0, high = 0, n = nums.length;
        while (high < n) {
            int val = nums[high];
            if (val == 0) {
                numZeroes++;
            }
            while (numZeroes > 1) { //window has become invalid, need to shrink the window
                int first = nums[low];
                if (first == 0) {
                    numZeroes--;
                }
                low++;
            }
            high++;
            maxLength = Math.max(maxLength, high - low - 1); //-1 is required as we are required to delete 1 character, even if the window contains all 1's
        }
        return maxLength;
    }
}
