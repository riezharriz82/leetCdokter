/**
 * https://leetcode.com/problems/design-hashset/
 * Design a HashSet without using any built-in hash table libraries.
 * <p>
 * To be specific, your design should include these functions:
 * <p>
 * add(value): Insert a value into the HashSet.
 * contains(value) : Return whether the value exists in the HashSet or not.
 * remove(value): Remove a value in the HashSet. If the value does not exist in the HashSet, do nothing.
 */
public class MyHashSet {
    int[] store = new int[1000000];

    public MyHashSet() {

    }

    public void add(int key) {
        store[key] = 1;
    }

    public void remove(int key) {
        store[key] = 0;
    }

    /**
     * Returns true if this set contains the specified element
     */
    public boolean contains(int key) {
        return store[key] != 0;
    }
}
