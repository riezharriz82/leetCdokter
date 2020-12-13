/**
 * https://leetcode.com/problems/sum-of-absolute-differences-in-a-sorted-array/
 * <p>
 * You are given an integer array nums sorted in non-decreasing order.
 * <p>
 * Build and return an integer array result with the same length as nums such that result[i] is equal to the summation of absolute differences
 * between nums[i] and all the other elements in the array.
 * <p>
 * In other words, result[i] is equal to sum(|nums[i]-nums[j]|) where 0 <= j < nums.length and j != i (0-indexed).
 * <p>
 * Input: nums = [2,3,5]
 * Output: [4,3,5]
 * Explanation: Assuming the arrays are 0-indexed, then
 * result[0] = |2-2| + |2-3| + |2-5| = 0 + 1 + 3 = 4,
 * result[1] = |3-2| + |3-3| + |3-5| = 1 + 0 + 2 = 3,
 * result[2] = |5-2| + |5-3| + |5-5| = 3 + 2 + 0 = 5.
 * <p>
 * Input: nums = [1,4,6,8,10]
 * Output: [24,15,13,15,21]
 * <p>
 * Constraints:
 * 2 <= nums.length <= 10^5
 * 1 <= nums[i] <= nums[i + 1] <= 10^4
 */
public class SumOfAbsoluteDifferencesInSortedArray {
    /**
     * Approach: Need to independently calculate the contribution of each index separately and then sum it up to return the result
     * Current index is >= previous elements and <= larger elements? Why is this info relevant because we have to consider positive diffs only
     * [2,3,5]
     * for 3-> (3-2) + (3-3) + (5-3) = 3 = ((3+3) - (2+3)) + (5-3)
     * ie. for numbers <= current index if we have the prefix sum till ith index, we can multiply the current val by no of occurrences (i+1)
     * and subtract the prefix sum to get the contributions of left part. This will be positive.
     * <p>
     * If we do the same approach for the right part, we would get negative sum because suffix sum would be larger
     * <p>
     * So instead we subtract contribution of right part (right[i+1]) from no of occurrences of ith element (n-i-1)
     * <p>
     * Very happy to solve this question in the contest
     * <p>
     * {@link SumOfSubarrayMinimums} {@link SumOfAllOddLengthSubarrays} {@link CountUniqueCharactersOfAllSubstrings} related problems
     */
    public int[] getSumAbsoluteDifferences(int[] nums) {
        int n = nums.length;
        int[] prefixSum = new int[n];
        int[] suffixSum = new int[n];
        prefixSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = nums[i] + prefixSum[i - 1];
        }
        suffixSum[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = nums[i] + suffixSum[i + 1];
        }
        int[] res = new int[n];
        for (int i = 0; i < n - 1; i++) {
            int leftDiffSum = (i + 1) * nums[i] - prefixSum[i]; //subtract the prefix sum from total contribution of ith element
            int rightDiffSum = suffixSum[i + 1] - (n - i - 1) * nums[i]; //subtract the total contribution of ith element from suffix sum
            //order has to be flipped to get positive diffs
            res[i] = leftDiffSum + rightDiffSum;
        }
        //special case for last element, it does not have any right subarray, so consider only the left portion
        res[n - 1] = n * nums[n - 1] - prefixSum[n - 1];
        return res;
    }
}
