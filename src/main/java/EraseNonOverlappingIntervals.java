import common.Interval;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.com/problems/non-overlapping-intervals/
 * <p>
 * Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 * <p>
 * Input: [[1,2],[2,3],[3,4],[1,3]]
 * Output: 1
 * Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
 */
public class EraseNonOverlappingIntervals {
    //Same approach as my original approach, but the logic is more simplified as we are sorting by end time
    // pick the interval ending first and only choose next interval that starts after this ends (non-overlapping)
    // since we are picking the interval that finishes early, first; we are bound to get the maximum no of non-overlapping intervals
    public int eraseOverlapIntervalsSimplified(int[][] intervals) {
        int n = intervals.length;
        if (n == 0) {
            return 0;
        }
        Interval[] input = new Interval[intervals.length];
        for (int i = 0; i < n; i++) {
            input[i] = new Interval(intervals[i][0], intervals[i][1]);
        }
        Arrays.sort(input, Comparator.comparingInt(o -> o.end)); //sort by end
        //Result will store all the non-overlapping intervals
        Interval[] result = new Interval[n];
        int index = 0;
        result[index++] = input[0];
        for (int i = 1; i < n; i++) {
            if (input[i].start >= result[index - 1].end) {
                result[index++] = input[i];
            }
        }
        //Total intervals - non-overlapping intervals will give overlapping intervals
        return n - index;
    }

    /**
     * My initial greedy approach: Idea is to start similar to {@link MergeIntervals} and always try to pick smallest interval
     * <pre>
     * 1-----------------10
     *     4---6
     * </pre>
     * Pick 4-6 instead of 1-10 as picking a smaller interval can lead to picking more non-overlapping intervals
     * If you pick a bigger range, no way you can pick more non-overlapping intervals
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
        if (n == 0) {
            return 0;
        }
        Interval[] input = new Interval[intervals.length];
        for (int i = 0; i < n; i++) {
            input[i] = new Interval(intervals[i][0], intervals[i][1]);
        }
        Arrays.sort(input, Comparator.comparingInt(o -> o.start)); //sort by start
        //Result will store all the non-overlapping intervals
        Interval[] result = new Interval[n];
        int index = 0;
        result[index++] = input[0];
        for (int i = 1; i < n; i++) {
            if (input[i].start < result[index - 1].end && input[i].end < result[index - 1].end) {
                //if this interval is smaller than the last interval i.e. lies completely within the previous interval
                // replace the last interval with this interval in order to pick more non-overlapping intervals
                result[index - 1] = input[i];
            } else if (input[i].start >= result[index - 1].end) {
                //non-overlapping interval, just add to the result
                result[index++] = input[i];
            }
        }
        //Total intervals - non-overlapping intervals will give overlapping intervals
        return n - index;
    }
}
