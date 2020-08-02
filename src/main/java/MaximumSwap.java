import java.util.ArrayList;
import java.util.Collections;

/**
 * https://leetcode.com/problems/maximum-swap/
 * <p>
 * Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the maximum valued number you could get.
 * <p>
 * Input: 2736
 * Output: 7236
 * Explanation: Swap the number 2 and the number 7.
 * <p>
 * Input: 9973
 * Output: 9973
 * Explanation: No swap.
 */
public class MaximumSwap {
    public int maximumSwap(int num) {
        ArrayList<Integer> digits = new ArrayList<>();
        while (num != 0) {
            int digit = num % 10;
            digits.add(digit);
            num /= 10;
        }
        Collections.reverse(digits);
        for (int i = 0; i < digits.size() - 1; i++) {
            int max = i;
            //find the largest digit present after ith index. PS that digit should not equal to digit present at ith index
            for (int j = i + 1; j < digits.size(); j++) {
                //PS. Equality condition has been added to find the rightmost digit in case of duplicate digits having max value
                if (digits.get(j) >= digits.get(max) && digits.get(j) != digits.get(i)) {
                    max = j;
                }
            }
            if (max != i) {
                //swap and break, since only one swap allowed
                Collections.swap(digits, i, max);
                break;
            }
        }
        //convert digits into integer
        int result = 0;
        for (Integer digit : digits) {
            result = result * 10 + digit;
        }
        return result;
    }
}
