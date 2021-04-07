import java.util.Map;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/data-stream-as-disjoint-intervals/
 * <p>
 * Given a data stream input of non-negative integers a1, a2, ..., an, ..., summarize the numbers seen so far as a list of disjoint intervals.
 * <p>
 * For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:
 * <p>
 * [1, 1]
 * [1, 1], [3, 3]
 * [1, 1], [3, 3], [7, 7]
 * [1, 3], [7, 7]
 * [1, 3], [6, 7]
 * <p>
 * Follow up:
 * What if there are lots of merges and the number of disjoint intervals are small compared to the data stream's size?
 */
public class DataStreamAsDisjointIntervals {
    TreeMap<Integer, int[]> map = new TreeMap<>();

    /**
     * Approach: In my initial implementation, I solved it by maintaining all the added numbers in a treeSet and then iterated over the
     * entire treeSet to find consecutive ranges i.e if {3,4,5} is present, add [3,5] to the result and start next search from key = higher(5)
     * TimeComplexity: O(logn) during add() and O(n) during get()
     * <p>
     * To reduce time complexity, we have to maintain the list of interval already covered and reuse/extend that result when adding new number
     * The invariant here is to not have any overlapping interval before or after adding a new number
     * TimeComplexity: O(logn) during add() and O(number of disjoint intervals) during get()
     * <p>
     * {@link RangeModule} {@link RemoveInterval} {@link MyCalendar1}
     */
    public DataStreamAsDisjointIntervals() {
    }

    public void addNum(int val) {
        if (!map.containsKey(val)) { //skip processing already added number
            Map.Entry<Integer, int[]> lower = map.lowerEntry(val);
            Map.Entry<Integer, int[]> higher = map.higherEntry(val);
            if (lower != null && higher != null && lower.getValue()[1] + 1 == val && higher.getKey() == val + 1) {
                //if current value merges two disjoint intervals in left and right, remove the right interval and extend the left interval
                map.remove(higher.getKey());
                map.put(lower.getKey(), new int[]{lower.getValue()[0], higher.getValue()[1]});
            } else if (lower != null && lower.getValue()[1] + 1 >= val) {
                //if val can extend the left interval ie. [3,5] and val=6, then update to [3,6]
                //if val lies in between the left interval ie. [3,8] and val=6, then do nothing
                map.put(lower.getKey(), new int[]{lower.getValue()[0], Math.max(val, lower.getValue()[1])});
            } else if (higher != null && higher.getKey() == val + 1) {
                //if val can extend an interval on the right ie. val=6 and [7,10], then remove [7,10] and insert [6,10]
                map.remove(higher.getKey());
                map.put(val, new int[]{val, higher.getValue()[1]});
            } else {
                //insert a new disjoint interval, if everything fails, this should be at the end, instead of the first
                //because it might be possible that lower and higher exists but still val is a disjoint interval
                map.put(val, new int[]{val, val});
            }
        }
    }

    public int[][] getIntervals() {
        return map.values().toArray(new int[0][2]);
    }
}
