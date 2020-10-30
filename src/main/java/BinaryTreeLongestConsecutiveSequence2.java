import common.TreeNode;
import javafx.util.Pair;

/**
 * https://leetcode.com/problems/binary-tree-longest-consecutive-sequence-ii/
 * <p>
 * Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.
 * <p>
 * Especially, this path can be either increasing or decreasing. For example, [1,2,3,4] and [4,3,2,1] are both considered valid,
 * but the path [1,2,4,3] is not valid. On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.
 *
 * <pre>
 * Input:
 *         1
 *        / \
 *       2   3
 * Output: 2
 * </pre>
 * Explanation: The longest consecutive path is [1, 2] or [2, 1].
 *
 * <pre>
 * Input:
 *         2
 *        / \
 *       1   3
 * Output: 3
 * </pre>
 * Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
 */
public class BinaryTreeLongestConsecutiveSequence2 {
    /**
     * Approach: Gather the longest consecutive sequence starting from a node (increasing) and longest sequence ending from a node (decreasing)
     * At each node check the results from children and see whether the current node can increase the previous increasing or decreasing sequence
     * <p>
     * Care must be taken to ensure current node can act as a middle element as well of the sequence
     */
    int result;

    public int longestConsecutive(TreeNode root) {
        recur(root);
        return result;
    }

    //pair of length of consecutive sequence beginning from node, length of consecutive sequence ending at node
    private Pair<Integer, Integer> recur(TreeNode root) {
        if (root == null) {
            return new Pair<>(0, 0);
        } else if (root.left == null && root.right == null) {
            result = Math.max(result, 1);
            return new Pair<>(1, 1);
        } else {
            Pair<Integer, Integer> left = recur(root.left);
            Pair<Integer, Integer> right = recur(root.right);
            int singlePathBeginning = 1, singlePathEnding = 1;
            if (root.left != null && root.val + 1 == root.left.val) { //try to extend results of left subtree
                result = Math.max(result, left.getKey() + 1);
                singlePathBeginning = Math.max(singlePathBeginning, left.getKey() + 1);
            } else if (root.left != null && root.val - 1 == root.left.val) {
                result = Math.max(result, left.getValue() + 1);
                singlePathEnding = Math.max(singlePathEnding, left.getValue() + 1);
            }
            if (root.right != null && root.val + 1 == root.right.val) { //same as above but for right subtree
                result = Math.max(result, right.getKey() + 1);
                singlePathBeginning = Math.max(singlePathBeginning, right.getKey() + 1);
            } else if (root.right != null && root.val - 1 == root.right.val) {
                singlePathEnding = Math.max(singlePathEnding, right.getValue() + 1);
                result = Math.max(result, right.getValue() + 1);
            }
            result = Math.max(result, singlePathBeginning + singlePathEnding - 1);
            //tricky part where current node acts as the middle element of longest increasing sequence and longest decreasing sequence
            return new Pair<>(singlePathBeginning, singlePathEnding); //return both of them because we don't know what sequence parent node needs
            //parent can either extend increasing sequence or decreasing sequence
        }
    }
}
