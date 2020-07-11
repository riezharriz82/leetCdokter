/**
 * https://leetcode.com/problems/design-hashmap/
 * Design a HashMap without using any built-in hash table libraries.
 * <p>
 * To be specific, your design should include these functions:
 * <p>
 * put(key, value) : Insert a (key, value) pair into the HashMap. If the value already exists in the HashMap, update the value.
 * get(key): Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
 * remove(key) : Remove the mapping for the value key if this map contains the mapping for the key.
 */
public class MyHashMap {
    int[] store = new int[1000000];

    public MyHashMap() {
    }

    /**
     * value will always be non-negative.
     */
    public void put(int key, int value) {
        store[key] = value + 1;
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
     */
    public int get(int key) {
        return store[key] - 1;
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping for the key
     */
    public void remove(int key) {
        store[key] = 0;
    }
}
