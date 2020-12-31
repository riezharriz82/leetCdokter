import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/snapshot-array/
 * <p>
 * Implement a SnapshotArray that supports the following interface:
 * <p>
 * SnapshotArray(int length) initializes an array-like data structure with the given length.  Initially, each element equals 0.
 * void set(index, val) sets the element at the given index to be equal to val.
 * int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
 * int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id
 * <p>
 * Input: ["SnapshotArray","set","snap","set","get"]
 * [[3],[0,5],[],[0,6],[0,0]]
 * Output: [null,null,0,null,5]
 * Explanation:
 * SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
 * snapshotArr.set(0,5);  // Set array[0] = 5
 * snapshotArr.snap();  // Take a snapshot, return snap_id = 0
 * snapshotArr.set(0,6);
 * snapshotArr.get(0,0);  // Get the value of array[0] with snap_id = 0, return 5
 * <p>
 * Constraints:
 * 1 <= length <= 50000
 * At most 50000 calls will be made to set, snap, and get.
 * 0 <= index < length
 * 0 <= snap_id < (the total number of times we call snap())
 * 0 <= val <= 10^9
 */
public class SnapshotArray {
    Map<Pair<Integer, Integer>, Integer> map = new HashMap<>(); //(index, snapId) -> value
    int curSnapId;

    /**
     * Approach: Can be solved using couple of approaches
     * 1. My initial approach was to use a map of (index, snap_id) -> value. When a get is called with the provided snap_id,
     * it might be possible that there are no changes in the snap_id, so we need to go down till 0 and see if we can find the
     * first (index, snap_id) that is present in the map, else no
     * TimeComplexity of get() will be O(total_snaps) and set will be O(1)
     * <p>
     * 2. For each index, maintain a treemap of snap_id -> value and for each set(), add the corresponding pair to the index
     * For each get(), perform a floor() to find a snap_id <= provided snap_id and return the value
     * TimeComplexity: get() will be O(log(total_snaps) and for set will be O(log(total_snaps))
     * <p>
     * Need to explain the trade offs properly to interviewer for full marks
     */
    public SnapshotArray(int length) {

    }

    public void set(int index, int val) {
        map.put(new Pair<>(index, curSnapId), val); //store the current index, curSnapId
        //in case of multiple overwrites for the same index in the same snapId, it will be safely overwritten
    }

    public int snap() {
        return curSnapId++;
    }

    public int get(int index, int snap_id) {
        int temp = snap_id;
        //iterate down till 0 until we find a valid snap_id for the given index
        while (temp >= 0 && !map.containsKey(new Pair<>(index, temp))) {
            temp--;
        }
        return map.getOrDefault(new Pair<>(index, temp), 0);
    }
}
