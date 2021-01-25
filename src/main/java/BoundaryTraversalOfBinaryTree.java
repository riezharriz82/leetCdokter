import common.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/boundary-of-binary-tree/ Premium
 * <p>
 * A binary tree boundary is the set of nodes (no duplicates) denoting the union of the left boundary, leaves, and right boundary.
 * <p>
 * The left boundary is the set of nodes on the path from the root to the left-most node. The right boundary is the set of nodes on the path
 * from the root to the right-most node.
 * <p>
 * The left-most node is the leaf node you reach when you always travel to the left subtree if it exists and the right subtree if it doesn't.
 * The right-most node is defined in the same way except with left and right exchanged. Note that the root may be the left-most and/or right-most node.
 * <p>
 * Given the root of a binary tree, return the values of its boundary in a counter-clockwise direction starting from the root.
 * <p>
 * Input:
 * <pre>
 *     ____1_____
 *    /          \
 *   2            3
 *  / \          /
 * 4   5        6
 *    / \      / \
 *   7   8    9  10
 * Output:
 * [1,2,4,7,8,9,10,6,3]
 *
 * Input:
 *      1
 *       \
 *        2
 *       / \
 *      3   4
 * Output: [1,3,4,2]
 * Explanation:
 * The left boundary is the nodes [1,2,3].
 * The right boundary is the nodes [1,2,4].
 * The leaves are nodes [3,4].
 * Unioning the sets together gives [1,2,3,4], which is [1,3,4,2] in counter-clockwise order.
 * </pre>
 */
public class BoundaryTraversalOfBinaryTree {
    /**
     * Approach: Tricky thing to note is to start left boundary traversal from root.left and right boundary traversal from root.right
     * It will simplify a lot of edge cases. During traversal, keep track of non leaf nodes as leaf nodes will be tracked separately
     * <p>
     * In my initial solution, I tried recurring from root for finding left and right boundary, keeping track of nodes in a visited set
     * However I could not match the output for the example [1,null,2,3,4] as I was returning [1,2,3,4] instead of [1,3,4,2]
     * <p>
     * {@link DeleteNodesAndReturnForest} {@link BinaryTreeColoringGame} {@link QuadTree} {@link CountNodesInCompleteBinaryTree}
     * {@link RightSideViewOfBinaryTree}
     */
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        } else if (root.left == null && root.right == null) { //if root is a leaf
            return Arrays.asList(root.val);
        }
        List<Integer> result = new ArrayList<>();
        result.add(root.val);
        leftBoundary(root.left, result); //start boundary traversal from left subtree of root, very crucial step
        leaves(root, result); //keep track of leaf nodes
        List<Integer> rightNodes = new ArrayList<>();
        rightBoundary(root.right, rightNodes); //start boundary traversal from right subtree of root
        Collections.reverse(rightNodes); //reverse the leaf nodes, stack can be also be used instead
        result.addAll(rightNodes);
        return result;
    }

    private void leftBoundary(TreeNode root, List<Integer> values) {
        if (root != null) {
            if (root.left != null || root.right != null) { //skip the leaf nodes by ensuring at least one child node is non null
                values.add(root.val);
            }
            if (root.left != null) {
                leftBoundary(root.left, values);
            } else {
                leftBoundary(root.right, values);
            }
        }
    }

    private void rightBoundary(TreeNode root, List<Integer> values) {
        if (root != null) {
            if (root.left != null || root.right != null) { //skip the leaf nodes by ensuring at least one child node is non null
                values.add(root.val);
            }
            if (root.right != null) {
                rightBoundary(root.right, values);
            } else {
                rightBoundary(root.left, values);
            }
        }
    }

    private void leaves(TreeNode root, List<Integer> values) {
        if (root != null) {
            if (root.left == null & root.right == null) {
                values.add(root.val);
            }
            leaves(root.left, values);
            leaves(root.right, values);
        }
    }
}
