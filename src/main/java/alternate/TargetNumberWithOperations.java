package alternate;

/**
 * https://binarysearch.io/problems/Target-Number-with-Operations
 * <p>
 * Given positive integers start and end (start < end), return the minimum number of operations needed to convert start to end using these operations:
 * <p>
 * Increment by 1
 * Multiply by 2
 * Constraints
 * <p>
 * start < end < 2 ** 31
 * <p>
 * Input start = 2 end = 9
 * <p>
 * Output 3
 */
public class TargetNumberWithOperations {
    /**
     * Approach: In case of problems dealing with finding min steps to reach start to end, its always prudent to consider the path from end to start too
     * If you start from start, you will double too much too fast and in the end you will be left with +1 operations
     * However if you start from end, if you reach an odd number, its guaranteed that you can't reach this by *2 operation, only +1 is a valid operation
     * If you reach an even number, fastest way to reach start would be to *2 operation unless start > end/2, then only +1 is a valid operation
     */
    public int solve(int start, int end) {
        int cnt = 0;
        while (end / 2 >= start) {
            if (end % 2 == 1) {
                //only +1 is valid operation
                end--;
            } else {
                //*2 will lead to min steps
                end >>= 1;
            }
            cnt++;
        }
        return cnt + (end - start); //if start > end/2, only +1 operation is valid
    }
}
