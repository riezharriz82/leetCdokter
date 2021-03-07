import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/average-of-levels-in-binary-tree/
 * <p>
 * Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.
 * <pre>
 * Input:
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * Output: [3, 14.5, 11]
 * </pre>
 * <p>
 * Explanation:
 * The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].
 * Note:
 * The range of node's value is in the range of 32-bit signed integer.
 */
public class AverageOfLevelsInBinaryTree {
    /**
     * Approach: Use DFS to first count the no of nodes present in each level and then again use DFS to find the total sum of nodes at each level
     * Then finally calculate the average of each levels.
     * <p>
     * BFS can reduce the no of passes as during BFS we already know the no of nodes present in each level
     * <p>
     * {@link MovingAverageFromDataStream}
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Long> sum = new ArrayList<>();
        List<Integer> count = new ArrayList<>();
        DFS(root, sum, count, 0);
        List<Double> res = new ArrayList<>();
        for (int i = 0; i < sum.size(); i++) {
            res.add((double) sum.get(i) / count.get(i));
        }
        return res;
    }

    private void DFS(TreeNode root, List<Long> sum, List<Integer> count, int level) {
        if (root != null) {
            if (level == sum.size()) {
                sum.add(0L);
                count.add(0);
            }
            sum.set(level, sum.get(level) + root.val);
            count.set(level, count.get(level) + 1);
            DFS(root.left, sum, count, level + 1);
            DFS(root.right, sum, count, level + 1);
        }
    }
}
