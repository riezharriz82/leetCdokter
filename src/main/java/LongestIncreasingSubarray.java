/**
 * https://leetcode.com/problems/longest-continuous-increasing-subsequence/
 * <p>
 * Given an unsorted array of integers, find the length of longest continuous increasing subsequence (subarray).
 * <p>
 * Input: [1,3,5,4,7]
 * Output: 3
 * Explanation: The longest continuous increasing subsequence is [1,3,5], its length is 3.
 * Even though [1,3,5,7] is also an increasing subsequence, it's not a continuous one where 5 and 7 are separated by 4.
 */
public class LongestIncreasingSubarray {
    /**
     * Approach: Standard sliding window code, faced a little issue while setting up the end and begin pointers.
     * See related problem {@link LongestConsecutiveSequence} {@link MinimumWindowSubsequence}
     */
    public int findLengthOfLCIS(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int begin = 0, end = 1, maxLength = 1;
        while (end < nums.length) {
            while (end < nums.length && nums[end - 1] < nums[end]) { //keep increasing the window until the window satisfies the constraint
                end++;
            }
            end--; //because we overshot by 1
            if (end - begin + 1 > maxLength) {
                maxLength = end - begin + 1;
            }
            begin = end + 1; //new window should start just after end of current window
            end = begin + 1; //new end should be after begin
        }
        return maxLength;
    }
}
