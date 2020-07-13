/**
 * https://leetcode.com/problems/count-complete-tree-nodes/
 * <p>
 * Given a complete binary tree, count the number of nodes.
 * <p>
 * Definition of a complete binary tree from Wikipedia:
 * In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level
 * are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 *
 * <pre>
 * Input:
 *     1
 *    / \
 *   2   3
 *  / \  /
 * 4  5 6
 *
 * Output: 6
 * </pre>
 */
public class CountNodesInCompleteBinaryTree {
    public int countNodes(TreeNode root) {
        return OptimizedDFS(root);
    }

    private int OptimizedDFS(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int lh = leftHeight(node);
        int rh = rightHeight(node);
        if (lh == rh) { //its a complete binary tree
            return (1 << lh) - 1; // complete binary tree of height h has 2^h - 1 nodes
        } else {
            // one of the subtrees will always be complete and will immediately return
            // so we are pruning one half of tree every time (kinda like binary search), so there are log(n) steps and calculating height takes log(n) complexity
            // so total complexity is log(n) * log(n)
            return 1 + OptimizedDFS(node.left) + OptimizedDFS(node.right);
        }
    }

    private int rightHeight(TreeNode node) {
        int height = 0;
        while (node != null) {
            height++;
            node = node.right;
        }
        return height;
    }

    private int leftHeight(TreeNode node) {
        int height = 0;
        while (node != null) {
            height++;
            node = node.left;
        }
        return height;
    }
}
