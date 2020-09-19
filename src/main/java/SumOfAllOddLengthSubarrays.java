/**
 * https://leetcode.com/problems/sum-of-all-odd-length-subarrays/
 * <p>
 * Given an array of positive integers arr, calculate the sum of all possible odd-length subarrays.
 * <p>
 * Return the sum of all odd-length subarrays of arr.
 * <p>
 * Input: arr = [1,4,2,5,3]
 * Output: 58
 * Explanation: The odd-length subarrays of arr and their sums are:
 * [1] = 1
 * [4] = 4
 * [2] = 2
 * [5] = 5
 * [3] = 3
 * [1,4,2] = 7
 * [4,2,5] = 11
 * [2,5,3] = 10
 * [1,4,2,5,3] = 15
 * If we add all these together we get 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58
 */
public class SumOfAllOddLengthSubarrays {
    /**
     * Brute force solution also works
     * This is an optimisation to solve it in linear time by counting the occurrences of each number in the result
     * If input is 1,4,2,5,3 for value 2, all possible subarray are
     * <pre>
     *       2       odd
     *     4,2
     *   1,4,2       odd
     *       2,5
     *       2,5,3   odd
     *     4,2,5     odd
     *     4,2,5,3
     *   1,4,2,5
     *   1,4,2,5,3   odd
     * </pre>
     * For index i, we can pick 0,1,2.. i elements on the left, total i+1, for index 2 = 3
     * we can pick n-i elements on the right too, 5-2 = 3
     * total no of subarrays = 3 * 3
     * subarrays with odd length = ceil(9/2) = 5
     * <p>
     * {@link NumberOfSubarraysWithOddSum} with similar maths problem related to subarray
     */
    public int sumOddLengthSubarrays(int[] arr) {
        int n = arr.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            int leftChoices = i + 1;
            int rightChoices = n - i;
            int totalContribution = arr[i] * (int) Math.ceil((leftChoices * rightChoices) / 2.0); // divide by 2.0 to keep it double
            res += totalContribution;
        }
        return res;
    }
}
