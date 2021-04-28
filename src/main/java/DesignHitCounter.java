import java.util.ArrayList;
import java.util.List;

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
public class DesignHitCounter {
    List<Integer> timestamps = new ArrayList<>();

    /**
     * <pre>
     * Approach: Binary search, store all the incoming hits into an array. Since the input is in sorted array, the array will be in sorted array.
     * We can use binary search to find the smallest index with value > t-300 and highest index with value <= t
     * Difference in this values is the number of hits in the past 300 seconds
     * Time complexity of getHits() would be log(n) and hit would be O(1)
     * Disadvantage of this approach is its memory requirement is high.
     *
     * In my initial quick implementation, I solved it by using treemap, store all the keys and their frequency in a treemap
     * and when asked to find getHits() for a timestamp, use subMap() to slice the map for the required time interval
     * Time Complexity of getHits() would be O(300) as we would have to iterate over 300 distinct elements and sum up their frequency
     * and hit would be O(logn)
     * Disadvantage of this approach is that it does not take into consideration that the input data is already sorted.
     *
     * The problem statement does not clarify that the timestamps to hit() and getHits() are in sorted order, so getHits(300) followed by getHits(200) is invalid
     * Given this assumption, we can use a dequeue to keep adding timestamps in hit() and during getHits() pop invalid elements from front of the queue
     * Result would be the size of the queue as it will contain only valid elements.
     * Disadvantage of this approach would be high memory consumption as if all values are repeated, we would insert duplicate values in the deque
     *
     * An optimized approach would be to put pair<integer, integer> in deque which coalesces duplicates by keeping track of their frequency
     * See {@link DesignHitCounterSimplified}
     * </pre>
     * {@link LoggerRateLimiter}
     */
    public DesignHitCounter() {

    }

    /**
     * Record a hit.
     *
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public void hit(int timestamp) {
        timestamps.add(timestamp);
    }

    /**
     * Return the number of hits in the past 5 minutes.
     *
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public int getHits(int timestamp) {
        int upperBoundIndex = upperBound(timestamp);
        int lowerBoundIndex = lowerBound(timestamp - 300);
        if (upperBoundIndex == -1 || lowerBoundIndex == -1) { //edge case
            return 0;
        }
        return upperBoundIndex - lowerBoundIndex + 1;
    }

    private int lowerBound(int timestamp) {
        //find lowest index with value > provided timestamp
        int low = 0, high = timestamps.size() - 1, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (timestamps.get(mid) > timestamp) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private int upperBound(int timestamp) {
        //find highest index with value <= provided timestamp
        int low = 0, high = timestamps.size() - 1, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (timestamps.get(mid) <= timestamp) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }
}
