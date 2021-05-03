import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/gray-code/
 * <pre>
 * The gray code is a binary numeral system where two successive values differ in only one bit.
 *
 * Given an integer n representing the total number of bits in the code, return any sequence of gray code.
 *
 * A gray code sequence must begin with 0.
 *
 * Input: n = 2
 * Output: [0,1,3,2]
 * Explanation:
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 * [0,2,3,1] is also a valid gray code sequence.
 * 00 - 0
 * 10 - 2
 * 11 - 3
 * 01 - 1
 *
 * Input: n = 1
 * Output: [0,1]
 *
 * Constraints:
 * 1 <= n <= 16
 * </pre>
 */
public class GrayCode {
    /**
     * Approach: Very tricky recursion, instead of maintaining a visited set to check whether the current flip resulted in an unique number
     * leverage the recursion to flip the bits in a very specific order.
     * <p>
     * {@link Subsets} {@link PartitionKEqualSumSubsets}
     */
    int num;

    public List<Integer> grayCodeOptimized(int n) {
        List<Integer> grayCodes = new ArrayList<>();
        recur(n, grayCodes);
        return grayCodes;
    }

    private void recur(int n, List<Integer> grayCodes) {
        if (n == 0) {
            grayCodes.add(num);
        } else {
            recur(n - 1, grayCodes);
            num ^= (1 << (n - 1));
            //this subsequent recursion is very tricky
            recur(n - 1, grayCodes);
        }
    }

    /**
     * Approach: Backtracking, try to flip every digit and check if the number is already visited. If not, recur further.
     * If the flip is successful, there is no point in trying to flip other digits in the same recursion function, because the next
     * recursive function will take care of it.
     * <p>
     * This was my original solution.
     */
    public List<Integer> grayCode(int n) {
        int curNumber = 0;
        Set<Integer> visited = new HashSet<>();
        List<Integer> grayCodes = new ArrayList<>();
        visited.add(curNumber);
        grayCodes.add(curNumber);
        recur(curNumber, visited, grayCodes, n);
        return grayCodes;
    }

    private void recur(int curNumber, Set<Integer> visited, List<Integer> grayCodes, int n) {
        for (int i = 0; i < n; i++) {
            int temp = curNumber;
            temp ^= (1 << i); //flip only one bit
            if (!visited.contains(temp)) { //if this flip resulted in an unvisited number
                grayCodes.add(temp);
                recur(temp, visited, grayCodes, n);
                break; //no point in flipping other digits as it would have already been visited by the previous recursion call
            }
        }
    }
}
