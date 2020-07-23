/**
 * https://leetcode.com/problems/path-sum/
 * <p>
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
 * <p>
 * Note: A leaf is a node with no children.
 * <p>
 * Example:
 * <p>
 * Given the below binary tree and sum = 22,
 * <pre>
 *       5
 *      / \
 *     4   8
 *    /   / \
 *   11  13  4
 *  /  \      \
 * 7    2      1
 * </pre>
 */
public class RootToLeafPathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
        return helper(root, 0, sum);
    }

    private boolean helper(TreeNode root, int curSum, int target) {
        if (root == null) {
            return false;
        }
        curSum += root.val;
        //this explicit check is required so that we don't include path sum with only one child (1 -> 2)
        if (root.left == null && root.right == null) {
            return curSum == target;
        }
        return helper(root.left, curSum, target) || helper(root.right, curSum, target);
    }
}
