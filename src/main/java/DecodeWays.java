import java.util.Arrays;

/**
 * https://leetcode.com/problems/decode-ways/
 * <p>
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given a non-empty string containing only digits, determine the total number of ways to decode it.
 * <p>
 * Input: "12"
 * Output: 2
 * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 */
public class DecodeWays {
    /**
     * Approach: Every digit has two options, either it can be single or it can be paired up with the adjacent right digit.
     * <p>
     * {@link alternate.NumberOfWaysToPairPeople} is similar problem
     */
    public int numDecodings(String s) {
        int[] memo = new int[s.length()];
        Arrays.fill(memo, -1); //because 0 is a valid answer
        return recur(s, 0, memo);
    }

    private int recur(String s, int index, int[] memo) {
        if (index == s.length()) {
            return 1;
        } else if (memo[index] != -1) {
            return memo[index];
        } else if (s.charAt(index) == '0') { //encoding starts from 1
            return memo[index] = 0;
        } else {
            int singleDigit = recur(s, index + 1, memo); //fix one digit
            if (index + 1 < s.length()) { //check if two digits possible
                int candidate = 10 * (s.charAt(index) - '0') + (s.charAt(index + 1) - '0');
                if (candidate < 27) { // check if valid two digit combo
                    return memo[index] = singleDigit + recur(s, index + 2, memo);
                }
            }
            return memo[index] = singleDigit;
        }
    }
}
