import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/combinations/
 * <p>
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 * <p>
 * Input: n = 4, k = 2
 * Output:
 * <pre>
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ] </pre>
 */
public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        helper(res, new ArrayList<>(), 1, n, 0, k);
        return res;
    }

    private void helper(List<List<Integer>> res, ArrayList<Integer> current, int currentNumber, int maxNumber, int curSize, int maxSize) {
        if (curSize == maxSize) {
            res.add(new ArrayList<>(current));
        } else {
            for (int i = currentNumber; i <= maxNumber; i++) {
                if (curSize + 1 <= maxSize) {
                    current.add(i);
                    helper(res, current, i + 1, maxNumber, curSize + 1, maxSize);
                    current.remove(current.size() - 1);
                }
            }
        }
    }
}
