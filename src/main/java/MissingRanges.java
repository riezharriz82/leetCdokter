import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/missing-ranges/ Premium
 * <p>
 * You are given an inclusive range [lower, upper] and a sorted unique integer array nums, where all elements are in the inclusive range.
 * <p>
 * A number x is considered missing if x is in the range [lower, upper] and x is not in nums.
 * <p>
 * Return the smallest sorted list of ranges that cover every missing number exactly.
 * That is, no element of nums is in any of the ranges, and each missing number is in one of the ranges.
 * <p>
 * Each range [a,b] in the list should be output as:
 * <p>
 * "a->b" if a != b
 * "a" if a == b
 * <p>
 * Input: nums = [0,1,3,50,75], lower = 0, upper = 99
 * Output: ["2","4->49","51->74","76->99"]
 * Explanation: The ranges are:
 * [2,2] --> "2"
 * [4,49] --> "4->49"
 * [51,74] --> "51->74"
 * [76,99] --> "76->99"
 * <p>
 * Input: nums = [], lower = 1, upper = 1
 * Output: ["1"]
 * Explanation: The only missing range is [1,1], which becomes "1".
 * <p>
 * Input: nums = [], lower = -3, upper = -1
 * Output: ["-3->-1"]
 * Explanation: The only missing range is [-3,-1], which becomes "-3->-1".
 * <p>
 * Input: nums = [-1], lower = -1, upper = -1
 * Output: []
 * Explanation: There are no missing ranges since there are no missing numbers.
 * <p>
 * Input: nums = [-1], lower = -2, upper = -1
 * Output: ["-2"]
 * <p>
 * Constraints:
 * -10^9 <= lower <= upper <= 10^9
 * 0 <= nums.length <= 100
 * lower <= nums[i] <= upper
 * All the values of nums are unique.
 */
public class MissingRanges {
    /**
     * Approach: Greedy Ad-hoc. Compare the current element in the array with the expected value. If it does not matches expectation,
     * there is a range missing.
     */
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        int expected = lower, index = 0, n = nums.length;
        List<Integer> missingRanges = new ArrayList<>();
        while (index < n) {
            int actual = nums[index];
            if (actual == expected) {
                index++;
                expected++;
            } else {
                //if i am expecting 10 and actual is 15, this means [10,14] is missing
                //if i am expecting 14 and actual is 15, this means [14,14] is missing
                missingRanges.add(expected);
                missingRanges.add(actual - 1);
                //instead of adding to an intermediate array we could directly format and update the result array
                expected = actual + 1;
                index++;
            }
        }
        if (expected <= upper) {
            missingRanges.add(expected);
            missingRanges.add(upper);
        }
        List<String> result = new ArrayList<>();
        for (int i = 0; i < missingRanges.size(); i += 2) {
            int start = missingRanges.get(i);
            int end = missingRanges.get(i + 1);
            if (start == end) { //[2,2] -> "2"
                result.add(Integer.toString(start));
            } else { //[2,4] -> "2->4"
                result.add(start + "->" + end);
            }
        }
        return result;
    }
}
