/**
 * https://leetcode.com/problems/merge-sorted-array/
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * <p>
 * Note:
 * <p>
 * The number of elements initialized in nums1 and nums2 are m and n respectively.
 * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
 * Example:
 * <p>
 * Input:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * <p>
 * Output: [1,2,2,3,5,6]
 */
public class MergeSortedArray {
    public void mergeByComparingElementsFromTheEndRatherThanFront(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) {
            return;
        }
        int i = m - 1, j = n - 1, k = nums1.length - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] <= nums2[j]) {
                nums1[k--] = nums2[j--];
            } else if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            }
        }
        while (j >= 0) { //copy remaining nums2
            nums1[k--] = nums2[j--];
        }
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) {
            return;
        } else if (m == 0) {
            for (int i = 0; i < n; i++) {
                nums1[i] = nums2[i];
            }
            return;
        }
        //shift numbers present in nums1 to the end
        int k = 0;
        for (int i = m - 1; i >= 0; i--) {
            nums1[nums1.length - 1 - k++] = nums1[i];
        }
        int i = nums1.length - k, j = 0, index = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                nums1[index++] = nums1[i++];
            } else if (nums1[i] > nums2[j]) {
                nums1[index++] = nums2[j++];
            } else {
                nums1[index++] = nums1[i++];
                nums1[index++] = nums2[j++];
            }
        }
        while (i < nums1.length) {
            nums1[index++] = nums1[i++];
        }
        while (j < nums2.length) {
            nums1[index++] = nums2[j++];
        }
    }
}
