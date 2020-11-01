import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/maximum-width-of-binary-tree/
 * <p>
 * Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels.
 * The binary tree has the same structure as a full binary tree, but some nodes are null.
 * <p>
 * The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level,
 * where the null nodes between the end-nodes are also counted into the length calculation.
 * <p>
 * This problem differs from the maximum diameter of a binary tree e.g {1,2,3,4} maximum width is 2 but maximum diameter is 3
 * Width is considered for one level
 */
public class MaximumWidthOfBinaryTree {
    /**
     * Approach: Leverage index property of parent/child in a tree. If parent index is i, left child will be at 2*i and right child
     * will be at 2*i + 1.
     * In the problem we are asked to find the max difference between indices in any level
     */
    public int widthOfBinaryTree(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        return DFS(root, list, 1, 0);
    }

    private int DFS(TreeNode root, List<Integer> list, int index, int level) {
        if (root == null) {
            return 0;
        }
        if (list.size() == level) { //first time seeing this level
            list.add(index); //add the first index
        }
        int curWidth = index - list.get(level); //compare the width against the first index
        int leftMaxWidth = DFS(root.left, list, 2 * index, level + 1);
        int rightMaxWidth = DFS(root.right, list, 2 * index + 1, level + 1);
        return Math.max(curWidth, Math.max(leftMaxWidth, rightMaxWidth));
    }
}
