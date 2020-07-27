import common.TreeNode;

import java.util.HashMap;


/**
 * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/description/
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 * <p>
 * Note:
 * You may assume that duplicates do not exist in the tree.
 * <p>
 * For example, given
 * <p>
 * inorder = [9,3,15,20,7]
 * postorder = [9,15,7,20,3]
 * Return the following binary tree:
 * <pre>
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * </pre>
 */
public class ConstructTreeFromInorderAndPostorderTraversal {
    int postOrderIndex;

    // Approach of the problem is to first fix root and then recursively fix the root of the left/right subarray
    // this is done by using the postorder traversal as the last element is root
    // you look up this root value in inorder traversal to get left / right subarray
    // next element in postorder traversal belongs to the right subarray, so you decrement the postorder index and first visit right subarray
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        postOrderIndex = postorder.length - 1;
        return buildTree(postorder, map, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int[] postorder, HashMap<Integer, Integer> map, int inorderStart, int inorderEnd) {
        if (inorderStart > inorderEnd) { //handle empty array
            return null;
        } else if (inorderStart == inorderEnd) {
            return new TreeNode(postorder[postOrderIndex--]);
        } else {
            TreeNode root = new TreeNode(postorder[postOrderIndex]);
            int rootInorderIndex = map.get(postorder[postOrderIndex--]);
            root.right = buildTree(postorder, map, rootInorderIndex + 1, inorderEnd);
            root.left = buildTree(postorder, map, inorderStart, rootInorderIndex - 1);
            return root;
        }
    }
}
