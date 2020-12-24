import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/strobogrammatic-number-ii/
 * <p>
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * <p>
 * Find all strobogrammatic numbers that are of length = n.
 * <p>
 * Input:  n = 2
 * Output: ["11","69","88","96"]
 */
public class StrobogrammaticNumber2 {
    private final char[][] pairs = new char[][]{{'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}, {'9', '6'}};

    /**
     * Approach: Only digits 0,1,6,8,9 can contribute to a number being strobogrammatic, so they kinda act like a palindrome
     * If you fix one pair at the left, other pair should be fixed in the right
     * Handle base cases for even and odd length numbers
     * <p>
     * Time Complexity: 5^(n/2) * n
     * Out of n digits half of them are duplicates, and for each digit, we have 5 options, so 5^(n/2)
     * {@link ConfusingNumber2}
     */
    public List<String> findStrobogrammatic(int n) {
        List<String> res = new ArrayList<>();
        recur(0, n - 1, res, new char[n]);
        return res;
    }

    private void recur(int left, int right, List<String> res, char[] current) {
        if (left == right) { //odd number base case, only single place remaining, can place only 0,1,8
            for (int i = 0; i < pairs.length; i++) {
                if (pairs[i][0] == pairs[i][1]) {
                    current[left] = pairs[i][0];
                    res.add(new String(current));
                }
            }
        } else if (left > right) { //even number base case, all digits have already been placed, add to result
            res.add(new String(current));
        } else {
            for (int i = 0; i < pairs.length; i++) {
                if (left == 0 && i == 0) { //digit 0 can't be placed as the first digit, it can only be used in inner digits
                    continue;
                }
                current[left] = pairs[i][0];
                current[right] = pairs[i][1];
                recur(left + 1, right - 1, res, current);
            }
        }
    }
}
