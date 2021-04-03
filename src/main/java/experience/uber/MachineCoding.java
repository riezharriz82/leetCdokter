package experience.uber;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <pre>
 * 2nd Round: Machine coding
 * Implement multilevel cache
 * // Multilevel Cache
 *
 * 1. Different levels of cache that are chained (L1 -> L2 -> L3 -> L4 ...)
 * 2. Each cache level has a specific CAPACITY
 *
 * Let's assume there are 5 levels of cache - L1, L2, L3, L4, L5
 *
 * The following is the order in terms of size: L1 < L2 < L3 < L4 < L5
 *
 * 3. Values at each level is a subset of values at lower level
 * 4. For Reads, if a value is not found at a particular level, try getting it from lower levels. Also, once a value is
 * found, it needs to be updated in higher levels as well
 * 5. Synchronize this for multiple threads reading and writing at the same time.
 * </pre>
 */
public class MachineCoding {
    List<ConcurrentHashMap<Integer, Integer>> caches = new ArrayList<>();
    List<Integer> capacities = Arrays.asList(5, 10, 15, 20, 25);
    int MAX_LEVELS = 5;

    MachineCoding() {
        for (int i = 0; i < MAX_LEVELS; i++) {
            caches.add(new ConcurrentHashMap<>(capacities.get(i)));
        }
    }

    public static void main(String[] args) {
        MachineCoding multilevelCache = new MachineCoding();
        multilevelCache.put(1, 2);
        multilevelCache.print();
    }

    public synchronized void put(int key, int val) {
        put(key, val, 0, MAX_LEVELS);
    }

    public void remove(int key) {
        put(key, 0);
    }

    /**
     * Recursively look for the provided key from smaller cache and move to bigger cache if not found.
     * Once found, don't forget to update the smaller cache
     */
    public synchronized Optional<Integer> get(int key) {
        Optional<Pair<Integer, Integer>> valueAndMaxLevel = get(key, 0); //pair of value, level at which it was found
        //once a value is found, need to update in smaller cache till the level it was found
        valueAndMaxLevel.ifPresent(pair -> put(key, pair.getKey(), 0, pair.getValue()));
        return valueAndMaxLevel.map(Pair::getKey);
    }

    /**
     * Recursively look for a value from the specified level
     */
    private Optional<Pair<Integer, Integer>> get(int key, int level) {
        if (level == MAX_LEVELS) {
            return Optional.empty();
        } else if (caches.get(level).contains(key)) {
            return Optional.of(new Pair<>(caches.get(level).get(key), level));
        } else {
            return get(key, level + 1);
        }
    }

    /**
     * Recursively put a key/value pair in the current level.
     * If the current level is full, evict a random key. Make sure to recursively remove the key from bigger caches as well.
     * else insert directly and recurse to bigger level.
     */
    private void put(int key, int val, int level, int maxLevel) {
        if (level == maxLevel) {
            return;
        }
        if (caches.get(level).contains(key) || caches.get(level).size() < capacities.get(level)) {
            caches.get(level).put(key, val);
            put(key, val, level + 1, maxLevel);
        } else {
            //evict random key, here first key is chosen at "random"
            int firstKey = caches.get(level).keySet().iterator().next();
            //recursively remove all occurrences of firstKey from bigger caches as well.
            remove(firstKey, level);
            caches.get(level).put(key, val);
            put(key, val, level + 1, maxLevel);
        }
    }

    private void remove(int key, int level) {
        if (level == MAX_LEVELS) {
            return;
        }
        caches.get(level).remove(key);
        remove(key, level + 1);
    }

    public void print() {
        print(0);
    }

    private void print(int level) {
        if (level == MAX_LEVELS) {
            return;
        }
        System.out.println("Elements at level " + level);
        caches.get(level).forEach((k, v) -> System.out.println(k + " -> " + v));
        print(level + 1);
    }
}


