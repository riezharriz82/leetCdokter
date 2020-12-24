/**
 * https://leetcode.com/problems/count-numbers-with-unique-digits/
 * <p>
 * Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.
 * <p>
 * Input: 2
 * Output: 91
 * Explanation: The answer should be the total numbers in the range of 0 ≤ x < 100,
 * excluding 11,22,33,44,55,66,77,88,99
 * <p>
 * Constraints:
 * 0 <= n <= 8
 */
public class CountNumbersWithUniqueDigit {
    /**
     * Approach: Digit DP, consider digits with single length, and then try to reuse its result for two length and three length numbers
     * for single digit, answer is 10
     * for double digit, fix first digit i.e 1_, now we can have 9 possible results because digits can't be repeated
     * and for first digits we can have 9 options, so total 9*9=81
     * <p>
     * Can also be solved using backtracking
     * <p>
     * {@link NumbersAtMostNGivenDigitSet} {@link ConfusingNumber2} related digit dp problems
     */
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans += recur(i, true, 10);
        }
        return ans;
    }

    private int recur(int n, boolean firstDigit, int choices) {
        if (n == 1) {
            return choices;
        } else if (firstDigit) { //special case for first digit as it can't have 0, so it has one choice being reduced
            return (choices - 1) * recur(n - 1, false, choices - 1); //reduce one choice when moving to next digit
        } else { //rest all digits can utilize all the choices available
            return (choices) * recur(n - 1, false, choices - 1);
        }
    }
}
