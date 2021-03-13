import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/count-binary-substrings/
 * <p>
 * Give a string s, count the number of non-empty (contiguous) substrings that have the same number of 0's and 1's,
 * and all the 0's and all the 1's in these substrings are grouped consecutively.
 * <p>
 * Substrings that occur multiple times are counted the number of times they occur.
 * <p>
 * Input: "00110011"
 * Output: 6
 * Explanation: There are 6 substrings that have equal number of consecutive 1's and 0's: "0011", "01", "1100", "10", "0011", and "01".
 * <p>
 * Notice that some of these substrings repeat and are counted the number of times they occur.
 * Also, "00110011" is not a valid substring because all the 0's (and 1's) are not grouped together.
 * <p>
 * Input: "10101"
 * Output: 4
 * Explanation: There are 4 substrings: "10", "01", "10", "01" that have equal number of consecutive 1's and 0's.
 * <p>
 * s.length will be between 1 and 50,000.
 * s will only consist of "0" or "1" characters.
 */
public class CountBinarySubstrings {
    /**
     * Approach: Expand around the center. The challenge in the problem is to consider substrings that are grouped consecutively.
     * If you simply change 0 to -1 and use prefix sum, you will find substrings that have equal no of 0's and 1's but it might
     * be possible that the groupings are not consecutive e.g. 0101
     * <p>
     * To find consecutive groupings, we have to expand from center e.g consider 0001111 or 1110000, if we compute the Run Length encoding,
     * we get [3,4], if we expand from center, we can get 3 substrings with equal no of 0's and 1's and grouped consecutively.
     * We don't care whether the first group is a 0 or 1, we just care about two adjacent groups being different.
     * <p>
     * {@link MaxSubarrayWithEqualOnesAndZeroes} {@link PalindromicSubstrings} {@link LargestPlusSign}
     */
    public int countBinarySubstrings(String s) {
        List<Integer> groupings = new ArrayList<>();
        int index = 0;
        while (index < s.length()) {
            int temp = index;
            while (temp < s.length() && s.charAt(index) == s.charAt(temp)) {
                temp++;
            }
            int length = temp - index; //find length of current grouping of similar consecutive characters
            groupings.add(length);
            index = temp; //reset index to start of new group
        }
        int result = 0;
        for (int i = 1; i < groupings.size(); i++) {
            //crux of the solution, expand equally from the center
            result += Math.min(groupings.get(i), groupings.get(i - 1));
        }
        return result;
    }
}
