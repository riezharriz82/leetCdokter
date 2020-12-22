import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/find-positive-integer-solution-for-a-given-equation/
 * <p>
 * Given a function  f(x, y) and a value z, return all positive integer pairs x and y where f(x,y) == z.
 * <p>
 * The function is constantly increasing, i.e.:
 * <p>
 * f(x, y) < f(x + 1, y)
 * f(x, y) < f(x, y + 1)
 * The function interface is defined like this:
 * <p>
 * interface CustomFunction {
 * public:
 * // Returns positive integer f(x, y) for any given positive integer x and y.
 * int f(int x, int y);
 * };
 * For custom testing purposes you're given an integer function_id and a target z as input, where function_id represent one function
 * from an secret internal list, on the examples you'll know only two functions from the list.
 * <p>
 * You may return the solutions in any order.
 * <p>
 * Input: function_id = 1, z = 5
 * Output: [[1,4],[2,3],[3,2],[4,1]]
 * Explanation: function_id = 1 means that f(x, y) = x + y
 * <p>
 * Input: function_id = 2, z = 5
 * Output: [[1,5],[5,1]]
 * Explanation: function_id = 2 means that f(x, y) = x * y
 * <p>
 * Constraints:
 * 1 <= function_id <= 9
 * 1 <= z <= 100
 * It's guaranteed that the solutions of f(x, y) == z will be on the range 1 <= x, y <= 1000
 * It's also guaranteed that f(x, y) will fit in 32 bit signed integer if 1 <= x, y <= 1000
 */
public class FindPositiveIntegerSolutionForGivenEquation {
    /**
     * Approach: Since the function is monotonic increasing, Binary search can be applied to first find the valid x, then valid y
     * For x, we need to find the last x, where f(x,1) <= z, because if f(x,1) > z, there is no point in incrementing y
     * <p>
     * Now for each 1 <= x <= last_valid_x, apply binary search on 1 <= y <= 1000 to find combination of f(x,y) == z
     * <p>
     * Another optimization to it would be to consider it as a 2D matrix instead and solve it in linear time
     * similar to {@link Search2DMatrixII}
     */
    public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
        List<List<Integer>> res = new ArrayList<>();
        int low = 1, high = 1000, lastValidX = -1; //last valid x stores the value of x such that f(x,1) <= z
        while (low <= high) {
            int x_mid = low + (high - low) / 2;
            if (customfunction.f(x_mid, 1) > z) {
                high = x_mid - 1;
            } else {
                lastValidX = x_mid;
                low = x_mid + 1;
            }
        }
        //fix x and binary search for y
        for (int i = 1; i <= lastValidX; i++) {
            low = 1;
            high = 1000;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                int val = customfunction.f(i, mid);
                if (val == z) {
                    res.add(Arrays.asList(i, mid));
                    break;
                } else if (val < z) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return res;
    }

    private interface CustomFunction {
        // Returns f(x, y) for any given positive integers x and y.
        // Note that f(x, y) is increasing with respect to both x and y.
        // i.e. f(x, y) < f(x + 1, y), f(x, y) < f(x, y + 1)
        int f(int x, int y);
    }
}
