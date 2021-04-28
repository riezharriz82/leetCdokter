import java.util.Random;

/**
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 * <p>
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * <p>
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 * <p>
 * Input: [3,2,3,1,2,4,5,5,6] and k = 4
 * Output: 4
 * <p>
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
public class KthLargestElementInArray {
    /**
     * Approach: TextBook Quick Select algorithm, since we are only interested in kth largest element, there is no point in using priority queue
     * as it would give us k smallest elements with time complexity of nlogk
     * <p>
     * We can optimize the time complexity to average case O(n) and worst case O(n^2) using quick select partitioning algorithm used in quick sort
     * To solve in worst case O(n) need to use median of median algorithm.
     * <p>
     * PS. Don't pick the last element as the pivot index, instead pick a random element between [low, high]
     * This step took a significant amount of time, getting the indexes correct.
     * <p>
     * {@link TopKFrequentElements} {@link ThirdMaximumNumber} {@link ShuffleArray}
     */
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        int targetIndex = n - k; //we have to find kth largest element, so we will transform it into (n-k)th smallest element (0 based indexing)
        return quickSelect(nums, 0, n - 1, targetIndex);
    }

    private int quickSelect(int[] nums, int low, int high, int targetIndex) {
        int pivotIndex = new Random().nextInt(high - low + 1) + low; //nextInt(10) will give a random number between [0,9]
        //but if our range was [15,24], we need to offset it by + low
        //we are choosing random pivot to reduce our worst case time complexity
        int smallestIndex = low, index = low;
        swap(nums, pivotIndex, high); //after choosing the pivot, we need to swap it with the last element
        //now the pivot will be the last index or nums[high]
        while (index < high) {
            if (nums[index] <= nums[high]) { //found a number smaller than pivot
                //swap nums[index] with nums[smallestIndex]
                swap(nums, index, smallestIndex);
                smallestIndex++;
            }
            index++;
        }
        swap(nums, smallestIndex, high); //swap the pivot to an index > all elements smaller than pivot
        if (smallestIndex == targetIndex) {
            return nums[smallestIndex];
        } else if (smallestIndex < targetIndex) {
            //we are interested in right part
            return quickSelect(nums, smallestIndex + 1, high, targetIndex);
        } else {
            //we are interested in left part
            return quickSelect(nums, low, smallestIndex - 1, targetIndex);
        }
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}