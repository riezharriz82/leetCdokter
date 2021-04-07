/**
 * https://leetcode.com/problems/global-and-local-inversions/
 * <p>
 * We have some permutation A of [0, 1, ..., N - 1], where N is the length of A.
 * <p>
 * The number of (global) inversions is the number of i < j with 0 <= i < j < N and A[i] > A[j].
 * <p>
 * The number of local inversions is the number of i with 0 <= i < N and A[i] > A[i+1].
 * <p>
 * Return true if and only if the number of global inversions is equal to the number of local inversions.
 * <p>
 * Input: A = [1,0,2]
 * Output: true
 * Explanation: There is 1 global inversion, and 1 local inversion.
 * <p>
 * Input: A = [1,2,0]
 * Output: false
 * Explanation: There are 2 global inversions, and 1 local inversion.
 * <p>
 * Note:
 * A will be a permutation of [0, 1, ..., A.length - 1].
 * A will have length in range [1, 5000].
 * The time limit for this problem has been reduced.
 */
public class GlobalAndLocalInversions {
    int globalInversions;

    /**
     * Approach: Tricky greedy, All local inversions are part of global inversions, so in case of local inversions != global inversions,
     * we need to find an inversion that is not local ie. A[i] > A[j] where j >= i + 2.
     * So we can keep track of max element found so far till ith index and it should not be > A[i+2]
     * <p>
     * Other way of solving this would be to leverage the fact that the array contains elements only from 0 to n-1
     * So each element would be ideally present in ith index but since we allow local inversions, each element can be offset by 1 position
     * ie. 1 can be present on 0th or 1st or 2nd index. If 1 is present on 3rd index, this means the inversion is not local
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * <p>
     * {@link CountOfSmallerNumbersAfterSelf}
     */
    public boolean isIdealPermutationOptimized(int[] A) {
        int n = A.length;
        for (int i = 0; i < n; i++) {
            if (Math.abs(A[i] - i) >= 2) { //each element can be offsetted by only 1 index
                return false;
            }
        }
        return true;
    }

    /**
     * Approach: Count global inversions using inversion count approach used in Merge Sort. Care must be taken in the merge function.
     * Visualize it as merging two sorted arrays. Need to copy elements from left and right array into temporary arrays and then rewrite into the original array.
     * <p>
     * Time Complexity: O(nlogn)
     * Space Complexity: O(n)
     */
    public boolean isIdealPermutationInversionCount(int[] A) {
        //count global inversions
        int n = A.length;
        int[] temp = new int[n];
        for (int i = 0; i < n; i++) {
            temp[i] = A[i];
        }
        mergeSort(temp, 0, n - 1);
        //count local inversions
        int localInversions = 0;
        for (int i = 0; i < n - 1; i++) {
            if (A[i] > A[i + 1]) {
                localInversions++;
            }
        }
        return localInversions == globalInversions;
    }

    private void mergeSort(int[] a, int low, int high) {
        if (low < high) {
            int mid = low + (high - low) / 2;
            mergeSort(a, low, mid);
            mergeSort(a, mid + 1, high);
            merge(a, low, mid, high);
        }
    }

    private void merge(int[] a, int low, int mid, int high) {
        int n1 = mid - low + 1;
        int n2 = high - mid;
        int[] left = new int[n1];
        int[] right = new int[n2];
        int temp = 0;
        //copy elements from left array [low, mid]
        for (int i = low; i <= mid; i++) {
            left[temp++] = a[i];
        }
        temp = 0;
        //copy elements from right array [mid+1, high]
        for (int i = mid + 1; i <= high; i++) {
            right[temp++] = a[i];
        }
        int result = low, ptr1 = 0, ptr2 = 0; //now visualize it as merging two sorted arrays and rewriting it into the original array
        int inversions = 0;
        while (ptr1 < n1 && ptr2 < n2) {
            int val1 = left[ptr1], val2 = right[ptr2];
            if (val1 > val2) {
                //inversion found
                inversions++;
                a[result++] = val2;
                ptr2++;
            } else {
                //this is important as we need to carry forward the inversions ie. if left array is [3,5,8...] and there are 4 elements smaller than 3
                //those 4 elements will be smaller than 5 as well hence we need not reset the inversions to 0
                globalInversions += inversions;
                a[result++] = val1; //rewrite it in the original array
                ptr1++;
            }
        }
        //copy remaining part
        while (ptr1 < n1) {
            a[result++] = left[ptr1++];
            globalInversions += inversions; //remember to update global inversions here as well.
        }
        while (ptr2 < n2) {
            a[result++] = right[ptr2++];
        }
    }
}
