/**
 * https://leetcode.com/problems/shortest-subarray-to-be-removed-to-make-array-sorted/
 * <p>
 * Given an integer array arr, remove a subarray (can be empty) from arr such that the remaining elements in arr are non-decreasing.
 * <p>
 * Return the length of the shortest subarray to remove.
 * <p>
 * Input: arr = [1,2,3,10,4,2,3,5]
 * Output: 3
 * Explanation: The shortest subarray we can remove is [10,4,2] of length 3. The remaining elements after that will be [1,2,3,3,5] which are sorted.
 * Another correct solution is to remove the subarray [3,10,4].
 * <p>
 * Input: [13,0,14,7,18,18,18,16,8,15,20]
 * Output: 8
 */
public class FindShortestSubarrayToBeRemovedToMakeArraySorted {
    /**
     * Approach: Find longest increasing subarray that starts from left and right.
     * Now we have two options, either cut the subarray from the left or right. This will give the upper bound.
     * We can try to relax the upper bound by then trying to merge two sorted subarrays and see if it gives a longer sorted subarray.
     * <p>
     * Consider, for example, if arr = [1,2,3,10,4,2,3,5]
     * <p>
     * The monotone non-decreasing prefix is [1,2,3,10]
     * The monotone non-decreasing suffix is [2,3,5]
     * We'll consider the following situations in the merge part:
     * 1. If we take [1] from the prefix, then we can attach [2,3,5] from the suffix to it
     * 2. If we take [1,2] from the prefix, then we can attach [2,3,5] from the suffix to it
     * 3. If we take [1,2,3] from the prefix, then we can attach [3,5] from the suffix to it
     * 4. If we take [1,2,3,10] from the prefix, then we can attach [] from the suffix to it
     */
    public int findLengthOfShortestSubarray(int[] arr) {
        int n = arr.length;
        if (n == 1) {
            return 0;
        }
        int left = 0, right = n - 1;
        while (left < n - 1 && arr[left] <= arr[left + 1]) { //longest increasing subarray from start
            left++;
        }
        if (left == n - 1) {
            return 0; //already a sorted array
        }
        while (right > 0 && arr[right] >= arr[right - 1]) { //longest increasing subarray from end
            right--;
        }
        //this is the upper bound
        int res = Math.min(n - left - 1, right); //if i keep the left or right longest increasing subarray
        //try to restrict the upper bound by trying to merge two sorted subarrays
        int i = 0, j = right;
        while (i <= left && j < n) {
            if (arr[i] <= arr[j]) {
                //need to delete everything in between i and j
                res = Math.min(res, j - i - 1);
                i++;
            } else {
                //skip this element
                j++;
            }
        }
        return res;
    }
}
