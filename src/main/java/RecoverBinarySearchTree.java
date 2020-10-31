import common.TreeNode;

/**
 * https://leetcode.com/problems/recover-binary-search-tree/
 * <p>
 * You are given the root of a binary search tree (BST), where exactly two nodes of the tree were swapped by mistake. Recover the tree without changing its structure.
 * <p>
 * Follow up: A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
 * <p>
 * Input: root = [1,3,null,null,2]
 * Output: [3,1,null,null,2]
 * Explanation: 3 cannot be a left child of 1 because 3 > 1. Swapping 1 and 3 makes the BST valid.
 */
public class RecoverBinarySearchTree {
    TreeNode prev, first, second;

    /**
     * Approach: If given a nearly sorted array, where two elements are swapped, how will you sort the array?
     * <p>
     * Typical solution is to sort the array and compare the mismatches, but it can be done in linear time by keeping track of
     * mismatched indices in two pointers
     * e.g {1,3,6,5,4,8}, first mismatch is at 5, 6 > 5 and second mismatch is at 4, 5 > 4
     * Need to swap 6 and 4
     * <p>
     * Care must be taken to handle single cases like {1,4,2,5,6}
     * <p>
     * We follow the same approach for bst as well because inorder traversal of bst gives sorted array.
     * This uses stack which can take o(logn) space, to do it in constant space use morris traversal
     */
    public void recoverTree(TreeNode root) {
        recur(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    private void recur(TreeNode root) {
        if (root == null) {
            return;
        }
        recur(root.left);
        if (prev != null && prev.val > root.val) { //mismatch found
            second = root;
            if (first == null) { //only update the first pointer for the first mismatch
                first = prev;
            }
        }
        prev = root;
        recur(root.right);
    }
}
