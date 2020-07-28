import common.TreeNode;

/**
 * https://leetcode.com/problems/find-bottom-left-tree-value/
 * <p>
 * Given a binary tree, find the leftmost value in the last row of the tree.
 * <p>
 * Input:
 * <pre>
 *         1
 *        / \
 *       2   3
 *      /   / \
 *     4   5   6
 *        /
 *       7
 * </pre>
 * Output:
 * 7
 */
public class BottomLeftValueOfBinaryTree {
    int leftMostValue, maxLevel;

    //Simple DFS keeping tracking of the first node of new level
    public int findBottomLeftValue(TreeNode root) {
        DFS(root, 1);
        return leftMostValue;
    }

    private void DFS(TreeNode root, int level) {
        if (root != null) {
            if (level > maxLevel) {
                leftMostValue = root.val;
                maxLevel = level;
            }
            DFS(root.left, level + 1);
            DFS(root.right, level + 1);
        }
    }
}
