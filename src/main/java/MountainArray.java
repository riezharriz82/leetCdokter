/**
 * https://leetcode.com/problems/valid-mountain-array/
 * Given an array A of integers, return true if and only if it is a valid mountain array.
 * <p>
 * Recall that A is a mountain array if and only if:
 * <p>
 * A.length >= 3
 * There exists some i with 0 < i < A.length - 1 such that:
 * A[0] < A[1] < ... A[i-1] < A[i]
 * A[i] > A[i+1] > ... > A[A.length - 1]
 */
public class MountainArray {
    /**
     * Approach: Treat the problem similar to solving non-overlapping problems
     * Keep track of longest increasing subarray ending at ith index starting from 0
     * Keep track of longest increasing subarray ending at ith index starting from last
     * <p>
     * {@link FindInMountainArray} {@link LongestMountainInArray} related mountain array problems
     */
    public boolean validMountainArray(int[] A) {
        if (A.length < 3) {
            return false;
        }
        int n = A.length;
        int[] left = new int[n]; //increasing array from left
        int[] right = new int[n]; //increasing array from right
        left[0] = 1;
        for (int i = 1; i < n; i++) {
            if (A[i] > A[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }
        right[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (A[i] > A[i + 1]) {
                right[i] = right[i + 1] + 1;
            } else {
                right[i] = 1;
            }
        }
        for (int i = 0; i < n; i++) {
            //problem statement mentions that left and right portion should be of non zero length
            //ie. [1,2,3] is not a valid mountain
            //so we ensure that left and right subarray length != 1, indicating left and right subarray should contain at least 2 elements
            if (left[i] != 1 && right[i] != 1 && left[i] + right[i] - 1 == n) {
                return true;
            }
        }
        return false;
    }
}
