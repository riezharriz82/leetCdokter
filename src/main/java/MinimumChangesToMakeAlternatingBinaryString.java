/**
 * https://leetcode.com/problems/minimum-changes-to-make-alternating-binary-string/
 * <p>
 * You are given a string s consisting only of the characters '0' and '1'. In one operation, you can change any '0' to '1' or vice versa.
 * <p>
 * The string is called alternating if no two adjacent characters are equal. For example, the string "010" is alternating, while the string "0100" is not.
 * <p>
 * Return the minimum number of operations needed to make s alternating.
 * <p>
 * Input: s = "0100"
 * Output: 1
 * Explanation: If you change the last character to '1', s will be "0101", which is alternating.
 * <p>
 * Input: s = "10"
 * Output: 0
 * Explanation: s is already alternating.
 * <p>
 * Input: s = "1111"
 * Output: 2
 * Explanation: You need two operations to reach "0101" or "1010".
 * <p>
 * Constraints:
 * 1 <= s.length <= 104
 * s[i] is either '0' or '1'.
 */
public class MinimumChangesToMakeAlternatingBinaryString {
    /**
     * Approach: There are only two patterns possible either "10101010.." or "01010101.."
     * Check converting to which pattern leads to minimum changes.
     * <p>
     * {@link MinimumNumberOfStepsToMakeTwoStringsAnagram} {@link MinimumNumberOfSwapsToMakeTheBinaryStringAlternating}
     */
    public int minOperations(String s) {
        int placeZeroFirst = 0, placeOneFirst = 0;
        char placeZero = '0', placeOne = '1';
        for (char c : s.toCharArray()) {
            if (c != placeZero) {
                placeZeroFirst++;
            }
            if (c != placeOne) {
                placeOneFirst++;
            }
            //flip the required chars
            placeZero = (placeZero == '0') ? '1' : '0';
            placeOne = (placeOne == '0') ? '1' : '0';
        }
        return Math.min(placeZeroFirst, placeOneFirst);
    }
}
