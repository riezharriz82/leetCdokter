/**
 * https://leetcode.com/problems/broken-calculator/
 * <p>
 * On a broken calculator that has a number showing on its display, we can perform two operations:
 * <p>
 * Double: Multiply the number on the display by 2, or;
 * Decrement: Subtract 1 from the number on the display.
 * Initially, the calculator is displaying the number X.
 * <p>
 * Return the minimum number of operations needed to display the number Y.
 * <p>
 * Input: X = 2, Y = 3
 * Output: 2
 * Explanation: Use double operation and then decrement operation {2 -> 4 -> 3}.
 * <p>
 * Input: X = 5, Y = 8
 * Output: 2
 * Explanation: Use decrement and then double {5 -> 4 -> 8}.
 * <p>
 * Input: X = 3, Y = 10
 * Output: 3
 * Explanation:  Use double, decrement and double {3 -> 6 -> 5 -> 10}.
 * <p>
 * Input: X = 1024, Y = 1
 * Output: 1023
 * Explanation: Use decrement operations 1023 times.
 * <p>
 * Note:
 * 1 <= X <= 10^9
 * 1 <= Y <= 10^9
 */
public class BrokenCalculator {
    /**
     * Approach: Greedy, Start from the end instead.
     * <p>
     * BFS will give TLE, If you greedily start from front, you will double too early causing you to overshoot Y early
     * <p>
     * If the current value Y is odd, it can be reached only via a decrement operation, hence prev value was Y + 1
     * If the current value Y is even, it can be reached either via a decrement or multiply operation.
     * However the smallest steps would be possible only via multiply operation
     * <p>
     * At each step, question yourself, which operation would have led me to this intermediate value in min steps?
     * <p>
     * Was able to solve it after solving some examples on paper and revisiting the related problem
     * <p>
     * {@link alternate.TargetNumberWithOperations} {@link CheckIfAWordIsValidAfterSubstitution} related problem
     */
    public int brokenCalc(int X, int Y) {
        int steps = 0;
        while (Y > X) { //work backwards from Y to X
            if (Y % 2 == 0) {
                Y /= 2; //multiplication leads to the smallest steps
            } else {
                Y++; //if Y is odd, only decrement possible, hence the prev element would have been Y+1
            }
            steps++;
        }
        //if Y < X e.g (5,8), perform decrement operation
        return steps + (X - Y);
    }
}
