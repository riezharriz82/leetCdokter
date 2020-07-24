import common.TreeNode;

/**
 * https://leetcode.com/problems/balanced-binary-tree/
 * <p>
 * Given a binary tree, determine if it is height-balanced.
 * For this problem, a height-balanced binary tree is defined as:
 * <p>
 * a binary tree in which the left and right subtrees of every node differ in height by no more than 1.
 * <p>
 * Given the following tree [3,9,20,null,null,15,7]:
 * <pre>
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * </pre>
 */
public class HeightBalancedBinaryTree {
    boolean isBalanced = true; //can avoid this by returning a special value from helper function when found to be unbalanced e.g. -1
    //need to check the left and right return value for -1 and then return -1 too

    public boolean isBalanced(TreeNode root) {
        helper(root);
        return isBalanced;
    }

    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = helper(root.left);
        int right = helper(root.right);
        if (Math.abs(left - right) > 1) {
            isBalanced = false;
        }
        return Math.max(left, right) + 1;
    }
}
