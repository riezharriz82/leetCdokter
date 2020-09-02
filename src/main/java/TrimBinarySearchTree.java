import common.TreeNode;

/**
 * https://leetcode.com/problems/trim-a-binary-search-tree/
 * <p>
 * Given a binary search tree and the lowest and highest boundaries as L and R, trim the tree so that all its elements lies in [L, R] (R >= L).
 * You might need to change the root of the tree, so the result should return the new root of the trimmed binary search tree.
 * <pre>
 * Input:
 *     3
 *    / \
 *   0   4
 *    \
 *     2
 *    /
 *   1
 *
 *   L = 1
 *   R = 3
 *
 * Output:
 *       3
 *      /
 *    2
 *   /
 *  1
 * </pre>
 */
public class TrimBinarySearchTree {
    /**
     * Instead of explicitly finding the successor node we can directly recur in the left/right subtree in hope of finding a successor
     */
    public TreeNode trimBSTSimplified(TreeNode root, int L, int R) {
        if (root == null) {
            return null;
        } else if (root.val > R) {
            return trimBST(root.left, L, R); //recur in the left subtree to decrease root.val
        } else if (root.val < L) {
            return trimBST(root.right, L, R); //need to increase the root.val
        } else {
            root.left = trimBST(root.left, L, R);
            root.right = trimBST(root.right, L, R);
            return root;
        }
    }

    /**
     * Original solution, in case of a node being out of range, find the first node in the subtree that lies within the range
     * and return that successor to the parent instead
     */
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) {
            return null;
        } else if (root.val < L || root.val > R) {
            TreeNode successor = findSuccessor(root, L, R);
            if (successor != null) {
                //recur for successor
                successor.left = trimBST(successor.left, L, R);
                successor.right = trimBST(successor.right, L, R);
                return successor;
            } else {
                return null;
            }
        } else {
            root.left = trimBST(root.left, L, R);
            root.right = trimBST(root.right, L, R);
            return root;
        }
    }

    //returns the first node in the subtree of root whose value lies within range
    private TreeNode findSuccessor(TreeNode root, int L, int R) {
        if (root == null) {
            return null;
        } else if (root.val < L) {
            return findSuccessor(root.right, L, R);
        } else if (root.val > R) {
            return findSuccessor(root.left, L, R);
        } else {
            return root;
        }
    }
}
