import javafx.util.Pair;

import java.util.ArrayDeque;

/**
 * https://leetcode.com/problems/design-hit-counter/ Premium
 * <pre>
 * Design a hit counter which counts the number of hits received in the past 5 minutes.
 *
 * Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made to the system
 * in chronological order (ie, the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1.
 *
 * It is possible that several hits arrive roughly at the same time.
 *
 * HitCounter counter = new HitCounter();
 *
 * // hit at timestamp 1.
 * counter.hit(1);
 *
 * // hit at timestamp 2.
 * counter.hit(2);
 *
 * // hit at timestamp 3.
 * counter.hit(3);
 *
 * // get hits at timestamp 4, should return 3.
 * counter.getHits(4);
 *
 * // hit at timestamp 300.
 * counter.hit(300);
 *
 * // get hits at timestamp 300, should return 4.
 * counter.getHits(300);
 *
 * // get hits at timestamp 301, should return 3.
 * counter.getHits(301);
 *
 * Follow up:
 * What if the number of hits per second could be very large? Does your design scale?
 * </pre>
 * Constraints:
 * 1 <= timestamp <= 2 * 10^9
 * All the calls are being made to the system in chronological order (i.e., timestamp is monotonically increasing).
 * At most 300 calls will be made to hit and getHits.
 */
public class DesignHitCounterSimplified {

    ArrayDeque<Pair<Integer, Integer>> deque = new ArrayDeque<>();

    /**
     * Approach: Since the timestamps to both hit() and getHits() are in sorted order, we can use deque as a sliding window to keep track
     * of frequencies of timestamps
     * <p>
     * {@link LoggerRateLimiter} {@link DesignHitCounter}
     */
    public DesignHitCounterSimplified() {

    }

    /**
     * Record a hit.
     *
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public void hit(int timestamp) {
        if (deque.isEmpty()) {
            deque.add(new Pair<>(timestamp, 1));
        } else if (deque.getLast().getKey().equals(timestamp)) {
            Pair<Integer, Integer> last = deque.removeLast();
            deque.add(new Pair<>(last.getKey(), last.getValue() + 1));  //increment the frequency of last element
            //this allows us to coalesce duplicate values and reduce memory footprint
        } else {
            deque.add(new Pair<>(timestamp, 1)); //new value found
        }
    }

    /**
     * Return the number of hits in the past 5 minutes.
     *
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public int getHits(int timestamp) {
        while (!deque.isEmpty() && deque.getFirst().getKey() <= timestamp - 300) {
            deque.removeFirst();
        }
        int totalFrequencies = 0;
        for (Pair<Integer, Integer> elements : deque) {
            totalFrequencies += elements.getValue();
        }
        return totalFrequencies;
    }
}
