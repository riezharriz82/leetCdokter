import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/time-based-key-value-store/
 * <p>
 * Create a timebased key-value store class TimeMap, that supports two operations.
 * <p>
 * 1. set(string key, string value, int timestamp)
 * <p>
 * Stores the key and value, along with the given timestamp.
 * 2. get(string key, int timestamp)
 * <p>
 * Returns a value such that set(key, value, timestamp_prev) was called previously, with timestamp_prev <= timestamp.
 * If there are multiple such values, it returns the one with the largest timestamp_prev.
 * If there are no values, it returns the empty string ("").
 * <p>
 * Input: inputs = ["TimeMap","set","get","get","set","get","get"], inputs = [[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
 * Output: [null,null,"bar","bar",null,"bar2","bar2"]
 * Explanation:
 * TimeMap kv;
 * kv.set("foo", "bar", 1); // store the key "foo" and value "bar" along with timestamp = 1
 * kv.get("foo", 1);  // output "bar"
 * kv.get("foo", 3); // output "bar" since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 ie "bar"
 * kv.set("foo", "bar2", 4);
 * kv.get("foo", 4); // output "bar2"
 * kv.get("foo", 5); //output "bar2"
 * <p>
 * Input: inputs = ["TimeMap","set","set","get","get","get","get","get"], inputs = [[],["love","high",10],["love","low",20],["love",5],["love",10],["love",15],["love",20],["love",25]]
 * Output: [null,null,null,"","high","high","low","low"]
 * <p>
 * Note:
 * All key/value strings are lowercase.
 * All key/value strings have length in the range [1, 100]
 * The timestamps for all TimeMap.set operations are strictly increasing.
 * 1 <= timestamp <= 10^7
 * TimeMap.set and TimeMap.get functions will be called a total of 120000 times (combined) per test case.
 */
public class TimeBasedKeyValueStore {
    Map<String, TreeMap<Integer, String>> map = new HashMap<>(); //map of key -> map of timestamps to value

    /**
     * Approach: Create a mapping of key to a ordered mapping of timestamps to value
     * Ordered mapping will help in getting a value associated with a timestamp <= provided timestamp using floor()
     * <p>
     * Alternatively since the problem statement mentions that the timestamps for a key are strictly increasing, we don't need no ordered map
     * We can directly append it to a list and use binary search
     * {@link SnapshotArray}
     */
    public TimeBasedKeyValueStore() {

    }

    public void set(String key, String value, int timestamp) {
        map.computeIfAbsent(key, __ -> new TreeMap<>()).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        TreeMap<Integer, String> values = map.getOrDefault(key, new TreeMap<>());
        Map.Entry<Integer, String> floorEntry = values.floorEntry(timestamp); // gets a value associated with a timestamp <= provided timestamp
        if (floorEntry == null) {
            return "";
        } else {
            return floorEntry.getValue();
        }
    }
}
