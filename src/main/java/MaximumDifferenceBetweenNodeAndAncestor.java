import common.TreeNode;

/**
 * https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/
 * <p>
 * Given the root of a binary tree, find the maximum value V for which there exist different nodes A and B where V = |A.val - B.val| and A is an ancestor of B.
 * <p>
 * A node A is an ancestor of B if either: any child of A is equal to B, or any child of A is an ancestor of B.
 * <p>
 * Input: root = [8,3,10,1,6,null,14,null,null,4,7,13]
 * Output: 7
 * Explanation: We have various ancestor-node differences, some of which are given below :
 * |8 - 3| = 5
 * |3 - 7| = 4
 * |8 - 1| = 7
 * |10 - 13| = 3
 * Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.
 */
public class MaximumDifferenceBetweenNodeAndAncestor {
    /**
     * Approach: As part of prior learnings, max absolute difference is found out by getting the diff between min and max elements.
     * Similar to range bound property of BST traversal, we provide min and max found so far for each node and try to find the max diff so far
     * <p>
     * {@link MaximumAbsoluteSumOfSubarray}
     */
    public int maxAncestorDiff(TreeNode root) {
        return recur(root, root.val, root.val);
    }

    private int recur(TreeNode root, int minVal, int maxVal) {
        if (root == null) {
            return 0;
        } else {
            //in bst we know that the current node will either act as new min in right subtree or new max in left subtree
            //but for binary tree, we don't know that, so we have to try updating both min and max
            int newMin = Math.min(minVal, root.val);
            int newMax = Math.max(maxVal, root.val);
            int curDiff = Math.abs(newMin - newMax);
            int leftDiff = recur(root.left, newMin, newMax); //max diff returned from left subtree
            int rightDiff = recur(root.right, newMin, newMax); //max diff returned from right subtree
            return Math.max(leftDiff, Math.max(rightDiff, curDiff));
        }
    }
}
