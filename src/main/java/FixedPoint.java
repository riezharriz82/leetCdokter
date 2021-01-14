/**
 * https://leetcode.com/problems/fixed-point/ Premium
 * <p>
 * Given an array of distinct integers arr, where arr is sorted in ascending order, return the smallest index i that satisfies arr[i] == i.
 * If there is no such index, return -1.
 * <p>
 * Input: arr = [-10,-5,0,3,7]
 * Output: 3
 * Explanation: For the given array, arr[0] = -10, arr[1] = -5, arr[2] = 0, arr[3] = 3, thus the output is 3.
 * <p>
 * Input: arr = [0,2,5,8,17]
 * Output: 0
 * Explanation: arr[0] = 0, thus the output is 0.
 * <p>
 * Input: arr = [-10,-5,3,4,7,9]
 * Output: -1
 * Explanation: There is no such i that arr[i] == i, thus the output is -1.
 * <p>
 * Constraints:
 * 1 <= arr.length < 104
 * -10^9 <= arr[i] <= 10^9
 * <p>
 * Follow up: The O(n) solution is very straightforward. Can we do better?
 */
public class FixedPoint {
    /**
     * Approach: Use binary search to find the fixed point, use errichto's template for easy AC
     * -10 -5  0 3  7
     * F   F  F T  T
     * Need to find the first T
     * <p>
     * {@link FindPeakElement} {@link SingleElementInSortedArray} related binary search problems
     */
    public int fixedPoint(int[] arr) {
        int low = 0, high = arr.length - 1, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] >= mid) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        if (ans == -1 || arr[ans] != ans) { //check whether the ans index is valid or not
            return -1;
        }
        return ans;
    }
}
