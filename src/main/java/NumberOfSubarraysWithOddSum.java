/**
 * https://leetcode.com/problems/number-of-sub-arrays-with-odd-sum/
 * <p>
 * Given an array of integers arr. Return the number of sub-arrays with odd sum.
 * <p>
 * As the answer may grow large, the answer must be computed modulo 10^9 + 7.
 * <p>
 * Input: arr = [1,3,5]
 * Output: 4
 * <p>
 * Explanation: All sub-arrays are [[1],[1,3],[1,3,5],[3],[3,5],[5]]
 * All sub-arrays sum are [1,4,9,3,8,5].
 * Odd sums are [1,9,3,5] so the answer is 4.
 */
public class NumberOfSubarraysWithOddSum {
    /**
     * <pre>
     * Input:           1  3  5
     * Prefix Sum    0  1  4  9
     * Even/Odd      E  O  E  O
     * </pre>
     * Now if we have to find number of subarray with odd sum, we have to pick two indices with prefix sum as even and odd respectively
     * If there are x even indices and y odd indices, result will be x * y
     * <p>
     * If the question was modified to return no of even sum subarray, answer will be sum of
     * <p>
     * a. If you pick any two odd indices. If there are x odd indices, result will be x C 2 ie x * (x - 1) / 2
     * <p>
     * b. If you pick any two even indices. If there are y even indices, result will be y C 2 ie. y * (y - 1)/ 2
     * <p>
     * {@link SumOfAllOddLengthSubarrays} {@link FindNoOfSubarraysWithKOddNumbers} related subarray and math related problems
     */
    public int numOfSubarrays(int[] arr) {
        long even = 1, odd = 0, sum = 0; //even has been initialized with 1 because cumulative sum starts with 0 which is even
        for (int value : arr) {
            sum += value;
            if (sum % 2 == 1) {
                odd++;
            } else {
                even++;
            }
        }
        long res = (even * odd) % 1000000007L;
        return (int) res;
    }
}
