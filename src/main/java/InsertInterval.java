import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * https://leetcode.com/problems/insert-interval/
 * <p>
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * <p>
 * You may assume that the intervals were initially sorted according to their start times.
 * <p>
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 * <p>
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 */
public class InsertInterval {
    /**
     * {@link MergeIntervals} is highly correlated
     * Add the newInterval to the list of intervals and sort them based on the start time.
     * Now start merging overlapping intervals, similar to the linked code.
     * This is a non-optimal solution but didn't took me time to implement.
     * <p>
     * Linear solution would be to divide the intervals in three parts
     * 1. All intervals that end before the target interval starts
     * 2. All intervals that overlaps with the target
     * 3. All intervals that starts after the overlapping intervals have been adjusted.
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int[][] union = new int[intervals.length + 1][2];
        int index = 0;
        for (int[] interval : intervals) {
            union[index++] = interval;
        }
        union[index] = newInterval; //add the target interval
        Arrays.sort(union, Comparator.comparingInt(o -> o[0]));

        List<int[]> result = new ArrayList<>();
        result.add(union[0]);
        for (int i = 1; i < union.length; i++) {
            int[] lastInterval = result.get(result.size() - 1);
            if (union[i][0] > lastInterval[1]) { //non overlapping found
                result.add(union[i]);
            } else if (union[i][0] <= lastInterval[1]) { //overlapping interval found
                result.set(result.size() - 1, new int[]{lastInterval[0], Math.max(lastInterval[1], union[i][1])});
            }
        }
        return result.toArray(new int[result.size()][2]);
    }
}
