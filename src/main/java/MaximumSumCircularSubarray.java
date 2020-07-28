/**
 * https://leetcode.com/problems/maximum-sum-circular-subarray/
 * <p>
 * Given a circular array C of integers represented by A, find the maximum possible sum of a non-empty subarray of C.
 * <p>
 * Here, a circular array means the end of the array connects to the beginning of the array.
 * <p>
 * Also, a subarray may only include each element of the fixed buffer A at most once.
 * <p>
 * Input: [1,-2,3,-2]
 * Output: 3
 * Explanation: Subarray [3] has maximum sum 3
 * <p>
 * Input: [5,-3,5]
 * Output: 10
 * Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10
 */
public class MaximumSumCircularSubarray {
    public int maxSubarraySumCircular(int[] A) {
        boolean allNegative = true;
        int maximumNegative = Integer.MIN_VALUE;
        for (int value : A) {
            if (value >= 0) {
                allNegative = false;
                break;
            } else {
                maximumNegative = Math.max(maximumNegative, value);
            }
        }
        //handle cases when all numbers are negative
        if (allNegative) {
            return maximumNegative;
        }
        /*
          Two cases are possible
          1. max sum subarray exists with no wrapping around
          2. in case of wrapping around, it wraps around from the end and covers some portion of the head
          In case 2, you will notice that middle elements will be left out from the result, if we can find out those middle elements
          and subtract it from total sum, we will get a candidate max circular sum.
          The middle subarray is actually the minimum sum subarray. If we negate all the numbers and run kadane algo on the transformed array
          we will get a max sum of subarray which is actually the min subarray sum of original array
         */
        int curSum = 0, maxSumNoCircle = Integer.MIN_VALUE, totalSum = 0;
        int curMinSum = 0, minSumCircular = Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            curSum += A[i];
            totalSum += A[i];
            if (curSum < 0) {
                curSum = 0;
            } else {
                maxSumNoCircle = Math.max(maxSumNoCircle, curSum);
            }

            A[i] *= -1;
            curMinSum += A[i];
            if (curMinSum < 0) {
                curMinSum = 0;
            } else {
                minSumCircular = Math.max(curMinSum, minSumCircular);
            }
        }
        //Instead of subtracting from total sum, we are adding because the sum is already negative, so we need to subtract it from -1 * minSumCircular
        return Math.max(maxSumNoCircle, totalSum + minSumCircular);

    }
}
