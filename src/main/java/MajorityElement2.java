import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/majority-element-ii/
 * <p>
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 * <p>
 * Note: The algorithm should run in linear time and in O(1) space.
 * <p>
 * Example 1:
 * <p>
 * Input: [3,2,3]
 * Output: [3]
 * <p>
 * Example 2:
 * <p>
 * Input: [1,1,1,3,3,2,2,2]
 * Output: [1,2]
 */
public class MajorityElement2 {
    public List<Integer> majorityElement(int[] nums) {
        int[] mapping = new int[4];
        //structure --> [value1, count1, value2, count2]
        for (int num : nums) {
            //first check any empty values
            if (mapping[0] == num) {
                mapping[1]++;
            } else if (mapping[2] == num) {
                mapping[3]++;
            } else {
                if (mapping[1] == 0) {
                    mapping[0] = num;
                    mapping[1] = 1;
                } else if (mapping[3] == 0) {
                    mapping[2] = num;
                    mapping[3] = 1;
                } else {
                    //new value found, decrement the existing counters
                    mapping[1]--;
                    mapping[3]--;
                }
            }
        }
        //num[0] and num[2] are possible majority elements, need to check their count in the input to confirm
        int firstCount = 0, secondCount = 0;
        for (int num : nums) {
            if (num == mapping[0]) {
                firstCount++;
            } else if (num == mapping[2]) {
                secondCount++;
            }
        }
        if (firstCount > nums.length / 3.0 && secondCount > nums.length / 3.0) {
            return Arrays.asList(mapping[0], mapping[2]);
        } else if (firstCount > nums.length / 3.0) {
            return Arrays.asList(mapping[0]);
        } else if (secondCount > nums.length / 3.0) {
            return Arrays.asList(mapping[2]);
        } else {
            return Arrays.asList();
        }
    }
}
