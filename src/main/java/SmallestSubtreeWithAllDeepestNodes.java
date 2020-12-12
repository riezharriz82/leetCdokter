import common.TreeNode;

/**
 * https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/
 * <p>
 * Given the root of a binary tree, the depth of each node is the shortest distance to the root.
 * <p>
 * Return the smallest subtree such that it contains all the deepest nodes in the original tree.
 * <p>
 * A node is called the deepest if it has the largest depth possible among any node in the entire tree.
 * <p>
 * The subtree of a node is tree consisting of that node, plus the set of all descendants of that node.
 * <p>
 * Note: This question is the same as 1123: https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/
 * <p>
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4]
 * Output: [2,7,4]
 * Explanation: We return the node with value 2, colored in yellow in the diagram.
 * The nodes coloured in blue are the deepest nodes of the tree.
 * Notice that nodes 5, 3 and 2 contain the deepest nodes in the tree but node 2 is the smallest subtree among them, so we return it.
 * <p>
 * Input: root = [1]
 * Output: [1]
 * Explanation: The root is the deepest node in the tree.
 * <p>
 * Input: root = [0,1,3,null,2]
 * Output: [2]
 * Explanation: The deepest node in the tree is 2, the valid subtrees are the subtrees of nodes 2, 1 and 0 but the subtree of node 2 is the smallest.
 */
public class SmallestSubtreeWithAllDeepestNodes {
    TreeNode lca;

    /**
     * Approach: Two pass algorithm, first pass to find the max depth of the tree.
     * Second pass to find nodes whose left and right subtree are at the max depth, last such node is the answer
     * Can be merged together to do it in a single pass
     * <p>
     * Another cool approach is to find the first and last node of the last level, then find the lca of those two nodes.
     * That will be the answer.
     * <pre>
     *          5
     *      2       8
     *    1   4   3   9
     *   0 3 6 9 8 7 0 4
     * </pre>
     * 5 is the answer
     * <p>
     * {@link LCABinaryTree}
     */
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        int maxDepth = recur(root, 0); //find the max depth of the tree
        lca(root, maxDepth, 0);
        return lca;
    }

    private int lca(TreeNode root, int maxDepth, int curDepth) {
        if (root == null) {
            return curDepth;
        } else {
            int leftDepth = lca(root.left, maxDepth, curDepth + 1);
            int rightDepth = lca(root.right, maxDepth, curDepth + 1);
            int smallestDepth = Math.min(leftDepth, rightDepth);
            if (smallestDepth == maxDepth) { //if left and right subtree are at the max depth, this node is the answer
                //lca changes multiple times during the tree traversal hence the global variable
                lca = root;
            }
            return Math.max(leftDepth, rightDepth);
        }
    }

    private int recur(TreeNode root, int depth) {
        if (root == null) {
            return depth;
        } else {
            return Math.max(recur(root.left, depth + 1), recur(root.right, depth + 1));
        }
    }
}
