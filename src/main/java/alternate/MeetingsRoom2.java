package alternate;

import common.Interval;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

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
     * {@see CarPooling} for solving it in other way
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
}
