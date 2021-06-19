import common.TreeNode;

/**
 * <pre>
 * https://leetcode.com/problems/find-distance-in-a-binary-tree/ Premium
 *
 * Given the root of a binary tree and two integers p and q, return the distance between the nodes of value p and value q in the tree.
 * The distance between two nodes is the number of edges on the path from one to the other.
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 0
 * Output: 3
 * Explanation: There are 3 edges between 5 and 0: 5-3-1-0.
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 7
 * Output: 2
 * Explanation: There are 2 edges between 5 and 7: 5-2-7.
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 5
 * Output: 0
 * Explanation: The distance between a node and itself is 0.
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 10^4].
 * 0 <= Node.val <= 10^9
 * All Node.val are unique.
 * p and q are values in the tree.
 * </pre>
 */
public class FindDistanceInABinaryTree {
    /**
     * Approach: Find the LCA first and then use the LCA to find the distance of the nodes from LCA in two different passes.
     * TimeComplexity: 3 linear passes.
     * Can be reduced to a single pass.
     * <p>
     * {@link LCABinaryTree} {@link LowestCommonAncestorOfABinaryTree4}
     */
    public int findDistance(TreeNode root, int p, int q) {
        if (p == q) {
            return 0;
        } else {
            TreeNode lca = findLCA(root, p, q);
            int dist1 = findDistance(lca, p);
            int dist2 = findDistance(lca, q);
            return dist1 + dist2;
        }
    }

    /**
     * There are always 2 ways to generally solve a tre question. Either pass information to the leaf or get back information from the leaf.
     * Here we are using information back from the leaf. Alternatively we could have passed curDistance as a param to the node and could have
     * used that to return in case we found the target node.
     */
    private int findDistance(TreeNode root, int target) {
        if (root == null) {
            return Integer.MAX_VALUE;
        } else if (root.val == target) {
            return 0;
        } else {
            int left = findDistance(root.left, target);
            int right = findDistance(root.right, target);
            if (left != Integer.MAX_VALUE) {
                return 1 + left;
            } else if (right != Integer.MAX_VALUE) {
                return 1 + right;
            } else {
                return Integer.MAX_VALUE;
            }
        }
    }

    private TreeNode findLCA(TreeNode root, int p, int q) {
        if (root == null) {
            return null;
        } else if (root.val == p || root.val == q) {
            return root;
        } else {
            TreeNode left = findLCA(root.left, p, q);
            TreeNode right = findLCA(root.right, p, q);
            if (left == null && right == null) {
                return null;
            } else if (left != null && right != null) { //found the LCA
                return root;
            } else if (left == null) { //right is already non null, so just pass back the info back to the parent.
                return right;
            } else {
                return left;
            }
        }
    }
}
