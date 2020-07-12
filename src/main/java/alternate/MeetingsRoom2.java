package alternate;

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
    public int minMeetingRooms(Interval[] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a.start));
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.add(intervals[0].end);
        for (int i = 1; i < intervals.length; ++i) {
            if (minHeap.peek() <= intervals[i].start) {
                //if the current interval starts after any finished meeting, remove the finished meeting
                minHeap.poll();
            }
            minHeap.add(intervals[i].end);
        }

        return minHeap.size();
    }

    private class Interval {
        int start;
        int end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
