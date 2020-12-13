import java.util.*;

/**
 * https://leetcode.com/problems/insert-delete-getrandom-o1/
 * <p>
 * Design a data structure that supports all following operations in average O(1) time.
 * <p>
 * insert(val): Inserts an item val to the set if not already present.
 * remove(val): Removes an item val from the set if present.
 * getRandom: Returns a random element from current set of elements
 * (it's guaranteed that at least one element exists when this method is called). Each element must have the same probability of being returned.
 */
public class InsertDeleteRandomConstantTime {
    Map<Integer, Integer> valueToIndexMapping = new HashMap<>();
    List<Integer> values = new ArrayList<>();

    public InsertDeleteRandomConstantTime() {

    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (valueToIndexMapping.containsKey(val)) {
            return false;
        } else {
            valueToIndexMapping.put(val, values.size());
            values.add(val);
            return true;
        }
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (!valueToIndexMapping.containsKey(val)) {
            return false;
        } else {
            int index = valueToIndexMapping.get(val);
            valueToIndexMapping.remove(val);
            //please note that deletion of any index other than last index in an arraylist takes linear time, hence we swap the
            //non-last index with the last index and always delete only the last index
            //this ensures o(1) time deletion
            if (index != values.size() - 1) { //if it's not the last element to be deleted
                //swap the last value and update its index in the mapping
                values.set(index, values.get(values.size() - 1));
                valueToIndexMapping.put(values.get(values.size() - 1), index);
            }
            values.remove(values.size() - 1); //remove the last value as it has already been swapped, allows o(1) time deletion
            return true;
        }
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        return values.get(new Random().nextInt(values.size()));
    }
}
