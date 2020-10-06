import javafx.util.Pair;

import java.util.*;

/**
 * https://leetcode.com/problems/logger-rate-limiter/ Premium
 * <p>
 * Design a logger system that receive stream of messages along with its timestamps, each message should be printed if and only if it is not printed in the last 10 seconds.
 * <p>
 * Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the given timestamp, otherwise returns false.
 * <p>
 * It is possible that several messages arrive roughly at the same time.
 * // logging string "foo" at timestamp 1
 * logger.shouldPrintMessage(1, "foo"); returns true;
 * <p>
 * // logging string "bar" at timestamp 2
 * logger.shouldPrintMessage(2,"bar"); returns true;
 * <p>
 * // logging string "foo" at timestamp 3
 * logger.shouldPrintMessage(3,"foo"); returns false;
 * <p>
 * // logging string "bar" at timestamp 8
 * logger.shouldPrintMessage(8,"bar"); returns false;
 * <p>
 * // logging string "foo" at timestamp 10
 * logger.shouldPrintMessage(10,"foo"); returns false;
 * <p>
 * // logging string "foo" at timestamp 11
 * logger.shouldPrintMessage(11,"foo"); returns true;
 */
public class LoggerRateLimiter {
    TreeMap<Integer, Set<String>> treeMap = new TreeMap<>();
    ArrayDeque<Pair<Integer, String>> queue = new ArrayDeque<>();
    Set<String> messages = new HashSet<>(); //not really required but helps to avoid iterating all the messages of 10 seconds

    /**
     * Approach: Leverage treeMap to return all messages present in the last 10 seconds
     * <p>
     * Learnings -- new treeMap api tailMap(), subMap(), headMap()
     */
    public boolean shouldPrintMessage(int timestamp, String message) {
        SortedMap<Integer, Set<String>> view = treeMap.tailMap(timestamp - 9);
        for (Map.Entry<Integer, Set<String>> entry : view.entrySet()) {
            if (entry.getValue().contains(message)) {
                return false;
            }
        }
        treeMap.computeIfAbsent(timestamp, __ -> new HashSet<>()).add(message);
        return true;
    }

    /**
     * Approach: Since the problem asked about values present in a window, sliding window is the typical way to solve it
     * <p>
     * {@link RecentCounter} for related sliding window problem
     */
    public boolean shouldPrintMessageSlidingWindow(int timestamp, String message) {
        if (queue.isEmpty()) {
            queue.add(new Pair<>(timestamp, message));
            messages.add(message);
            return true;
        } else {
            while (!queue.isEmpty() && queue.peekFirst().getKey() <= timestamp - 10) {
                messages.remove(queue.peekFirst().getValue());
                queue.pollFirst();
            }
            if (messages.contains(message)) {
                return false;
            }
            queue.add(new Pair<>(timestamp, message));
            messages.add(message);
            return true;
        }
    }
}
