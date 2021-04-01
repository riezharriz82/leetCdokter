/**
 * https://leetcode.com/problems/hexspeak/ Premium
 * <p>
 * A decimal number can be converted to its Hexspeak representation by first converting it to an uppercase hexadecimal string,
 * then replacing all occurrences of the digit 0 with the letter O, and the digit 1 with the letter I.
 * Such a representation is valid if and only if it consists only of the letters in the set {"A", "B", "C", "D", "E", "F", "I", "O"}.
 * <p>
 * Given a string num representing a decimal integer N, return the Hexspeak representation of N if it is valid, otherwise return "ERROR".
 * <p>
 * Input: num = "257"
 * Output: "IOI"
 * Explanation:  257 is 101 in hexadecimal.
 * <p>
 * Input: num = "3"
 * Output: "ERROR"
 * <p>
 * Constraints:
 * 1 <= N <= 10^12
 * There are no leading zeros in the given string.
 * All answers must be in uppercase letters.
 */
public class HexSpeak {
    /**
     * Approach: Ad-hoc, Convert the string representation of number to a long representation.
     * Then convert it to hexadecimal format.
     */
    public String toHexspeak(String num) {
        long hex = Long.parseLong(num);
        StringBuilder res = new StringBuilder();
        while (hex != 0) {
            long rem = hex % 16;
            if (rem >= 2 && rem <= 9) {
                return "ERROR";
            }
            if (rem >= 10) {
                res.append((char) ('A' + (rem - 10)));
            } else if (rem == 0) {
                res.append('O');
            } else if (rem == 1) {
                res.append('I');
            }
            hex /= 16;
        }
        return res.reverse().toString();
    }
}
