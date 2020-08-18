import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/numbers-with-same-consecutive-differences/
 * <p>
 * Return all non-negative integers of length N such that the absolute difference between every two consecutive digits is K.
 * <p>
 * Note that every number in the answer must not have leading zeros except for the number 0 itself. For example, 01 has one leading zero and is invalid, but 0 is valid.
 * <p>
 * You may return the answer in any order.
 * <p>
 * Input: N = 3, K = 7
 * Output: [181,292,707,818,929]
 * Explanation: Note that 070 is not a valid number, because it has leading zeroes.
 */
public class NumbersWithSameConsecutiveDifference {
    /**
     * Approach: As the problem asked to generate all the results, my intuition was to use recursion
     * So I fixed one digit and recurred for next digit until I pick total N digits
     */
    public int[] numsSameConsecDiff(int N, int K) {
        if (N == 1) {
            return new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        } else {
            List<Integer> res = new ArrayList<>();
            for (int i = 1; i < 10; i++) {
                recur(i, 0, 1, K, N, res);
            }
            return res.stream().mapToInt(i -> i).toArray();
        }
    }

    private void recur(int digit, int currentNumber, int currentLength, int diff, int targetLength, List<Integer> result) {
        if (digit >= 0 && digit <= 9) {
            int number = currentNumber * 10 + digit;
            if (currentLength == targetLength) {
                result.add(number);
            } else if (diff != 0) {
                recur(digit + diff, number, currentLength + 1, diff, targetLength, result);
                recur(digit - diff, number, currentLength + 1, diff, targetLength, result);
            } else {
                //Need a special case for diff = 0
                recur(digit, number, currentLength + 1, diff, targetLength, result);
            }
        }
    }
}
