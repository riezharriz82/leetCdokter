/**
 * https://leetcode.com/problems/divide-two-integers/
 * <p>
 * Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.
 * <p>
 * Return the quotient after dividing dividend by divisor.
 * <p>
 * The integer division should truncate toward zero, which means losing its fractional part. For example, truncate(8.345) = 8 and truncate(-2.7335) = -2.
 * <p>
 * Example 1:
 * <p>
 * Input: dividend = 10, divisor = 3
 * Output: 3
 * Explanation: 10/3 = truncate(3.33333..) = 3.
 */
public class DivideTwoIntegers {
    public int divide(int param, int param2) {
        if (param == Integer.MIN_VALUE && param2 == -1) {
            return Integer.MAX_VALUE;
        }
        //if both have same sign, then sign is positive else negative
        //use xor as it can easily help in identifying two different values
        int sign = (param > 0) ^ (param2 > 0) ? -1 : 1;
        long dividend = Math.abs((long) param); //using long to avoid stupid overflow issue
        long divisor = Math.abs((long) param2);
        long result = 0, tmpDividend = dividend, tmp = divisor;

        //21/3
        //check whether we can subtract 3*1 from 21, then check we can subtract 3*2 from 21, then 3*4, then 3*8
        //last valid number that can be subtracted is 12, so new dividend is 21 - 12 = 9
        //add 4 to the result
        //9/3
        //check whether we can subtract 3*1 from 9, 3*2 from 9, 3*4
        //last valid number that can be subtracted is 6, so new dividend is 9 - 6 = 3
        //add 2 to the result
        //3/3
        //check 3*1, 3*2
        //new dividend is 0, add 1 to the result
        while (tmpDividend - tmp >= 0) {
            long multiplier = 1;
            while (tmpDividend - tmp >= 0) {
                tmp *= 2;
                multiplier *= 2;
            }
            multiplier /= 2;
            tmp /= 2;
            result += multiplier;

            tmpDividend -= tmp;
            tmp = divisor;
        }
        return (int) (sign * result);
    }
}
