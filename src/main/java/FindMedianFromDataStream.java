import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/find-median-from-data-stream/
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
 * <p>
 * For example,
 * [2,3,4], the median is 3
 * <p>
 * [2,3], the median is (2 + 3) / 2 = 2.5
 * <p>
 * Design a data structure that supports the following two operations:
 * <p>
 * void addNum(int num) - Add a integer number from the data stream to the data structure.
 * double findMedian() - Return the median of all elements so far.
 * <p>
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 * <p>
 * Follow up:
 * If all integer numbers from the stream are between 0 and 100, how would you optimize it?
 * If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
 */
public class FindMedianFromDataStream {
    PriorityQueue<Integer> maxQ = new PriorityQueue<>(Comparator.reverseOrder());
    PriorityQueue<Integer> minQ = new PriorityQueue<>();

    public FindMedianFromDataStream() {

    }

    /**
     * Approach: 2 Priority queues similar to previously solved {@link SlidingWindowMedian}
     * Since we don't need to remove elements priority queue would suffice, make sure to rebalance heap after adding the num
     * <p>
     * Left heap should have at most 1 more elements than right heap
     * Right heap should not have more elements than left heap
     */
    public void addNum(int num) {
        if (maxQ.isEmpty() || num <= maxQ.peek()) {
            maxQ.add(num);
        } else {
            minQ.add(num);
        }
        if (maxQ.size() - minQ.size() > 1) {
            minQ.add(maxQ.poll());
        } else if (minQ.size() - maxQ.size() >= 1) {
            maxQ.add(minQ.poll());
        }
    }

    public double findMedian() {
        if (maxQ.size() > minQ.size()) { //if left has more elements than right, then currently we have odd numbers
            return (double) maxQ.peek();
        } else { //even numbers present, take the average
            return (maxQ.peek() / 2.0) + (minQ.peek() / 2.0);
        }
    }
}