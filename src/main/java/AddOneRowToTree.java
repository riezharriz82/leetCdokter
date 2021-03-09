import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/add-one-row-to-tree/
 * <p>
 * Given the root of a binary tree, then value v and depth d, you need to add a row of nodes with value v at the given depth d. The root node is at depth 1.
 * <p>
 * The adding rule is: given a positive integer depth d, for each NOT null tree nodes N in depth d-1, create two tree nodes with value v as N's left subtree root and right subtree root.
 * And N's original left subtree should be the left subtree of the new left subtree root, its original right subtree should be the right subtree of the new right subtree root.
 * If depth d is 1 that means there is no depth d-1 at all, then create a tree node with value v as the new root of the whole original tree, and the original tree is the new root's left subtree.
 * <pre>
 * Input:
 *        4
 *      /   \
 *     2     6
 *    / \   /
 *   3   1 5
 *
 * v = 1 d = 2
 *
 * Output:
 *        4
 *       / \
 *      1   1
 *     /     \
 *    2       6
 *   / \     /
 *  3   1   5
 *
 * Input:
 *       4
 *      /
 *     2
 *    / \
 *   3   1
 *
 * v = 1 d = 3
 *
 * Output:
 *       4
 *      /
 *     2
 *    / \
 *   1   1
 *  /     \
 * 3       1
 * </pre>
 * Note:
 * The given d is in range [1, maximum depth of the given tree + 1].
 * The given binary tree has at least one tree node.
 */
public class AddOneRowToTree {
    /**
     * Approach: Use Recursion to maintain a mapping of all nodes present at the d-1 level.
     * For such nodes, insert the new node in left and right.
     * <p>
     * {@link DeleteNodesAndReturnForest}
     */
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode newRoot = new TreeNode(v);
            newRoot.left = root;
            return newRoot;
        } else {
            List<TreeNode> requiredNodes = new ArrayList<>();
            DFS(root, requiredNodes, d - 1, 1); //store all nodes present at d-1 level in requiredNodes
            //can also be done in-place during recursion itself instead of storing nodes
            for (TreeNode requiredNode : requiredNodes) {
                //insert the node in the left
                TreeNode tempLeft = requiredNode.left;
                requiredNode.left = new TreeNode(v);
                requiredNode.left.left = tempLeft;
                //insert the node in the right
                TreeNode tempRight = requiredNode.right;
                requiredNode.right = new TreeNode(v);
                requiredNode.right.right = tempRight;
            }
            return root;
        }
    }

    private void DFS(TreeNode root, List<TreeNode> requiredNodes, int requiredDepth, int curDepth) {
        if (root != null) {
            if (curDepth == requiredDepth) {
                requiredNodes.add(root);
            }
            DFS(root.left, requiredNodes, requiredDepth, curDepth + 1);
            DFS(root.right, requiredNodes, requiredDepth, curDepth + 1);
        }
    }
}
