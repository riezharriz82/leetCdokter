import java.util.Map;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/range-module/
 * <p>
 * A Range Module is a module that tracks ranges of numbers. Your task is to design and implement the following interfaces in an efficient manner.
 * <p>
 * addRange(int left, int right) Adds the half-open interval [left, right), tracking every real number in that interval. Adding an interval that
 * partially overlaps with currently tracked numbers should add any numbers in the interval [left, right) that are not already tracked.
 * queryRange(int left, int right) Returns true if and only if every real number in the interval [left, right) is currently being tracked.
 * removeRange(int left, int right) Stops tracking every real number currently being tracked in the interval [left, right).
 * <p>
 * addRange(10, 20): null
 * removeRange(14, 16): null
 * queryRange(10, 14): true (Every number in [10, 14) is being tracked)
 * queryRange(13, 15): false (Numbers like 14, 14.03, 14.17 in [13, 15) are not being tracked)
 * queryRange(16, 17): true (The number 16 in [16, 17) is still being tracked, despite the remove operation)
 */
public class RangeModule {
    TreeMap<Integer, Integer> map = new TreeMap<>(); //map of start time, end time

    /**
     * Approach: Use ordered map to keep track of ranges, when adding a range, remove all the ranges that overlaps with the [left, right)
     * <pre>
     *      --------------------- (range to add)
     * ----------   -------  ---------------- (Existing ranges)
     * -------------------------------------- (Final map after merging)
     * </pre>
     * While removing the ranges, keep track of the new left and right boundary, it will be used to create a new range
     * <p>
     * {@link EmployeeFreeTime}
     */
    public RangeModule() {

    }

    /**
     * {@link MergeIntervals} Merge overlapping intervals
     */
    public void addRange(int left, int right) {
        while (true) {
            Map.Entry<Integer, Integer> floorEntry = map.floorEntry(right);
            if (floorEntry == null || floorEntry.getValue() < left) {
                //no overlap found
                break;
            }
            left = Math.min(floorEntry.getKey(), left); //keep track of the new left
            right = Math.max(floorEntry.getValue(), right); //keep track of the new right
            map.remove(floorEntry.getKey()); //remove overlapping intervals
        }
        map.put(left, right); //add the final coordinates that is a result of merging overlapped intervals
    }

    public boolean queryRange(int left, int right) {
        Map.Entry<Integer, Integer> floorEntry = map.floorEntry(left);
        return floorEntry != null && floorEntry.getValue() >= right;
    }

    /**
     * <pre>
     * ---------- --  ------------ existing range
     *        ----------  range to be removed
     * --------------------------- merged range
     * -------          ----------  final range
     * </pre>
     * {@link RemoveInterval} {@link RemoveCoveredIntervals}
     */
    public void removeRange(int left, int right) {
        addRange(left, right); //first add the range to simplify the code, then create splits
        Map.Entry<Integer, Integer> floorEntry = map.floorEntry(left);
        map.remove(floorEntry.getKey()); //remove the complete overlapped interval
        if (floorEntry.getKey() < left) {
            map.put(floorEntry.getKey(), left); //insert back the slice that is on the left of range that needs to be removed
        }
        if (floorEntry.getValue() > right) {
            map.put(right, floorEntry.getValue()); //insert back the slice that is on the right of range that needs to be removed
        }
    }
}
