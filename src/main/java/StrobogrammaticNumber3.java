/**
 * https://leetcode.com/problems/strobogrammatic-number-iii/
 * <p>
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * <p>
 * Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.
 * <p>
 * Input: low = "50", high = "100"
 * Output: 3
 * Explanation: 69, 88, and 96 are three strobogrammatic numbers.
 * Note:
 * Because the range might be a large number, the low and high numbers are represented as string.
 */
public class StrobogrammaticNumber3 {
    private final char[][] pairs = new char[][]{{'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}, {'9', '6'}};

    /**
     * Approach: Fix digits of a candidate string pairwise (first and last) and then check whether the string >= low && string <= high
     * <p>
     * It looks to be slow but it works because there are not many such numbers [0,1e12] has 24999 such numbers
     * <p>
     * {@link StrobogrammaticNumber2} {@link NumbersAtMostNGivenDigitSet} {@link ConfusingNumber2} related digit DP problems
     */
    public int strobogrammaticInRange(String low, String high) {
        int ans = 0;
        for (int len = low.length(); len <= high.length(); len++) { //important to recur for smallest length to largest length
            ans += recur(0, len - 1, low, high, new char[len]);
        }
        return ans;
    }

    private int recur(int left, int right, String low, String high, char[] result) {
        if (left == right) { //special case for the middlemost character, can only place 0,1,8
            int ans = 0;
            for (int i = 0; i < pairs.length; i++) {
                if (pairs[i][0] != pairs[i][1])
                    continue;
                result[left] = pairs[i][0];
                ans += recur(left + 1, right - 1, low, high, result);
            }
            return ans;
        } else if (left > right) { //if all characters have been placed, evaluate the string to see if it's within bounds
            String candidate = new String(result);
            if ((result.length == low.length() && candidate.compareTo(low) < 0) || (result.length == high.length() && candidate.compareTo(high) > 0)) {
                return 0;
            }
            return 1;
        } else { //place pairwise characters and recur for remaining digits
            int ans = 0;
            for (int i = 0; i < pairs.length; i++) {
                if (left == 0 && pairs[i][0] == '0') { //0 can't be the first digit
                    continue;
                }
                result[left] = pairs[i][0];
                result[right] = pairs[i][1];
                ans += recur(left + 1, right - 1, low, high, result);
            }
            return ans;
        }
    }
}
