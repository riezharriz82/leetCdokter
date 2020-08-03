import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/pascals-triangle/
 * <p>
 * Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
 * <p>
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.
 * <p>
 * Input: 5
 * Output:
 * <pre>
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 * </pre>
 */
public class PascalsTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows == 0) {
            return res;
        }
        res.add(Arrays.asList(1));
        for (int i = 1; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            row.add(1); //first number in the row is fixed
            for (int j = 0; j < i - 1; j++) { // each row has i + 1 number, 2 of which are fixed, left are i + 1 - 2 numbers
                row.add(res.get(i - 1).get(j) + res.get(i - 1).get(j + 1)); //previous row is i - 1, previous columns for jth index are j and j+1
            }
            row.add(1); //last number in the row is fixed
            res.add(row);
        }
        return res;
    }
}
