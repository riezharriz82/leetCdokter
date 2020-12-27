/**
 * https://leetcode.com/problems/confusing-number-ii/
 * <p>
 * We can rotate digits by 180 degrees to form new digits. When 0, 1, 6, 8, 9 are rotated 180 degrees, they become 0, 1, 9, 8, 6 respectively.
 * When 2, 3, 4, 5 and 7 are rotated 180 degrees, they become invalid.
 * <p>
 * A confusing number is a number that when rotated 180 degrees becomes a different number with each digit valid.(Note that the rotated number can be greater than the original number.)
 * <p>
 * Given a positive integer N, return the number of confusing numbers between 1 and N inclusive.
 * <p>
 * Input: 20
 * Output: 6
 * Explanation:
 * The confusing numbers are [6,9,10,16,18,19].
 * 6 converts to 9.
 * 9 converts to 6.
 * 10 converts to 01 which is just 1.
 * 16 converts to 91.
 * 18 converts to 81.
 * 19 converts to 61.
 * <p>
 * Input: 100
 * Output: 19
 * Explanation:
 * The confusing numbers are [6,9,10,16,18,19,60,61,66,68,80,81,86,89,90,91,98,99,100].
 * <p>
 * Note:
 * 1 <= N <= 10^9
 */
public class ConfusingNumber2 {
    private final char[][] pairs = new char[][]{{'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}, {'9', '6'}};
    int[] digits = new int[]{0, 1, 6, 8, 9};
    char[] validDigits = new char[]{'0', '1', '6', '8', '9'};
    int ans;

    /**
     * Count the total number possible with digits 0,1,6,8,9 and then remove the numbers which if rotated are still the same ie. remove
     * strobogrammatic numbers, so we will be left with numbers those are confusing
     * <p>
     * {@link CrackingTheSafe} {@link MostSimilarPathInAGraph} {@link OptimalAccountBalancing} {@link StrobogrammaticNumber3}
     * {@link NumbersAtMostNGivenDigitSet} related hard google backtracking questions
     */
    public int confusingNumberIIOptimized(int N) {
        int totalNumbers = countNumbers(N);
        int strobogrammaticNumbers = countStrobogrammaticNumbers(N);
        return totalNumbers - strobogrammaticNumbers;
    }

    //count total numbers with digits 0,1,6,8,9 that are less than n
    private int countNumbers(int n) {
        String input = Integer.toString(n);
        int ans = 0;
        for (int len = 1; len <= input.length(); len++) {
            int val = recur(input, len, 1, len == input.length() ? 1 : 0); //isTight == 1 only when len == input.length()
            //refer to NumbersAtMostNGivenDigitSet for more detailed explaination
            ans += val;
        }
        return ans;
    }

    private int recur(String input, int length, int firstDigit, int isTight) {
        char ub = isTight == 1 ? input.charAt(input.length() - length) : '9';
        if (length == 1) {
            int ans = 0;
            for (char digit : validDigits) {
                if (isTight == 1 && digit > ub) {
                    break;
                }
                ans++;
            }
            return ans;
        } else {
            int ans = 0;
            for (int i = 0; i < validDigits.length; i++) {
                if (firstDigit == 1 && i == 0) { //can't place 0 on first digit
                    continue;
                }
                if (isTight == 1 && validDigits[i] > ub) { //need to exclude digits that are higher than allowed if under tight constraint
                    break;
                }
                ans += recur(input, length - 1, 0, isTight == 1 && validDigits[i] == ub ? 1 : 0);
                //tight constraint only when current digit is under tight constraint and is currently the max permissible
                //e.g. 4321, when at 0th index, if digit is 4, then we will be at tight constraint to restrict till 3 in the next digit
            }
            return ans;
        }
    }

    //count numbers that if rotated 180deg are still the same, refer to StrobogrammaticNumber3 for more description
    private int countStrobogrammaticNumbers(int n) {
        String input = Integer.toString(n);
        int ans = 0;
        for (int len = 1; len <= input.length(); len++) {
            int val = recur(0, len - 1, input, new char[len]);
            ans += val;
        }
        return ans;
    }

    //this looks a bit expensive but there are not many strobogrammatic numbers, hence it works fine [0,1e12] has 24999 such numbers
    private int recur(int left, int right, String input, char[] res) {
        if (left == right) { //in case of odd length, at the middlemost digit, we can only place, 0,1,8
            int ans = 0;
            for (int i = 0; i < pairs.length; i++) {
                if (pairs[i][0] != pairs[i][1]) {
                    continue;
                }
                res[left] = pairs[i][0];
                ans += recur(left + 1, right - 1, input, res);
                //can also directly evaluate the res[] here without recurring, but this makes code a bit shorter
            }
            return ans;
        } else if (left > right) { //when all characters have been placed, evaluate the current string
            String candidate = new String(res);
            if (candidate.length() == input.length() && candidate.compareTo(input) > 0) { //discard numbers greater than input
                return 0;
            }
            return 1;
        } else {
            int ans = 0;
            //iteratively place pairwise valid characters on first and last digit, need to exclude placing 0 on first digit
            for (int i = 0; i < pairs.length; i++) {
                if (left == 0 && pairs[i][0] == '0') {
                    continue;
                }
                res[left] = pairs[i][0];
                res[right] = pairs[i][1];
                ans += recur(left + 1, right - 1, input, res);
            }
            return ans;
        }
    }


    /**
     * Approach: Backtracking, generate all numbers and check if it's a confusing number or not
     */
    public int confusingNumberIIBruteForce(int N) {
        for (int i = 0; i < digits.length; i++) {
            recur(digits[i], N);
        }
        return ans;
    }

    private void recur(long number, int max) {
        if (number >= 1 && number <= max && isConfusingNumber(number)) {
            //if valid number, increment the count
            ans++;
        } else if (number == 0 || number > max) {
            //prune invalid number, 0 is a special case because if num is 0, stackOverflow occurs
            return;
        }
        for (int i = 0; i < digits.length; i++) {
            recur(number * 10 + digits[i], max);
        }
    }

    private boolean isConfusingNumber(long number) {
        long confusingNumber = 0;
        long temp = number;
        while (temp != 0) {
            long digit = temp % 10;
            long rotatedDigit;
            if (digit == 1 || digit == 0 || digit == 8) {
                rotatedDigit = digit;
            } else if (digit == 6) {
                rotatedDigit = 9;
            } else {
                rotatedDigit = 6;
            }
            confusingNumber = 10 * confusingNumber + rotatedDigit;
            temp /= 10;
        }
        return confusingNumber != number; //check if the rotated number is not equal to the original number
    }
}
