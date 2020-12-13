import common.TreeNode;

/**
 * https://leetcode.com/problems/closest-binary-search-tree-value/
 * <p>
 * Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
 * <p>
 * Note:
 * Given target value is a floating point.
 * You are guaranteed to have only one unique value in the BST that is closest to the target.
 * <pre>
 * Input: root = [4,2,5,1,3], target = 3.714286
 *
 *     4
 *    / \
 *   2   5
 *  / \
 * 1   3
 * </pre>
 * Output: 4
 */
public class ClosestBinarySearchTreeValue {
    double minDiff = Double.MAX_VALUE;
    int result;

    /**
     * Approach: Since the given tree is a BST, we don't need to traverse the entire tree to find the target node
     * At every step, keep track of the min diff and the best node found so far
     * If current node is < target, recurse in right to find a closer node
     * If current node is > target, recurse in left to find a closer node
     */
    public int closestValue(TreeNode root, double target) {
        DFS(root, target);
        return result;
    }

    private void DFS(TreeNode root, double target) {
        if (root != null) {
            double curDiff = Math.abs(root.val - target);
            if (curDiff < minDiff) { //keep track of the result so far
                minDiff = curDiff;
                result = root.val;
            }
            if (target > root.val) {
                DFS(root.right, target);
            } else {
                DFS(root.left, target);
            }
        }
    }
}
