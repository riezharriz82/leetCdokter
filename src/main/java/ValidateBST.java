import common.TreeNode;

/**
 * https://leetcode.com/problems/validate-binary-search-tree/
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * <p>
 * Assume a BST is defined as follows:
 * <p>
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * <p>
 * <p>
 * Example 1:
 * <pre>
 *     2
 *    / \
 *   1   3
 * </pre>
 * Input: [2,1,3]
 * Output: true
 */
public class ValidateBST {
    TreeNode previous; //A noob way would be to store previousValue in int or long i.e. long previousValue = Long.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {
        return helper(root);
    }

    private boolean helper(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean left = helper(root.left);
        if (left) {
            if (previous == null || root.val > previous.val) {
                previous = root;
                return helper(root.right);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
