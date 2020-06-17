/**
 * https://leetcode.com/problems/sum-of-square-numbers/
 * Given a non-negative integer c, your task is to decide whether there're two integers a and b such that a2 + b2 = c.
 * Input: 5
 * Output: True
 * Explanation: 1 * 1 + 2 * 2 = 5
 */
public class SumOfSquareNumbers {

    public boolean twoPointerSolution(int c) {
        double sqrt = Math.sqrt(c);
        if (sqrt == (int) sqrt) {
            return true;
        }
        int right = (int) sqrt;
        int left = 1;
        while (left <= right) {
            if (left * left + right * right == c) {
                return true;
            } else if (left * left + right * right < c) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }

    public boolean judgeSquareSum(int c) {
        int sqrt = (int) Math.sqrt(c);
        for (int i = 0; i <= sqrt; i++) {
            int bSquared = c - (i * i);
            double sqrt1 = Math.sqrt(bSquared);
            if (sqrt1 == (int) sqrt1) {
                return true;
            }
        }
        return false;
    }

    //Another solution can be by leveraging a square number is 1+3+5+7+..
    //See https://leetcode.com/problems/valid-perfect-square/discuss/83874/A-square-number-is-1%2B3%2B5%2B7%2B...-JAVA-code
}
