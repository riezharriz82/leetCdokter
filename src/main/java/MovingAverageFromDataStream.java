import java.util.ArrayDeque;

/**
 * https://leetcode.com/problems/moving-average-from-data-stream/
 * <p>
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 * <p>
 * MovingAverage m = new MovingAverage(3);
 * m.next(1) = 1
 * m.next(10) = (1 + 10) / 2
 * m.next(3) = (1 + 10 + 3) / 3
 * m.next(5) = (10 + 3 + 5) / 3
 */
public class MovingAverageFromDataStream {
    ArrayDeque<Integer> queue = new ArrayDeque<>();
    int curSum;
    int maxSize;

    public MovingAverageFromDataStream(int size) {
        maxSize = size;
    }

    /**
     * Approach: Keep a deque for placing the required elements of the window, if queue is at it's max capacity,
     * remove the first element and add the new element to the tail.
     * Keep the current sum handy to be able to create a rolling sum of the new window
     * <p>
     * Instead of dequeue, another approach would be to use circular array of required size. Use two pointers head/tail.
     * tail is one step ahead of head.
     * increment the tail.
     * Remove the element at tail from windowSum
     * Add the element at the head.
     * Increment the head.
     * use modular arithmetic while incrementing head/tail (head + 1)%size
     */
    public double next(int val) {
        if (queue.size() < maxSize) {
            queue.addLast(val);
            curSum += val;
        } else { //queue is at its capacity, need to remove oldest element at the head of the queue
            int head = queue.pollFirst();
            curSum -= head;
            queue.addLast(val);
            curSum += val;
        }
        return curSum / (double) queue.size();
    }
}
