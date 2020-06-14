/**
 * https://leetcode.com/problems/merge-sorted-array/
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
