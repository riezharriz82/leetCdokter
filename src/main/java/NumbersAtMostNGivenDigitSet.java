import java.util.Arrays;

/**
 * https://leetcode.com/problems/numbers-at-most-n-given-digit-set/
 * <p>
 * Given an array of digits, you can write numbers using each digits[i] as many times as we want.
 * For example, if digits = ['1','3','5'], we may write numbers such as '13', '551', and '1351315'.
 * <p>
 * Return the number of positive integers that can be generated that are less than or equal to a given integer n.
 * <p>
 * Input: digits = ["1","3","5","7"], n = 100
 * Output: 20
 * Explanation:
 * The 20 numbers that can be written are:
 * 1, 3, 5, 7, 11, 13, 15, 17, 31, 33, 35, 37, 51, 53, 55, 57, 71, 73, 75, 77.
 * <p>
 * Input: digits = ["1","4","9"], n = 1000000000
 * Output: 29523
 * Explanation:
 * We can write 3 one digit numbers, 9 two digit numbers, 27 three digit numbers,
 * 81 four digit numbers, 243 five digit numbers, 729 six digit numbers,
 * 2187 seven digit numbers, 6561 eight digit numbers, and 19683 nine digit numbers.
 * In total, this is 29523 integers that can be written using the digits array.
 */
public class NumbersAtMostNGivenDigitSet {
    int res;

    /**
     * Approach: Digit DP, try to find count of valid numbers of length=1,2,3...(length of N)
     * Reference video that helped me https://www.youtube.com/watch?v=heUFId6Qd1A
     * <p>
     * {@link SequentialDigits} {@link LargestTimeForGivenDigits} {@link StrobogrammaticNumber2} digit related problems
     */
    public int atMostNGivenDigitSetDP(String[] digits, int n) {
        int ans = 0;
        String number = Integer.toString(n); //changed the number to a string, so that it's easier to access chars at each index
        char[] digit = new char[digits.length];
        for (int i = 0; i < digits.length; i++) {
            digit[i] = digits[i].charAt(0);
        }
        int[][] memo = new int[number.length() + 1][2];
        for (int[] ints : memo) {
            Arrays.fill(ints, -1);
        }
        for (int len = 1; len <= number.length(); len++) {
            //all length of numbers smaller than required number length are not under any bounds
            ans += recur(digit, number, len, len == number.length() ? 1 : 0, memo);
        }
        return ans;
    }

    private int recur(char[] digits, String number, int n, int isLimited, int[][] memo) {
        char upperBound = number.charAt(number.length() - n);
        if (memo[n][isLimited] != -1) {
            return memo[n][isLimited];
        }
        if (n == 1) { //single digit left, no recursion left
            int ans = 0;
            for (int i = 0; i < digits.length; i++) {
                if (isLimited == 1 && digits[i] > upperBound) { //if not limited, place all the digits
                    break;
                }
                ans++;
            }
            return memo[n][isLimited] = ans;
        } else {
            int ans = 0;
            for (int i = 0; i < digits.length; i++) {
                if (isLimited == 1 && digits[i] > upperBound) {
                    break;
                }
                //only the last valid digit for this index will set the tight constraint for remaining n-1 digits
                //e.g if target number is 4321, and we are setting 1 as the first digit, remaining 3 digits are free
                //i.e we can place all valid digits in the last 3 slots
                //but if we place 4 as the first digit, for the 2nd digit, we have to limit from 0 till 3 only
                ans += recur(digits, number, n - 1, (isLimited == 1 && digits[i] == upperBound) ? 1 : 0, memo);
            }
            return memo[n][isLimited] = ans;
        }
    }

    /**
     * Approach: Generate all the numbers by fixing digits recursively. Gives TLE
     */
    public int atMostNGivenDigitSetRecursive(String[] digits, int n) {
        int[] digitSet = new int[digits.length];
        for (int i = 0; i < digits.length; i++) {
            digitSet[i] = Integer.parseInt(digits[i]);
        }
        recur(digitSet, n, 0);
        return res;
    }

    private void recur(int[] digitSet, int n, long curNumber) {
        if (curNumber > n) { //don't proceed if current number > target, no good can come
            return;
        }
        res++;
        for (int digit : digitSet) {
            recur(digitSet, n, curNumber * 10 + digit);
        }
    }
}
