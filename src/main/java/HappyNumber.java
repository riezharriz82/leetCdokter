import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/happy-number/
 * <p>
 * Write an algorithm to determine if a number n is "happy".
 * <p>
 * A happy number is a number defined by the following process: Starting with any positive integer, replace the number by
 * the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay),
 * or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.
 * <p>
 * Return True if n is a happy number, and False if not.
 * <p>
 * Input: 19
 * Output: true
 * Explanation:
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 */
public class HappyNumber {
    Set<Integer> set = new HashSet<>();

    /**
     * Approach was based on {@link PrisonCellAfterNDays} in a finite set of transformations, things are bound to repeat.
     * Use hashset to detect cycle
     * Advanced way to detect cycle without extra space would be to use floyd's cycle finding algorithm using slow and fast pointer
     * Here slow and fast pointer would be pointing to the next transformed number
     */
    public boolean isHappy(int n) {
        if (n == 1) {
            return true;
        } else if (n == 0) {
            return false;
        } else {
            int newNumber = 0;
            while (n > 0) {
                int digit = n % 10;
                newNumber += (digit * digit);
                n /= 10;
            }
            if (!set.add(newNumber)) { //cycle found
                return false;
            }
            return isHappy(newNumber);
        }
    }
}
