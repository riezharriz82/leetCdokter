package alternate;

/**
 * https://binarysearch.io/problems/Run-Length-Decoding
 * <p>
 * Run-length encoding is a fast and simple method of encoding strings. The basic idea is to represent
 * repeated successive characters as a single count and character. For example, the string "AAAABBBCCDAA" would
 * be encoded as "4A3B2C1D2A".
 * <p>
 * Given a string s that's a run-length encoded string, return its decoded version.
 * <p>
 * Note: The original string is guaranteed not to have numbers in it.
 * <p>
 * s = "4A3B2C1D2A"
 * "AAAABBBCCDAA"
 */
public class RunLengthDecoding {
    /**
     * Two gotchas:
     * 1. Whenever a string has numbers in it and you are parsing it, remember that a number can have multiple digits.
     * (During my initial implementation I forgot about it and got WA)
     * 2. A String can be converted into integer while traversing the string without storing the digits in a list
     */
    public String solve(String s) {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        while (index < s.length()) {
            int multiplier = 0;
            while (Character.isDigit(s.charAt(index))) {
                //convert the string to integer during traversal without storing the digits in a list
                multiplier = 10 * multiplier + (s.charAt(index++) - '0');
            }
            char ch = s.charAt(index++);
            while (multiplier > 0) {
                sb.append(ch);
                multiplier--;
            }
        }
        return sb.toString();
    }
}
