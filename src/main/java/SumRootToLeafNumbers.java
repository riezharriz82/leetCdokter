import common.TreeNode;

/**
 * https://leetcode.com/problems/sum-root-to-leaf-numbers/
 * <p>
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 * <p>
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 * <p>
 * Find the total sum of all root-to-leaf numbers.
 * <p>
 * Input: [1,2,3]
 * <pre>
 *     1
 *    / \
 *   2   3
 * </pre>
 * Output: 25
 * Explanation:
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Therefore, sum = 12 + 13 = 25.
 */
public class SumRootToLeafNumbers {
    public int sumNumbers(TreeNode root) {
        return DFS(root, 0);
    }

    private int DFS(TreeNode root, int currentNumber) {
        if (root == null) {
            return 0;
        } else {
            currentNumber = 10 * currentNumber + root.val;
            if (root.left == null && root.right == null) {
                return currentNumber;
            } else {
                return DFS(root.left, currentNumber) + DFS(root.right, currentNumber);
            }
        }
    }
}
