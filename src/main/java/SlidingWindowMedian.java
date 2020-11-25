import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/sliding-window-median/
 * <p>
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value.
 * So the median is the mean of the two middle value.
 * <p>
 * [2,3,4] , the median is 3
 * <p>
 * [2,3], the median is (2 + 3) / 2 = 2.5
 * <p>
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
 * You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 * Your job is to output the median array for each window in the original array.
 * <p>
 * For example,
 * Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 * <pre>
 * Window position                Median
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       1
 *  1 [3  -1  -3] 5  3  6  7       -1
 *  1  3 [-1  -3  5] 3  6  7       -1
 *  1  3  -1 [-3  5  3] 6  7       3
 *  1  3  -1  -3 [5  3  6] 7       5
 *  1  3  -1  -3  5 [3  6  7]      6
 * Therefore, return the median sliding window as [1,-1,-1,3,5,6].
 * </pre>
 * Note:
 * You may assume k is always valid, ie: k is always smaller than input array's size for non-empty array.
 * Answers within 10^-5 of the actual value will be accepted as correct.
 */
public class SlidingWindowMedian {
    /**
     * Approach: Need to keep 2 priority queues, one for keeping the left sorted half, other for right sorted half
     * Left should be max priority queue, right should be min priority queue
     * In case of even k median will be the average of top of both of the queues
     * In case of odd k, median will be the top of left queue
     * <p>
     * Imagine a sorted array of size 10, left queue will contain the first 5 elements, right queue will contain remaining 5
     * ie. largest element of left queue <= smallest element of right queue
     * In case of sorted array of size 9, left will contain the first 5 elements and right will contain the remaining 4
     * <p>
     * Left queue can have max 1 element more than the right queue.
     * If left queue has 2 element more than the right queue, need to rebalance it by popping from left and pushing to right
     * If right has 1 more element than left, rebalance by popping from right and pushing to left
     * <p>
     * Since we have to remove elements falling out of the window, pq.remove() takes o(n) time whereas treeMap supports
     * deletion in log(n) time, so instead of pq we will use treeMap
     */
    public double[] medianSlidingWindow(int[] nums, int k) {
        //map of values and their frequency of occurrence
        TreeMap<Integer, Integer> left = new TreeMap<>((o1, o2) -> Integer.compare(o2, o1));
        TreeMap<Integer, Integer> right = new TreeMap<>();
        List<Double> medians = new ArrayList<>();
        int leftSize = 0, rightSize = 0; //need to explicitly handle the no of elements in map because map.size() will return unique keys
        //but we need to consider the frequency of each key as well
        for (int i = 0; i < nums.length; i++) {
            if (left.isEmpty() || nums[i] <= left.firstKey()) { //if current element is <= the largest element in left
                left.put(nums[i], left.getOrDefault(nums[i], 0) + 1);
                leftSize++;
            } else {
                right.put(nums[i], right.getOrDefault(nums[i], 0) + 1);
                rightSize++;
            }
            if (i >= k) { //sliding window
                int outsideWindow = nums[i - k];
                if (outsideWindow <= left.firstKey()) { //if the no going out of the window belongs to the first half
                    left.put(outsideWindow, left.get(outsideWindow) - 1);
                    left.remove(outsideWindow, 0);
                    leftSize--;
                } else {
                    right.put(outsideWindow, right.get(outsideWindow) - 1);
                    right.remove(outsideWindow, 0);
                    rightSize--;
                }
            }
            //rebalance if required
            if (leftSize - rightSize > 1) {//if left has 2 more element than right
                int largestVal = left.firstKey();
                left.put(largestVal, left.get(largestVal) - 1);
                left.remove(largestVal, 0);
                leftSize--;
                right.put(largestVal, right.getOrDefault(largestVal, 0) + 1);
                rightSize++;
            } else if (rightSize - leftSize >= 1) { //if right has 1 more element than left
                int smallestVal = right.firstKey();
                right.put(smallestVal, right.get(smallestVal) - 1);
                right.remove(smallestVal, 0);
                rightSize--;
                left.put(smallestVal, left.getOrDefault(smallestVal, 0) + 1);
                leftSize++;
            }
            if (i >= k - 1) { //update the medians list
                if (k % 2 == 0) {
                    medians.add((left.firstKey() / 2.0) + (right.firstKey() / 2.0)); //not (a+b)/2 because a + b can overflow :(
                } else {
                    medians.add((double) left.firstKey());
                }
            }
        }
        double[] result = new double[medians.size()];
        for (int i = 0; i < medians.size(); i++) {
            result[i] = medians.get(i);
        }
        return result;
    }
}
