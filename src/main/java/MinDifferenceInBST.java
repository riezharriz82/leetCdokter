import common.TreeNode;

/**
 * https://leetcode.com/problems/minimum-distance-between-bst-nodes/
 * Given a Binary Search Tree (BST) with the root node root, return the minimum difference between the values of any two different nodes in the tree.
 * <p>
 * Example :
 * <p>
 * Input: root = [4,2,6,1,3,null,null]
 * Output: 1
 * Explanation:
 * Note that root is a common.TreeNode object, not an array.
 * <pre>
 *           4
 *         /   \
 *       2      6
 *      / \
 *     1   3
 * </pre>
 * while the minimum difference in this tree is 1, it occurs between node 1 and node 2, also between node 3 and node 2.
 */
public class MinDifferenceInBST {
    int min = Integer.MAX_VALUE;
    TreeNode prevNode;

    public int minDiffInBST(TreeNode root) {
        helper(root);
        return min;
    }

    private void helper(TreeNode root) {
        if (root == null) {
            return;
        }
        helper(root.left);
        if (prevNode != null) {
            min = Math.min(min, root.val - prevNode.val);
        }
        prevNode = root;
        helper(root.right);
    }
}
