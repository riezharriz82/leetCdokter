import common.TreeNode;

/**
 * https://leetcode.com/problems/find-a-corresponding-node-of-a-binary-tree-in-a-clone-of-that-tree/
 * <p>
 * Given two binary trees original and cloned and given a reference to a node target in the original tree.
 * <p>
 * The cloned tree is a copy of the original tree.
 * <p>
 * Return a reference to the same node in the cloned tree.
 * <p>
 * Note that you are not allowed to change any of the two trees or the target node and the answer must be a reference to a node in the cloned tree.
 * <p>
 * Follow up: Solve the problem if repeated values on the tree are allowed.
 * <p>
 * Input: tree = [7,4,3,null,null,6,19], target = 3
 * Output: 3
 * Explanation: In all examples the original and cloned trees are shown. The target node is a green node from the original tree.
 * The answer is the yellow node from the cloned tree.
 */
public class FindACorrespondingNodeOfBinaryTreeInACloneOfTree {
    /**
     * Approach: Walk both the trees simultaneously, when you find the node in original matches the target node, return the
     * corresponding node in the cloned tree.
     * <p>
     * In my initial submission instead of checking the reference, I compared them directly by value, which worked because the tree does
     * not have duplicates
     * <p>
     * {@link CloneGraph}
     */
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        return recur(original, cloned, target);
    }

    private TreeNode recur(TreeNode original, TreeNode cloned, TreeNode target) {
        if (original == null) {
            return null;
        } else if (original == target) { //match found, return the corresponding node in the cloned tree
            return cloned;
        } else {
            TreeNode result = recur(original.left, cloned.left, target);
            if (result != null) {
                return result;
            }
            return recur(original.right, cloned.right, target);
        }
    }
}
