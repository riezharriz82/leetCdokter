import common.TreeNode;

/**
 * https://leetcode.com/problems/symmetric-tree/
 * <p>
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 * <p>
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 * <pre>
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * </pre>
 * <p>
 * But the following [1,2,2,null,3,null,3] is not:
 * <pre>
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 * </pre>
 */
public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetricHelper(root.left, root.right);
    }

    private boolean isSymmetricHelper(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left == null || right == null) {
            return false;
        }
        return left.val == right.val && isSymmetricHelper(left.left, right.right) && isSymmetricHelper(left.right, right.left);
    }
}
