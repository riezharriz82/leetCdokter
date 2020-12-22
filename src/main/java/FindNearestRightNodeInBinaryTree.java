import common.TreeNode;

/**
 * https://leetcode.com/problems/find-nearest-right-node-in-binary-tree/
 * <p>
 * Given the root of a binary tree and a node u in the tree, return the nearest node on the same level that is to the right of u,
 * or return null if u is the rightmost node in its level.
 * <p>
 * Input: root = [1,2,3,null,4,5,6], u = 4
 * Output: 5
 * Explanation: The nearest node on the same level to the right of node 4 is node 5.
 * <p>
 * Input: root = [3,null,4,2], u = 2
 * Output: null
 * Explanation: There are no nodes to the right of 2.
 */
public class FindNearestRightNodeInBinaryTree {
    /**
     * Approach: During DFS, keep track of the current level and the target level of the required node
     * If currentLevel is same as target level and result is not set, this means this is the right most node of the target.
     * This works because we always visit left subtree first and then right subtree.
     */
    int targetLevel = -1;
    TreeNode result;

    public TreeNode findNearestRightNode(TreeNode root, TreeNode u) {
        DFS(root, u, 0);
        return result;
    }

    private void DFS(TreeNode root, TreeNode target, int level) {
        if (root != null) {
            if (root == target) {
                targetLevel = level;
            } else if (result == null && level == targetLevel) {
                result = root;
            }
            DFS(root.left, target, level + 1);
            DFS(root.right, target, level + 1);
        }
    }
}
