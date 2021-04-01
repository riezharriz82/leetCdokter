import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/two-sum-iii-data-structure-design/
 * <p>
 * Design a data structure that accepts a stream of integers and checks if it has a pair of integers that sum up to a particular value.
 * <p>
 * Implement the TwoSum class:
 * <p>
 * TwoSum() Initializes the TwoSum object, with an empty array initially.
 * void add(int number) Adds number to the data structure.
 * boolean find(int value) Returns true if there exists any pair of numbers whose sum is equal to value, otherwise, it returns false.
 * <p>
 * Input
 * ["TwoSum", "add", "add", "add", "find", "find"]
 * [[], [1], [3], [5], [4], [7]]
 * Output
 * [null, null, null, null, true, false]
 * <p>
 * Explanation
 * TwoSum twoSum = new TwoSum();
 * twoSum.add(1);   // [] --> [1]
 * twoSum.add(3);   // [1] --> [1,3]
 * twoSum.add(5);   // [1,3] --> [1,3,5]
 * twoSum.find(4);  // 1 + 3 = 4, return true
 * twoSum.find(7);  // No two integers sum up to 7, return false
 * <p>
 * Constraints:
 * -10^5 <= number <= 10^5
 * -2^31 <= value <= 2^31 - 1
 * At most 5 * 10^4 calls will be made to add and find.
 */
public class TwoSum3 {
    Map<Long, Integer> map = new HashMap<>();

    /**
     * Approach: For a real life interview question, we would have to clarify access pattern to the interviewer
     * ie. whether the no of add() is much more than find() or vice-versa as this would help in optimizing for our use case
     * <p>
     * If finds() are much more than add(), we can precompute all possible valid sum results during add() and time complexity of find() would be O(1)
     * If add() are much more than find(), then we have to use standard hashmap algorithm for computing two sum
     * <p>
     * Another interesting observation that I read is the time complexity of iterating a sparse hashmap is proportional to its capacity.
     * So a hashmap with 2 elements but with a capacity of 1000_0000 will take a lot of time to traverse than a list containing 2 elements
     * Hence in discuss section, many people leveraged an arraylist to store all unique keys, instead of iterating over entrySet()
     * <p>
     * It looks like a minor optimization but my code took ~100 ms vs ~20 ms of an arraylist version.
     * <p>
     * {@link TwoSum}
     */
    public TwoSum3() {

    }

    /**
     * Add the number to an internal data structure.
     */
    public void add(int number) {
        map.put((long) number, map.getOrDefault((long) number, 0) + 1);
    }

    /**
     * Find if there exists any pair of numbers which sum is equal to the value.
     */
    public boolean find(int value) {
        for (Map.Entry<Long, Integer> entry : map.entrySet()) {
            long curKey = entry.getKey();
            long targetKey = value - entry.getKey();
            if (targetKey != curKey && map.containsKey(targetKey)) {
                return true;
            } else if (targetKey == curKey && entry.getValue() > 1) {
                return true;
            }
        }
        return false;
    }
}
