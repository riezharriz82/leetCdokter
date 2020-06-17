import javafx.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/k-diff-pairs-in-an-array/
 * Given an array of integers and an integer k, you need to find the number of unique k-diff pairs in the array.
 * Here a k-diff pair is defined as an integer pair (i, j), where i and j are both numbers in the array and their absolute difference is k.
 * <p>
 * Example 1:
 * Input: [3, 1, 4, 1, 5], k = 2
 * Output: 2
 * Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
 * Although we have two 1s in the input, we should only return the number of unique pairs.
 */
public class KDiffPairInArray {
    public int findPairsCleaner(int[] nums, int k) {
        if (k < 0) { //only absolute kdiffs are required
            return 0;
        }
        Map<Integer, Integer> input = new HashMap<>(nums.length);
        for (int num : nums) {
            input.put(num, input.getOrDefault(num, 0) + 1);
        }
        int count = 0;

        for (Map.Entry<Integer, Integer> entry : input.entrySet()) {
            if (k == 0) {
                if (entry.getValue() >= 2) {
                    count++;
                }
            } else if (input.containsKey(entry.getKey() + k)) {
                count++;
            }
        }
        return count;
    }

    public int findPairs(int[] nums, int k) {
        if (k < 0) {
            return 0;
        }
        Map<Integer, Integer> input = new HashMap<>(nums.length);
        for (int num : nums) {
            Integer cur = input.get(num);
            input.put(num, cur == null ? 1 : cur + 1);
        }
        int count = 0;
        Set<Pair<Integer, Integer>> result = new HashSet<>();
        for (int val : nums) {
            if (k == 0) {
                if (input.get(val) > 1 && !result.contains(new Pair<>(val, val))) {
                    count++;
                    input.put(val, input.get(val) - 1);
                    result.add(new Pair<>(val, val));
                }
            } else if (input.containsKey(val + k) && !result.contains(new Pair<>(val, val + k))) {
                count++;
                result.add(new Pair<>(val, val + k));
            } else if (input.containsKey(val - k) && !result.contains(new Pair<>(val - k, val))) {
                count++;
                result.add(new Pair<>(val - k, val));
            }
        }
        return count;
    }
}
