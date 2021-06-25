import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/next-greater-element-iii/
 * <p>
 * Given a positive 32-bit integer n, you need to find the smallest 32-bit integer which has exactly the same digits existing
 * in the integer n and is greater in value than n. If no such positive 32-bit integer exists, you need to return -1.
 * <p>
 * Input: 12
 * Output: 21
 * <p>
 * Input: 21
 * Output: -1
 */
public class NextGreaterElement3 {
    /**
     * Approach : Iterate from the right to find a digit a[i] greater than adjacent left a[i-1]
     * Now find an index j (j >= i) such that a[j] > a[i-1]
     * Swap out a[i-1], a[j]
     * Sort the digits from i till end (NOT Reverse, as numbers can be duplicate)
     *
     * {@link RemoveKDigits}
     */
    public int nextGreaterElement(int n) {
        List<Integer> digits = new ArrayList<>();
        while (n != 0) {
            int digit = n % 10;
            digits.add(digit);
            n /= 10;
        }
        Collections.reverse(digits);
        boolean mismatchFound = false;
        for (int i = digits.size() - 1; i > 0; i--) {
            if (digits.get(i) > digits.get(i - 1)) {
                mismatchFound = true;
                int justGreaterIndex = findJustGreaterIndex(digits, digits.get(i - 1), i);
                Collections.swap(digits, i - 1, justGreaterIndex);
                List<Integer> sublist = digits.subList(i, digits.size());
                // sublist returns a view of the actual list, not a copy, so modifying the sublist will modify the actual list
                Collections.sort(sublist); //sort required as digits can have duplicates
                break;
            }
        }
        return mismatchFound ? convertToNumber(digits) : -1;
    }

    private int findJustGreaterIndex(List<Integer> digits, int value, int index) {
        int candidate = Integer.MAX_VALUE, candidateIndex = -1;
        for (int i = index; i < digits.size(); i++) {
            int currentElement = digits.get(i);
            if (currentElement > value && currentElement < candidate) {
                candidate = currentElement;
                candidateIndex = i;
            }
        }
        return candidateIndex;
    }

    private int convertToNumber(List<Integer> digits) {
        long number = 0;
        for (int digit : digits) {
            number = (10L * number) + digit;
        }
        if (number > Integer.MAX_VALUE) { //required to handle criteria that the result should not exceed 32 bit value
            return -1;
        }
        return (int) number;
    }
}
