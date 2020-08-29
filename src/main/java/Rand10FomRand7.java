import java.util.Random;

/**
 * https://leetcode.com/problems/implement-rand10-using-rand7/
 * <p>
 * Given the API rand7 which generates a uniform random integer in the range 1 to 7, write a function rand10 which generates a
 * uniform random integer in the range 1 to 10. You can only call the API rand7 and you shouldn't call any other API.
 * Please don't use the system's Math.random().
 * <p>
 * Notice that Each test case has one argument n, the number of times that your implemented function rand10 will be called while testing.
 * <p>
 * Follow up:
 * <p>
 * What is the expected value for the number of calls to rand7() function?
 * Could you minimize the number of calls to rand7()?
 * <p>
 * Input: n = 1
 * Output: [2]
 */
public class Rand10FomRand7 {
    /**
     * Approach: Rejection sampling - If the number is outside our expectation, reject and sample again
     * If we call rand7() twice, we can generate random numbers from 1 to 49 uniformly.
     * But we are only interested from 1 to 10, so how can we map 1-49 to 1-10?
     * <p>
     * In my initial approach, i tried taking the sum of two values, but the value was non-uniform (values getting repeated)
     * The correct way to map them would be to consider a 7*7 matrix and first roll giving the row and second roll giving the col.
     * Now we can visualize those 49 numbers as 1-10, 1-10, 1-10, 1-10, 1-9
     * If we discard the last 1-9 range, then probability of picking each number is 4/40 = 1/10 (the desired probability)
     * <p>
     * So we discard any number greater than 40 and repeat the process again.
     */
    public int rand10() {
        int row = rand7();
        int col = rand7();
        int number = (row - 1) * 7 + col;
        if (number > 40) {
            return rand10();
        } else {
            //handle special case for 10
            return number % 10 == 0 ? 10 : number % 10;
        }
    }

    private int rand7() {
        return new Random().nextInt(7) + 1;
    }
}
