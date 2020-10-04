import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * https://leetcode.com/problems/merge-intervals/
 * Given a collection of intervals, merge all overlapping intervals.
 * <pre>
 * Example 1:
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * Example 2:
 *
 * Input: [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 * </pre>
 */
public class MergeIntervals {
    /**
     * Approach: Process intervals one by one
     * Sort by start time, if the new interval starts before the last processed interval ends, we have an overlap.
     * The new right boundary will be the max of end time of both the intervals.
     */
    public int[][] mergeSimplified(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            if (i == 0 || res.get(res.size() - 1)[1] < intervals[i][0]) { //if there is no overlap between the last merged element and the current
                res.add(intervals[i]);
            } else {
                int[] lastElement = res.get(res.size() - 1);
                res.set(res.size() - 1, new int[]{lastElement[0], Math.max(lastElement[1], intervals[i][1])});
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
