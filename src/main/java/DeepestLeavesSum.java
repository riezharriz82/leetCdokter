import common.TreeNode;

/**
 * https://leetcode.com/problems/deepest-leaves-sum/
 * <p>
 * Given the root of a binary tree, return the sum of values of its deepest leaves.
 * <p>
 * Input: root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
 * Output: 15
 * <p>
 * Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
 * Output: 19
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [1, 10^4].
 * 1 <= Node.val <= 100
 */
public class DeepestLeavesSum {
    /**
     * Approach: Two pass solution, first find the deepest level and then find the sum of nodes present at that level in second pass
     * Can also be done in a single pass by keeping track of curLevel and maxLevel.
     * If curLevel > maxLevel, reset sum. Increment sum only if curLevel == maxLevel
     * <p>
     * {@link ZigZagTraversal}
     */
    public int deepestLeavesSum(TreeNode root) {
        int deepestLevel = maxDepth(root);
        return sum(root, deepestLevel, 1);
    }

    private int sum(TreeNode root, int deepestLevel, int curLevel) {
        if (root == null) {
            return 0;
        } else if (curLevel == deepestLevel) {
            return root.val;
        } else {
            return sum(root.left, deepestLevel, curLevel + 1) + sum(root.right, deepestLevel, curLevel + 1);
        }
    }

    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
        }
    }
}
