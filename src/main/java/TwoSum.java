import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/two-sum/
 */
public class TwoSum {

    public int[] twoSumUsingHashTable(int[] nums, int target) {
        Map<Integer, Integer> mapping = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int inverse = target - nums[i];
            if (mapping.containsKey(inverse) && mapping.get(inverse) != i) {
                return new int[]{i, mapping.get(inverse)};
            }
            mapping.put(nums[i], i);
        }
        return new int[]{0, 0};
    }

    public int[] twoSum(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int[] indices = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            indices[i] = i;
        }
        Integer[] integerIndices = Arrays.stream(indices).boxed().toArray(Integer[]::new);
        Arrays.sort(integerIndices, Comparator.comparingInt(o -> nums[o]));

        while (left < right) {
            if (nums[integerIndices[left]] + nums[integerIndices[right]] == target) {
                return new int[]{integerIndices[left], integerIndices[right]};
            } else if (nums[integerIndices[left]] + nums[integerIndices[right]] < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{0, 0}; //not possible since problem statement guarantees exactly one solution
    }
}
