/**
 * <pre>
 * https://leetcode.com/problems/minimum-number-of-swaps-to-make-the-binary-string-alternating/
 *
 * Given a binary string s, return the minimum number of character swaps to make it alternating, or -1 if it is impossible.
 *
 * The string is called alternating if no two adjacent characters are equal. For example, the strings "010" and "1010" are alternating, while the string "0100" is not.
 *
 * Any two characters may be swapped, even if they are not adjacent.
 *
 * Input: s = "111000"
 * Output: 1
 * Explanation: Swap positions 1 and 4: "111000" -> "101010"
 * The string is now alternating.
 *
 * Input: s = "010"
 * Output: 0
 * Explanation: The string is already alternating, no swaps are needed.
 *
 * Input: s = "1110"
 * Output: -1
 *
 * Constraints:
 * 1 <= s.length <= 1000
 * s[i] is either '0' or '1'.
 * </pre>
 */
public class MinimumNumberOfSwapsToMakeTheBinaryStringAlternating {
    /**
     * Approach: Greedy, final string would either start with 0 ie. 010101.... or with 1 ie. 10101....
     * So try both as target string and compare the input string char by char, keeping track of no of mismatches.
     * No of mismatches are the no of swaps required.
     * <p>
     * {@link MinimumChangesToMakeAlternatingBinaryString}
     */
    public int minSwaps(String s) {
        int ones = 0, zeroes = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') {
                zeroes++;
            } else {
                ones++;
            }
        }
        if (s.length() % 2 == 0 && ones != zeroes) {
            return -1;
        }
        if (s.length() % 2 == 1 && Math.abs(ones - zeroes) != 1) {
            return -1;
        }
        int placeZeroFirst = mismatchesPresent(s, '0');
        int placeOneFirst = mismatchesPresent(s, '1');
        //if the mismatches are odd, this implies there is a char that has no opposite pair available hence this results in an impossible swap
        //as the total swaps required will be half of total mismatches present as one swap can correct 2 mismatches
        if (placeOneFirst % 2 == 1 && placeZeroFirst % 2 == 1) { //both are odd
            return -1;
        } else if (placeOneFirst % 2 == 0 && placeZeroFirst % 2 == 0) { //both are even
            return Math.min(placeOneFirst / 2, placeZeroFirst / 2);
        } else if (placeOneFirst % 2 == 0) {
            return placeOneFirst / 2;
        } else {
            return placeZeroFirst / 2;
        }
    }

    private int mismatchesPresent(String s, char initialChar) {
        char temp = initialChar;
        int swaps = 0;
        for (char c : s.toCharArray()) {
            if (c != temp) {
                swaps++;
            }
            temp ^= '1'; //adding this avoids writing if/else to toggle between '0' and '1'
            temp += '0'; //this is required to cast the integer back to char as the result of previous operation would be 0 or 1 in integer.
        }
        return swaps;
    }
}
