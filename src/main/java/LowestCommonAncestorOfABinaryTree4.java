import common.TreeNode;

import java.util.HashSet;

/**
 * <pre>
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iv/ Premium
 *
 * Given the root of a binary tree and an array of TreeNode objects nodes, return the lowest common ancestor (LCA) of all the nodes in nodes.
 * All the nodes will exist in the tree, and all values of the tree's nodes are unique.
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], nodes = [4,7]
 * Output: 2
 * Explanation: The lowest common ancestor of nodes 4 and 7 is node 2.
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], nodes = [1]
 * Output: 1
 * Explanation: The lowest common ancestor of a single node is the node itself.
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], nodes = [7,6,2,4]
 * Output: 5
 * Explanation: The lowest common ancestor of the nodes 7, 6, 2, and 4 is node 5.
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], nodes = [0,1,2,3,4,5,6,7,8]
 * Output: 3
 * Explanation: The lowest common ancestor of all the nodes is the root node.
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 10^4].
 * -10^9 <= Node.val <= 10^9
 * All Node.val are unique.
 * All nodes[i] will exist in the tree.
 * All nodes[i] are distinct.
 * </pre>
 */
public class LowestCommonAncestorOfABinaryTree4 {
    /**
     * Approach: Standard LCA Question with a twist of finding LCA for K nodes instead of 2 nodes.
     * Store all target nodes in a set and apply the same solution for a normal LCA Code.
     *
     * {@link LCABinarySearchTree} {@link SmallestSubtreeWithAllDeepestNodes} {@link LCABinaryTree}
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        var targetNodes = new HashSet<Integer>();
        for (TreeNode node : nodes) {
            targetNodes.add(node.val);
        }
        return recur(root, targetNodes);
    }

    private TreeNode recur(TreeNode root, HashSet<Integer> targetNodes) {
        if (root == null) {
            return null;
        } else if (targetNodes.contains(root.val)) {
            return root;
        } else {
            TreeNode left = recur(root.left, targetNodes);
            TreeNode right = recur(root.right, targetNodes);
            if (left != null && right != null) {
                return root;
            } else if (left == null) {
                return right;
            } else {
                return left;
            }
        }
    }
}
