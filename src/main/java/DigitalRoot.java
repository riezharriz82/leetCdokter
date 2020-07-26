/**
 * https://leetcode.com/problems/add-digits/
 * <p>
 * Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
 * <p>
 * Input: 38
 * Output: 2
 * <p>
 * Explanation: The process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.
 * <p>
 * Follow up:
 * Could you do it without any loop/recursion in O(1) runtime?
 */
public class DigitalRoot {
    // fun fact: digital root a number which is multiple of 9 is always 9. It acts as a divisibility rule for muliples of 9
    public int addDigits(int num) {
        if (num == 0) {
            return 0;
        } else {
            return 1 + (num - 1) % 9;
        }
    }
}
