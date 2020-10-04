import common.Interval;

import java.util.*;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * find the minimum number of conference rooms required.
 * <p>
 * For example,
 * Given [[0, 30],[5, 10],[15, 20]],
 * return 2.
 */
public class MeetingsRoom2 {

    /**
     * Approach: It's critical to understand why we chose priority queue instead of simply comparing the start time of current interval with
     * end time of previous interval e.g {2,8},{5,15},{10,20}
     * we need to remove all intervals ending before the start of current interval, we can do that by priority queue.
     * <p>
     * {@link CarPooling} for similar problem
     */
    public int minMeetingRooms(Interval[] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a.start));
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.add(intervals[0].end); //we are only interested in end time
        for (int i = 1; i < intervals.length; ++i) {
            if (minHeap.peek() <= intervals[i].start) {
                //if the current interval starts after any finished meeting, remove the finished meeting
                //any part is important, that's why we used priority queue, not stack
                minHeap.poll();
            }
            minHeap.add(intervals[i].end);
        }
        return minHeap.size();
    }

    public int minMeetingRooms(int[][] intervals) {
        int max = 0, cur = 0;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int[] interval : intervals) {
            map.put(interval[0], map.getOrDefault(interval[0], 0) + 1);
            map.put(interval[1], map.getOrDefault(interval[1], 0) - 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            cur += entry.getValue();
            max = Math.max(cur, max);
        }
        return max;
    }
}
