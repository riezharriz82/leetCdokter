import java.util.Arrays;

/**
 * https://leetcode.com/problems/two-sum-less-than-k/ Premium
 * <p>
 * Given an array A of integers and integer K, return the maximum S such that there exists i < j with A[i] + A[j] = S and S < K.
 * If no i, j exist satisfying this equation, return -1.
 * <p>
 * Input: A = [34,23,1,24,75,33,54,8], K = 60
 * Output: 58
 * Explanation: We can use 34 and 24 to sum 58 which is less than 60.
 * <p>
 * Input: A = [10,20,30], K = 15
 * Output: -1
 * Explanation: In this case it is not possible to get a pair sum less that 15.
 */
public class TwoSumLessThanK {

    /**
     * Approach: Two pointer solution similar to classic TwoSum problem
     * An interesting observation is that the problem statement says find two indices such that i < j
     * So even after sorting it works because sum is commutative,
     * e.g. [3,2,4,1], 5
     * answer is 3 + 1 = 4
     * after sorting [1,2,3,4], answer is 1 + 3 = 4
     * so result sum will be unique and will still be the same after sorting, because even if the numbers are swapped, their sum will still be the same.
     */
    public int twoSumLessThanK(int[] A, int K) {
        Arrays.sort(A);
        int begin = 0, end = A.length - 1;
        int res = -1;
        while (begin < end) {
            int curSum = A[begin] + A[end];
            if (curSum > K) {
                end--;
            } else if (curSum < K) {
                res = Math.max(res, curSum);
                begin++;
            } else {
                begin++;
            }
        }
        return res;
    }
}
