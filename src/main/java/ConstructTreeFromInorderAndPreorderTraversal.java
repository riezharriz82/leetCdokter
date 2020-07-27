import common.TreeNode;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * <p>
 * Note:
 * You may assume that duplicates do not exist in the tree.
 * <p>
 * preorder = [3,9,20,15,7]
 * inorder = [9,3,15,20,7]
 * Return the following binary tree:
 * <pre>
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * </pre>
 */
public class ConstructTreeFromInorderAndPreorderTraversal {
    int preorderIndex;

    /**
     * Approach is similar to {@link ConstructTreeFromInorderAndPostorderTraversal}
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(preorder, map, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, HashMap<Integer, Integer> map, int inorderStart, int inorderEnd) {
        if (inorderStart > inorderEnd) { //handle empty array
            return null;
        } else if (inorderStart == inorderEnd) {
            return new TreeNode(preorder[preorderIndex++]);
        } else {
            TreeNode root = new TreeNode(preorder[preorderIndex]);
            int rootInorderIndex = map.get(preorder[preorderIndex++]);
            root.left = buildTree(preorder, map, inorderStart, rootInorderIndex - 1);
            root.right = buildTree(preorder, map, rootInorderIndex + 1, inorderEnd);
            return root;
        }
    }
}
