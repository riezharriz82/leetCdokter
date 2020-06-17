/**
 * https://leetcode.com/problems/plus-one/
 * Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
 * <p>
 * The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.
 * <p>
 * You may assume the integer does not contain any leading zero, except the number 0 itself.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,3]
 * Output: [1,2,4]
 * Explanation: The array represents the integer 123.
 */
public class PlusOne {

    public int[] plusOneSimplified(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            } else {
                digits[i] = 0;
            }
        }
        int[] res = new int[digits.length + 1];
        res[0] = 1;
        return res;
    }

    public int[] plusOne(int[] digits) {
        int[] tempResult = new int[digits.length];
        int carry = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            tempResult[i] = (carry + digits[i]) % 10;
            carry = (carry + digits[i]) / 10;
        }
        if (carry == 1) {
            int[] tempResultWithOne = new int[digits.length + 1];
            tempResultWithOne[0] = 1;
            System.arraycopy(tempResult, 0, tempResultWithOne, 1, tempResult.length);
            return tempResultWithOne;
        } else {
            return tempResult;
        }
    }
}
