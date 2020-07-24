package alternate;

import common.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetfree.com/problems/boundary-of-binary-tree.html
 * <p>
 * Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root.
 * Boundary includes left boundary, leaves, and right boundary in order without duplicate nodes.
 * <p>
 * Left boundary is defined as the path from root to the left-most node.
 * Right boundary is defined as the path from root to the right-most node.
 * If the root doesn't have left subtree or right subtree, then the root itself is left boundary or right boundary.
 * Note this definition only applies to the input binary tree, and not applies to any subtrees.
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
 * </pre>
 * Output:
 * [1,2,4,7,8,9,10,6,3]
 */
public class BoundaryTraversalOfBinaryTree {
    /**
     * One simple solution is to divide the problem into three sub-problems, left boundary, leaves, right boundary (in reverse)
     * During left/right boundary traversal make sure to not add leaf nodes
     * <p>
     * problem statement is very similar to the Preorder traversal. Actually, the order of traversal is the same(except for the right boundary nodes,
     * for which it is the reverse), but we need to selectively include the nodes in the return result list.
     * Thus, we need to include only those nodes in the result, which are either on the left boundary, the leaves or the right boundary.
     * <p>
     * In order to distinguish between the various kinds of nodes, we make use of a as follows:
     * <p>
     * Flag=0: Root Node.
     * <p>
     * Flag=1: Left Boundary Node.
     * <p>
     * Flag=2: Right Boundary Node.
     * <p>
     * Flag=3: Others(Middle Node).
     * <p>
     * We make use of three lists , , to store the appropriate nodes and append the three lists at the end.
     * <p>
     * We go for the normal preorder traversal, but while calling the recursive function for preorder traversal using the left child or the right child of the current node,
     * we also pass the information indicating the type of node that the current child behaves like.
     * <p>
     * For obtaining the flag information about the left child of the current node, we make use of the function leftChildFlag(node, flag). In the case of a left child,
     * the following cases are possible, as can be verified by looking at the figure above:
     * <p>
     * The current node is a left boundary node: In this case, the left child will always be a left boundary node.
     * <p>
     * The current node is a root node: In this case, the left child will always be a left boundary node
     * <p>
     * The current node is a right boundary node: In this case, if the right child of the current node doesn't exist, the left child always acts as the right boundary node.
     * But, if the right child exists, the left child always acts as the middle node.
     * <p>
     * Similarly, for obtaining the flag information about the right child of the current node, we make use of the function rightChildFlag(node, flag). In the case of a right child,
     * the following cases are possible, as can be verified by looking at the figure above:
     * <p>
     * The current node is a right boundary node: In this case, the right child will always be a right boundary node.
     * <p>
     * The current node is a root node: In this case, the right child will always be a left boundary node.
     * <p>
     * The current node is a left boundary node: In this case, if the left child of the current node doesn't exist, the right child always acts as the left boundary node.
     * But, if the left child exists, the left child always acts as the middle node.
     */
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> left_boundary = new LinkedList<>(), right_boundary = new LinkedList<>(), leaves = new LinkedList<>();
        preorder(root, left_boundary, right_boundary, leaves, 0);
        left_boundary.addAll(leaves);
        left_boundary.addAll(right_boundary);
        return left_boundary;
    }

    public boolean isLeaf(TreeNode cur) {
        return (cur.left == null && cur.right == null);
    }

    public boolean isRightBoundary(int flag) {
        return (flag == 2);
    }

    public boolean isLeftBoundary(int flag) {
        return (flag == 1);
    }

    public boolean isRoot(int flag) {
        return (flag == 0);
    }

    public int leftChildFlag(TreeNode cur, int flag) {
        if (isLeftBoundary(flag) || isRoot(flag))
            return 1;
        else if (isRightBoundary(flag) && cur.right == null)
            return 2;
        else return 3;
    }

    public int rightChildFlag(TreeNode cur, int flag) {
        if (isRightBoundary(flag) || isRoot(flag))
            return 2;
        else if (isLeftBoundary(flag) && cur.left == null)
            return 1;
        else return 3;
    }

    public void preorder(TreeNode cur, List<Integer> left_boundary, List<Integer> right_boundary, List<Integer> leaves, int flag) {
        if (cur == null)
            return;
        if (isRightBoundary(flag))
            right_boundary.add(0, cur.val);
        else if (isLeftBoundary(flag) || isRoot(flag))
            left_boundary.add(cur.val);
        else if (isLeaf(cur))
            leaves.add(cur.val);
        preorder(cur.left, left_boundary, right_boundary, leaves, leftChildFlag(cur, flag));
        preorder(cur.right, left_boundary, right_boundary, leaves, rightChildFlag(cur, flag));
    }
}
