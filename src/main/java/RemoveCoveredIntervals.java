import java.util.Arrays;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/remove-covered-intervals/
 * <p>
 * Given a list of intervals, remove all intervals that are covered by another interval in the list.
 * <p>
 * Interval [a,b) is covered by interval [c,d) if and only if c <= a and b <= d.
 * <p>
 * After doing so, return the number of remaining intervals.
 * <p>
 * Input: intervals = [[1,4],[3,6],[2,8]]
 * Output: 2
 * Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.
 * <p>
 * Input: intervals = [[1,4],[2,3]]
 * Output: 1
 */
public class RemoveCoveredIntervals {
    /**
     * Approach: Similar to {@link MergeIntervals} but the condition to add a new interval has changed
     * Following intervals need to be added
     * <pre>
     * -------------
     *         ----------
     * </pre>
     */
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> Integer.compare(o1[0], o2[0]));
        LinkedList<int[]> result = new LinkedList<>();
        result.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            if ((intervals[i][0] > result.getLast()[1])
                    || (intervals[i][0] < result.getLast()[1] && intervals[i][1] > result.getLast()[1]) && intervals[i][0] > result.getLast()[0]) {
                //start of current interval should lie before the end of previous interval and end of current interval should lie after the end of previous interval
                //care must be taken for {1,2}, {1,4}
                result.add(intervals[i]);
            } else {
                result.set(result.size() - 1, new int[]{result.getLast()[0], Math.max(result.getLast()[1], intervals[i][1])});
            }
        }
        return result.size();
    }
}
