import java.util.HashSet;

/**
 * https://leetcode.com/problems/smallest-integer-divisible-by-k/
 * <p>
 * Given a positive integer K, you need to find the length of the smallest positive integer N such that N is divisible by K, and N only contains the digit 1.
 * <p>
 * Return the length of N. If there is no such N, return -1.
 * <p>
 * Note: N may not fit in a 64-bit signed integer.
 * <p>
 * Input: K = 1
 * Output: 1
 * Explanation: The smallest answer is N = 1, which has length 1.
 * <p>
 * Input: K = 2
 * Output: -1
 * Explanation: There is no such positive integer N divisible by 2.
 * <p>
 * Input: K = 3
 * Output: 3
 * Explanation: The smallest answer is N = 111, which has length 3.
 */
public class SmallestIntegerDivisibleByK {
    /**
     * Approach: Pigeon Hole Principle
     * Since the number can lie outside the bounds of long integer, we can't store the whole number
     * A trick to check whether the next no in series is divisible by k is to leverage current remainder to generate remainder of next number
     * <p>
     * 11 = 3*3 + 2 (2 is the remainder)
     * 10*11 = 10*3*3 + 10*2
     * 10*11 + 1 = 10*3*3 + 10*2 + 1
     * 111 = 30*3 + 21
     * <p>
     * 21 is the next remainder and can be broken down to 21%3 = 0
     * <p>
     * Now the problem does not explicitly state that it's not always possible to find a valid number that divides K and it can happen
     * when remainders start repeating, there can only be a finite set of remainders so if they repeat, then we got ourselves an
     * infinite loop. So care must be taken to handle such cases separately.
     * <p>
     * {@link PrisonCellAfterNDays} related problem
     */
    public int smallestRepunitDivByK(int K) {
        HashSet<Integer> remainders = new HashSet<>();
        int n = 1;
        int len = 1;
        remainders.add(1);
        while (true) {
            if (n % K == 0) {
                return len;
            }
            n = 10 * n + 1; //generate the next candidate for calculating remainder
            n = n % K; //get the remainder
            if (!remainders.add(n)) {
                return -1;
            }
            len++; //increase the length
        }
    }
}
