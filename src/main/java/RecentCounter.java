import java.util.ArrayDeque;

/**
 * https://leetcode.com/problems/number-of-recent-calls/
 * <p>
 * You have a RecentCounter class which counts the number of recent requests within a certain time frame.
 * <p>
 * Implement the RecentCounter class:
 * <p>
 * RecentCounter() Initializes the counter with zero recent requests.
 * int ping(int t) Adds a new request at time t, where t represents some time in milliseconds, and returns the number of requests
 * that has happened in the past 3000 milliseconds (including the new request).
 * Specifically, return the number of requests that have happened in the inclusive range [t - 3000, t].
 * It is guaranteed that every call to ping uses a strictly larger value of t than the previous call.
 * <p>
 * Input
 * ["RecentCounter", "ping", "ping", "ping", "ping"]
 * [[], [1], [100], [3001], [3002]]
 * Output
 * [null, 1, 2, 3, 3]
 * <p>
 * Explanation
 * RecentCounter recentCounter = new RecentCounter();
 * recentCounter.ping(1);     // requests = [1], range is [-2999,1], return 1
 * recentCounter.ping(100);   // requests = [1, 100], range is [-2900,100], return 2
 * recentCounter.ping(3001);  // requests = [1, 100, 3001], range is [1,3001], return 3
 * recentCounter.ping(3002);  // requests = [1, 100, 3001, 3002], range is [2,3002], return 3
 */
public class RecentCounter {
    ArrayDeque<Integer> queue = new ArrayDeque<>();
    int MAX_DIFF = 3000;

    public RecentCounter() {

    }

    /**
     * Approach: Sliding Window, we need a data structure that allows us to remove element from the head and add element to the tail
     * Deque fits perfectly. After adding an element, check if the head needs to be trimmed.
     * Return the size of the queue after trimming because those are the number that fits in the current window.
     * <p>
     * In case the input was not sorted, we have to use treeMap data structure and keep removing the firstKey() that falls out of the range.
     */
    public int ping(int t) {
        if (queue.isEmpty()) {
            queue.add(t);
            return 1;
        } else {
            while (!queue.isEmpty() && t - queue.peekFirst() > MAX_DIFF) {
                queue.removeFirst();
            }
            queue.add(t);
            return queue.size();
        }
    }
}
