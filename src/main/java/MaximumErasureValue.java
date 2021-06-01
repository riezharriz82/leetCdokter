/**
 * <pre>
 * https://leetcode.com/problems/maximum-erasure-value/
 *
 * You are given an array of positive integers nums and want to erase a subarray containing unique elements. The score you get by erasing the subarray is equal to the sum of its elements.
 *
 * Return the maximum score you can get by erasing exactly one subarray.
 *
 * An array b is called to be a subarray of a if it forms a contiguous subsequence of a, that is, if it is equal to a[l],a[l+1],...,a[r] for some (l,r).
 *
 * Input: nums = [4,2,4,5,6]
 * Output: 17
 * Explanation: The optimal subarray here is [2,4,5,6].
 *
 * Input: nums = [5,2,1,2,5,2,1,2,5]
 * Output: 8
 * Explanation: The optimal subarray here is [5,2,1] or [1,2,5].
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^4
 * </pre>
 */
public class MaximumErasureValue {
    /**
     * Approach: Sliding Window, Keep expanding the window until you find a duplicate element. After that keep shrinking the window until the window does not
     * contain a duplicate element.
     * This is the standard paradigm for sliding window questions asking for "maximum" value.
     * <p>
     * {@link MinimumWindowSubstring} {@link LongestSubstringWithoutRepeatingCharacters}
     */
    public int maximumUniqueSubarray(int[] nums) {
        int low = 0, high = 0, curSum = 0, maxSum = 0;
        int[] cnt = new int[10001]; //alternatively could have used boolean[] or hashset, don't specifically need the count but only whether the element is already present or not
        while (high < nums.length) {
            int val = nums[high];
            cnt[val]++;
            curSum += val;
            while (cnt[val] > 1) { //while the current window has duplicates, keep shrinking the window
                int leftVal = nums[low];
                cnt[leftVal]--;
                low++;
                curSum -= leftVal; //keep updating the current sum of the window
            }
            maxSum = Math.max(maxSum, curSum);
            high++;
        }
        return maxSum;
    }
}
