import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/count-of-smaller-numbers-after-self/
 * <p>
 * You are given an integer array nums and you have to return a new counts array.
 * The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 * <p>
 * Example:
 * <p>
 * Input: [5,2,6,1]
 * Output: [2,1,1,0]
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 */

public class CountofSmallerNumbersAfterSelf {
    /**
     * Approach: Use merge sort for problems that look for pairs (i,j) such that i < j and nums[i], nums[j] satisfy some constraint
     * The complicated part is the merge portion of the mergesort
     */
    public List<Integer> countSmaller(int[] nums) {
        Node[] nodes = new Node[nums.length];
        for (int i = 0; i < nums.length; i++) {
            nodes[i] = new Node(i, nums[i]); //need to store the indices as well in order to update the result
        }
        int[] count = new int[nums.length];
        mergeSort(nodes, count, 0, nums.length - 1);

        List<Integer> answer = new ArrayList<>();
        for (int i : count) {
            answer.add(i);
        }
        return answer;
    }

    private void mergeSort(Node[] nodes, int[] answer, int l, int r) {
        if (l < r) {
            int mid = (l + r) / 2;
            mergeSort(nodes, answer, l, mid);
            mergeSort(nodes, answer, mid + 1, r);
            merge(nodes, answer, l, mid, r);
        }
    }

    //create a copy of the left subarray and right subarray
    //now task is to merge two sorted arrays using two pointer approach
    //during merge, update the original array so that original array gets sorted
    private void merge(Node[] nodes, int[] answer, int l, int mid, int r) {
        //copy the left part
        int n1 = mid - l + 1;
        Node[] left = new Node[n1]; // +1 because it includes mid
        for (int i = 0; i < n1; i++) {
            left[i] = nodes[l + i];
        }
        //copy the right part
        int n2 = r - mid;
        Node[] right = new Node[n2];
        for (int i = 0; i < n2; i++) {
            right[i] = nodes[mid + i + 1]; //+1 because mid is already included in left
        }
        int smallerNumber = 0; //count of smaller number on the right
        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            if (left[i].val <= right[j].val) {
                answer[left[i].idx] += smallerNumber; //smaller number will get carry forwarded to the next element in the left subarray
                //{5,7}, {2,3,8} -> smallerNumber will be 2 for 5, which should be used for 7 as well because anything smaller than 5 would
                //definitely be smaller than 7
                //also notice that we are doing increment operation, because an index is evaluated multiple times in merge() operation
                //because it's part of the recursion, so we need to increment the result already stored from previous recursions
                nodes[k++] = left[i]; //update the original array, notice k was initialized from l
                i++;
            } else if (left[i].val > right[j].val) {
                smallerNumber++; //found a smaller number on the right
                nodes[k++] = right[j];
                j++;
            }
        }
        while (i < n1) { //copy remaining left and right array
            answer[left[i].idx] += smallerNumber;
            nodes[k++] = left[i++];
        }
        while (j < n2) {
            nodes[k++] = right[j++];
        }
    }

    private static class Node {
        public int idx;
        public int val;

        public Node(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }
}
