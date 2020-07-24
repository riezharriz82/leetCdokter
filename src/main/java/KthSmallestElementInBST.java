import common.TreeNode;

/**
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 * <p>
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: root = [3,1,4,null,2], k = 1
 * <pre>
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * </pre>
 * Output: 1
 */
public class KthSmallestElementInBST {
    int count;

    public int kthSmallest(TreeNode root, int k) {
        count = k;
        return inorderTraversal(root);
    }

    private int inorderTraversal(TreeNode root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        int leftRes = inorderTraversal(root.left);
        if (leftRes != Integer.MAX_VALUE) {
            count--;
            if (count == 0) {
                return root.val;
            }
            return inorderTraversal(root.right);

        } else {
            return leftRes;
        }
    }
}
