import common.TreeNode;

/**
 * https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/
 * <p>
 * Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly two or zero sub-node.
 * If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes.
 * More formally, the property root.val = min(root.left.val, root.right.val) always holds.
 * <p>
 * Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.
 * <p>
 * If no such second minimum value exists, output -1 instead.
 * <p>
 * Input: root = [2,2,5,null,null,5,7]
 * Output: 5
 * Explanation: The smallest value is 2, the second smallest value is 5.
 * <p>
 * Input: root = [2,2,2]
 * Output: -1
 * Explanation: The smallest value is 2, but there isn't any second smallest value.
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [1, 25].
 * 1 <= Node.val <= 231 - 1
 * root.val == min(root.left.val, root.right.val) for each internal node of the tree.
 */
public class SecondMinimumNodeInABinaryTree {
    Integer secondMinimum;
    int minimum;

    /**
     * Approach: Tricky, Divide and conquer.
     * <p>
     * For finding kth minimum node in a tournament tree
     * 1. Use quick select by storing all leaf nodes.
     * <p>
     * For finding 3rd minimum node in a tournament tree without storing all the leaf nodes
     * Notice that the second minimum node would be the smallest among the nodes that fought with the smallest node (root)
     * That node is not necessarily the node in the second level but can be any node that first node fought with. So we just keep track of
     * all element that the root node fought with and return the minimum
     * <p>
     * This induction can be leveraged to find the 3rd minimum as well by keeping track of the nodes that the second minimum fought with as well.
     * Answer would be the minimum of those two sets.
     * <p>
     * This question was asked to me in HotStar DSA interview round. Unfortunately I didn't even mention the quick select algorithm and just
     * mentioned the PriorityQueue algorithm
     * <p>
     * References:
     * Tournament Sort
     * For Visualization refer: https://www.youtube.com/watch?v=sYXBqvkIduQ
     * https://malkit.blogspot.com/2012/07/finding-kth-minimum-partial-ordering.html
     */
    public int findSecondMinimumValue(TreeNode root) {
        minimum = root.val;
        recur(root);
        return secondMinimum == null ? -1 : secondMinimum;
    }

    private void recur(TreeNode root) {
        if (root != null) {
            if (root.val == minimum) {
                recur(root.left);
                recur(root.right);
            } else if (secondMinimum == null) { //this is to handle cases where root.val == Integer.MAX_VALUE
                secondMinimum = root.val;
            } else if (root.val < secondMinimum) { //pruning, no need to further recurse down the tree as the root node of this subtree
                //is guaranteed to be the minimum of all nodes
                secondMinimum = root.val;
            } else if (root.val > secondMinimum) { //pruning, no need to further recurse down the tree if we already have a better solution
                return;
            }
        }
    }
}
