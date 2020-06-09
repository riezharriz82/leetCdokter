import javafx.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/k-diff-pairs-in-an-array/
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
