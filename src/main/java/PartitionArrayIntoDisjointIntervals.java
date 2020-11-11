/**
 * https://leetcode.com/problems/partition-array-into-disjoint-intervals/
 * <p>
 * Given an array A, partition it into two (contiguous) subarrays left and right so that:
 * <p>
 * Every element in left is less than or equal to every element in right.
 * left and right are non-empty.
 * left has the smallest possible size.
 * Return the length of left after such a partitioning.  It is guaranteed that such a partitioning exists.
 * <p>
 * Input: [5,0,3,8,6]
 * Output: 3
 * Explanation: left = [5,0,3], right = [8,6]
 * <p>
 * Input: [1,1,1,0,6,12]
 * Output: 4
 * Explanation: left = [1,1,1,0], right = [6,12]
 * <p>
 * Note:
 * 2 <= A.length <= 30000
 * 0 <= A[i] <= 10^6
 * It is guaranteed there is at least one way to partition A as described.
 */
public class PartitionArrayIntoDisjointIntervals {
    /**
     * Approach: Remember the prior learnings, when dealing with finding non-overlapping left/right subarray, use two passes
     * left pass for calculating the result for subarray ending at index i, starting from 0
     * right pass for calculating the result for subarray ending at index i but starting from n-1
     * then evaluate the result of left[i], right[i+1] and see if it's a valid partition
     * <p>
     * Here we follow the same paradigm, find first partition such that largest value of left subarray <= smallest value of right subarray
     * because every element in left <= every element in the right
     */
    public int partitionDisjoint(int[] A) {
        int n = A.length;
        int[] maxFromTheLeft = new int[n];
        int[] minFromTheRight = new int[n];
        maxFromTheLeft[0] = A[0];
        for (int i = 1; i < n; i++) {
            maxFromTheLeft[i] = Math.max(A[i], maxFromTheLeft[i - 1]);
        }
        minFromTheRight[n - 1] = A[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            minFromTheRight[i] = Math.min(A[i], minFromTheRight[i + 1]);
        }
        for (int i = 0; i < n - 1; i++) {
            if (maxFromTheLeft[i] <= minFromTheRight[i + 1]) {
                return i + 1;
            }
        }
        return -1;
    }
}
