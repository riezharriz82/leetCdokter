import java.util.Arrays;

/**
 * https://leetcode.com/problems/maximum-sum-of-two-non-overlapping-subarrays/
 * <p>
 * Given an array A of non-negative integers, return the maximum sum of elements in two non-overlapping (contiguous) subarrays, which have lengths L and M.
 * (For clarification, the L-length subarray could occur before or after the M-length subarray.)
 * <p>
 * Input: A = [0,6,5,2,2,5,1,9,4], L = 1, M = 2
 * Output: 20
 * Explanation: One choice of subarrays is [9] with length 1, and [6,5] with length 2.
 */
public class MaximumSumOfTwoNonOverlappingSubarraysOfSpecificSize {
    /**
     * Approach: Since this problem asked for non-overlapping solutions, did it by two pass algorithm
     * left[i] store the max subarray sum of specific size ending at index i, starting from 0
     * right[i] store the max subarray sum of specific size ending at index i, starting from end
     * end result would be max(left[i], right[i+1]) for 0 <= i < n
     * <p>
     * first do it when left subarray is of L length and right subarray is of M length
     * then do it when right subarray is of M length and left subarray is of L length
     * keep track of the max result achieved in both of the cases
     */
    public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        return Math.max(calculate(A, L, M), calculate(A, M, L));
    }

    private int calculate(int[] A, int L, int M) {
        int n = A.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, Integer.MIN_VALUE);
        Arrays.fill(right, Integer.MIN_VALUE);
        //left[i] will denote the max subarray sum of L length subarray ending at ith index and starting from 0th index
        int curSum = 0;
        for (int i = 0; i < n; i++) {
            curSum += A[i];
            if (i >= L) { //need to drop out the element falling out of the window
                curSum -= A[i - L];
                left[i] = Math.max(left[i - 1], curSum); //either carry forward the result of the previous subarray if curSum < previous result
            } else if (i == L - 1) { //first subarray of length which equals the expected length
                left[i] = curSum;
            }
        }
        curSum = 0;
        //right[i] will denote the max subarray sum of M length subarray ending at ith index and starting from the end
        int offset = 0; //declared to ease out the logic behind calculating current window size
        for (int i = n - 1; i >= 0; i--, offset++) {
            curSum += A[i];
            if (offset >= M) {
                curSum -= A[i + M];
                right[i] = Math.max(right[i + 1], curSum);
            } else if (offset == M - 1) {
                right[i] = curSum;
            }
        }
        int result = 0;
        for (int i = 0; i < n - 1; i++) {
            if (left[i] != Integer.MIN_VALUE && right[i] != Integer.MIN_VALUE) {
                result = Math.max(result, left[i] + right[i + 1]); //keep track of the max sum of non-overlapping subarray seen so far
            }
        }
        return result;
    }
}
