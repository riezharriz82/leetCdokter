import common.TreeNode;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/
 * <p>
 * Return any binary tree that matches the given preorder and postorder traversals.
 * <p>
 * Values in the traversals pre and post are distinct positive integers.
 * <p>
 * Input: pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
 * Output: [1,2,3,4,5,6,7]
 */
public class ConstructTreeFromPreorderAndPostorderTraversal {
    int preorderIndex;

    /**
     * {@link ConstructTreeFromInorderAndPostorderTraversal} {@link ConstructTreeFromInorderAndPreorderTraversal} for related problems
     */
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < post.length; i++) {
            map.put(post[i], i);
        }
        return buildTree(pre, post, map, 0, post.length - 1);
    }

    private TreeNode buildTree(int[] pre, int[] post, HashMap<Integer, Integer> map, int postorderStart, int postorderEnd) {
        if (postorderStart > postorderEnd) {
            return null;
        }
        if (postorderStart == postorderEnd) {
            return new TreeNode(pre[preorderIndex++]);
        }
        TreeNode root = new TreeNode(pre[preorderIndex++]);
        int rootPostorderIndex = map.get(pre[preorderIndex]);
        //do this on paper to get the feel on why we are offsetting the indexes
        root.left = buildTree(pre, post, map, postorderStart, rootPostorderIndex);
        root.right = buildTree(pre, post, map, rootPostorderIndex + 1, postorderEnd - 1);
        //postorderEnd - 1 is important because root was at postorderEnd
        return root;
    }
}
