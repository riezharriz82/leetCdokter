import common.TreeNode;

/**
 * https://leetcode.com/problems/longest-univalue-path/
 * <p>
 * Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.
 * <p>
 * The length of path between two nodes is represented by the number of edges between them.
 * <pre>
 *               1
 *              / \
 *             4   5
 *            / \   \
 *           4   4   5
 * </pre>
 * Output: 2, {4-4-4}, need to return no of edges, so 2
 */
public class LongestUnivaluePath {
    int maxPath = 0;

    /**
     * Approach: Similar to {@link DiameterOfBinaryTree} but the constraint is to update the diameter only if the child nodes have the same val as parent
     * Return the max path possible by either considering left or right child
     */
    public int longestUnivaluePath(TreeNode root) {
        recur(root);
        return maxPath;
    }

    private int recur(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int left = recur(root.left);
            int right = recur(root.right);
            int currentPath = 0; //stores the length of the current path by evaluating results from left and right subtree
            int maxChildPath = 0; //stores the result to be returned to parent node
            if (root.left != null && root.left.val == root.val) {
                currentPath += (1 + left);
                maxChildPath = 1 + left;
            }
            if (root.right != null && root.right.val == root.val) {
                currentPath += (1 + right);
                maxChildPath = Math.max(maxChildPath, 1 + right); //path to return is either left or right, whichever is longer
            }
            maxPath = Math.max(maxPath, currentPath);
            return maxChildPath;
        }
    }
}
