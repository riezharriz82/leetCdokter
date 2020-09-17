/**
 * https://leetcode.com/problems/longest-mountain-in-array/
 * <p>
 * Let's call any (contiguous) subarray B (of A) a mountain if the following properties hold:
 * <p>
 * B.length >= 3
 * There exists some 0 < i < B.length - 1 such that B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
 * (Note that B could be any subarray of A, including the entire array A.)
 * <p>
 * Given an array A of integers, return the length of the longest mountain.
 * <p>
 * Return 0 if there is no mountain.
 * <p>
 * Input: [2,1,4,7,3,2,5]
 * Output: 5
 * Explanation: The largest mountain is [1,4,7,3,2] which has length 5.
 * <p>
 * Input: [2,2,2]
 * Output: 0
 * Explanation: There is no mountain.
 */
public class LongestMountainInArray {
    /**
     * Approach: Similar to how a non overlapping problem is solved
     * A mountain can be divided into two parts, left part and right part which should be smaller than the center of the mountain
     * So if we keep track of the count of numbers smaller than current number on the left and on the right
     * we can find the max length subarray by considering each index as the center of the mountain
     * <p>
     * It can be done in single pass but the solution is not generic
     * https://leetcode.com/problems/longest-mountain-in-array/discuss/271110/Easy-to-Understand-Code-O(n)
     */
    public int longestMountain(int[] A) {
        int n = A.length;
        if (n == 0) {
            return 0;
        }
        int[] left = new int[n]; //left[i] denotes the count of the number smaller than A[i] present on the left
        int[] right = new int[n]; //right[i] denotes the count of the number smaller than A[i] present on the right
        left[0] = 0;
        right[n - 1] = 0;
        for (int i = 1; i < n; i++) {
            if (A[i] > A[i - 1]) { //need to consider only a contiguous chain of smaller number (subarray)
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 0;
            }
        }
        for (int i = n - 2; i >= 0; i--) {
            if (A[i] > A[i + 1]) {
                right[i] = right[i + 1] + 1;
            } else {
                right[i] = 0;
            }
        }
        int res = 0;
        for (int i = 0; i < n; i++) { //pick each ith index as the center of the mountain and keep track of the max length
            if (left[i] != 0 && right[i] != 0) { //important condition because left and right length needs to be non zero
                //this will also ensure the min length of the mountain is 3, because min value of left[i] and right[i] is 1
                // 1 + 1 + 1 = 3
                res = Math.max(res, left[i] + right[i] + 1);
            }
        }
        return res;
    }
}
