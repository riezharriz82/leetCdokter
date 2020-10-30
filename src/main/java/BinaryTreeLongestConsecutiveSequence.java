import common.TreeNode;

/**
 * https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/
 * <p>
 * Given a binary tree, find the length of the longest consecutive sequence path.
 * <p>
 * The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
 * The longest consecutive path need to be from parent to child (cannot be the reverse).
 *
 * <pre>
 * Input:
 *
 *    1
 *     \
 *      3
 *     / \
 *    2   4
 *         \
 *          5
 * </pre>
 * Output: 3
 * Explanation: Longest consecutive sequence path is 3-4-5, so return 3.
 */
public class BinaryTreeLongestConsecutiveSequence {
    /**
     * Approach: As we learnt earlier that to solve tree related question, we can pass information down to the child or leverage information
     * gathered from the child, here I am passing information to the child ie. what is the current length of longest consecutive sequence and the
     * current parent.
     * <p>
     * Child can then decide whether it can extend the longest consecutive sequence or it has to start a new sequence.
     * <p>
     * {@link LongestConsecutiveSequence} related problem
     */

    int longestConsecutiveSequenceLength = 0;

    public int longestConsecutive(TreeNode root) {
        recur(root, null, 0);
        return longestConsecutiveSequenceLength;
    }

    private void recur(TreeNode root, Integer parent, int curConsecutiveSequenceLength) {
        longestConsecutiveSequenceLength = Math.max(longestConsecutiveSequenceLength, curConsecutiveSequenceLength);
        if (root != null) {
            if (parent != null && parent + 1 == root.val) { //if child can extend the previous consecutive sequence
                curConsecutiveSequenceLength += 1;
            } else {
                //start a new sequence
                curConsecutiveSequenceLength = 1;
            }
            recur(root.left, root.val, curConsecutiveSequenceLength); //update the current parent and pass in the current sequence length
            recur(root.right, root.val, curConsecutiveSequenceLength);
        }
    }
}
