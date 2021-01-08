import common.TreeNode;

/**
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 * <p>
 * Given a non-empty binary tree, find the maximum path sum.
 * <p>
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
 * The path must contain at least one node and does not need to go through the root.
 * <p>
 * Input: [-10,9,20,null,null,15,7]
 * <pre>
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * </pre>
 * Output: 42
 */
public class BinaryTreeMaxPathSum {
    private int maxSum = Integer.MIN_VALUE;

    /**
     * Approach: Tricky thing is to handle negative number, if a root node has negative children, largest path would never be formed if you chose
     * negative children. Simply ignore -ve children. It handles a lot of if's and else's.
     */
    public int maxPathSum(TreeNode root) {
        helper(root);
        return maxSum;
    }

    /**
     * Similar to kadane algorithm
     * <pre>
     *     -1
     *       \
     *       -2
     * </pre>
     */
    private int helper(TreeNode root) {
        if (root == null) return 0;
        //using max acts kinda like kadane algorithm, extend the left path only if it leads to a greater result than root
        // if left path is negative, it can never lead to a greater result, however if it's positive; max will take care of it
        int left = Math.max(helper(root.left), 0);
        int right = Math.max(helper(root.right), 0);

        //current path best sum would be to add left and right subtree with root value
        maxSum = Math.max(maxSum, root.val + left + right);

        //choose max of one path and add to root to return to parent
        return root.val + Math.max(left, right);
    }
}
