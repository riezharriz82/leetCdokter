/**
 * https://leetcode.com/problems/sum-of-square-numbers/
 */
public class SumOfSquareNumbers {

    public boolean twoPointerSolution(int c) {
        double sqrt = Math.sqrt(c);
        if (sqrt == (int) sqrt) {
            return true;
        }
        int left = (int) sqrt;
        int right = 1;
        while (right <= left) {
            if (left * left + right * right == c) {
                return true;
            } else if (left * left + right * right < c) {
                right++;
            } else {
                left--;
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
