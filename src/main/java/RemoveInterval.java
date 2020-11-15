import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/remove-interval/ Premium
 * <p>
 * Given a sorted list of disjoint intervals, each interval intervals[i] = [a, b] represents the set of real numbers x such that a <= x < b.
 * <p>
 * We remove the intersections between any interval in intervals and the interval toBeRemoved.
 * <p>
 * Return a sorted list of intervals after all such removals.
 * <p>
 * Input: intervals = [[0,2],[3,4],[5,7]], toBeRemoved = [1,6]
 * Output: [[0,1],[6,7]]
 * <p>
 * Input: intervals = [[0,5]], toBeRemoved = [2,3]
 * Output: [[0,2],[3,5]]
 * <p>
 * Input: intervals = [[-5,-4],[-3,-2],[1,2],[3,5],[8,9]], toBeRemoved = [-1,4]
 * Output: [[-5,-4],[-3,-2],[4,5],[8,9]]
 */
public class RemoveInterval {
    /**
     * Approach: Interval problem similar to {@link InsertInterval} {@link RemoveCoveredIntervals} {@link FindRightInterval}
     * <p>
     * Initially I started solving using binary search to find the left index and create multiple splits but the implementation
     * got tricky due to handling of various edge cases
     * <p>
     * Saw the hint of considering each interval one by one and split it accordingly, then was able to implement it and got AC.
     * However the initial implementation was a bit crude, current implementation is a bit simplified
     */
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        for (int[] interval : intervals) {
            if (interval[1] < toBeRemoved[0] || interval[0] > toBeRemoved[1]) {
                //if the interval is completely on the left of the target interval or completely on the right of it
                //PS: Don't need to compare both ends of interval to decide whether it's completely on the left/right.
                result.add(Arrays.asList(interval[0], interval[1]));
            } else {
                if (interval[0] < toBeRemoved[0]) { //if interval overlaps with the left of the target interval
                    result.add(Arrays.asList(interval[0], toBeRemoved[0]));
                }
                //notice the if and not else if, this is to handle cases where the interval is bigger than the target interval
                // and hence overlaps from both left and right
                if (interval[1] > toBeRemoved[1]) { //if interval overlaps with the right portion of the target interval
                    result.add(Arrays.asList(toBeRemoved[1], interval[1]));
                }
            }
        }
        return result;
    }
}
