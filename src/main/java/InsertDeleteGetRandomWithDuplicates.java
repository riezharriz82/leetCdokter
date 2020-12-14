import java.util.*;

/**
 * https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/
 * <p>
 * Design a data structure that supports all following operations in average O(1) time.
 * <p>
 * Note: Duplicate elements are allowed.
 * insert(val): Inserts an item val to the collection.
 * remove(val): Removes an item val from the collection if present.
 * getRandom: Returns a random element from current collection of elements. The probability of each element being returned is linearly related to the number of same value the collection contains.
 * <p>
 * // Init an empty collection.
 * RandomizedCollection collection = new RandomizedCollection();
 * <p>
 * // Inserts 1 to the collection. Returns true as the collection did not contain 1.
 * collection.insert(1);
 * <p>
 * // Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
 * collection.insert(1);
 * <p>
 * // Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
 * collection.insert(2);
 * <p>
 * // getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
 * collection.getRandom();
 * <p>
 * // Removes 1 from the collection, returns true. Collection now contains [1,2].
 * collection.remove(1);
 * <p>
 * // getRandom should return 1 and 2 both equally likely.
 * collection.getRandom();
 */
public class InsertDeleteGetRandomWithDuplicates {
    Map<Integer, Set<Integer>> valueToIndexMapping = new HashMap<>();
    List<Integer> indices = new ArrayList<>();

    /**
     * Approach: List already provides addition and getRandom() capability in constant time but to delete element, we need to
     * find the index in which it's present.
     * So we keep a map of val to list of indices where the val occurs so that we can perform deletion in constant time
     * <p>
     * In order to maintain O(1) deletion time, need to swap out the element at the last index to the index we are deleting because
     * deleting the last index is constant time
     * <p>
     * While swapping out elements, need to be very careful with correctly deleting the elements. Need to have special condition
     * when the element swapped out occurs at the last index itself
     * <p>
     * {@link InsertDeleteRandomConstantTime} related problem
     */
    public InsertDeleteGetRandomWithDuplicates() {

    }

    /**
     * Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (valueToIndexMapping.containsKey(val)) {
            valueToIndexMapping.get(val).add(indices.size());
            indices.add(val);
            return false;
        } else {
            valueToIndexMapping.computeIfAbsent(val, __ -> new HashSet<>()).add(indices.size());
            indices.add(val);
            return true;
        }
    }

    /**
     * Removes a value from the collection. Returns true if the collection contained the specified element.
     */
    public boolean remove(int val) {
        if (valueToIndexMapping.containsKey(val)) {
            int indexOfVal = valueToIndexMapping.get(val).iterator().next();
            int valueAtLastIndex = indices.get(indices.size() - 1);
            if (valueAtLastIndex == val) {
                //if val is present at the last index, then no need to perform swap, directly delete the last index
                valueToIndexMapping.get(val).remove(indices.size() - 1);
                valueToIndexMapping.remove(val, new HashSet<Integer>());
                indices.remove(indices.size() - 1);
            } else {
                //swap out the element at last index to index of val, be very careful in updating the indices
                valueToIndexMapping.get(valueAtLastIndex).remove(indices.size() - 1);
                valueToIndexMapping.get(valueAtLastIndex).add(indexOfVal);
                indices.set(indexOfVal, valueAtLastIndex);
                valueToIndexMapping.get(val).remove(indexOfVal);
                valueToIndexMapping.remove(val, new HashSet<Integer>()); //remove val from map if there are no indices left
                indices.remove(indices.size() - 1);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get a random element from the collection.
     */
    public int getRandom() {
        return indices.get(new Random().nextInt(indices.size()));
    }
}
