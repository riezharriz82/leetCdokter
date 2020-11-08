import common.TreeNode;

/**
 * https://leetcode.com/problems/distribute-coins-in-binary-tree/
 * <p>
 * Given the root of a binary tree with N nodes, each node in the tree has node.val coins, and there are N coins total.
 * <p>
 * In one move, we may choose two adjacent nodes and move one coin from one node to another.  (The move may be from parent to child, or from child to parent.)
 * <p>
 * Return the number of moves required to make every node have exactly one coin.
 * <p>
 * Input: [3,0,0]
 * Output: 2
 * Explanation: From the root of the tree, we move one coin to its left child, and one coin to its right child.
 * <p>
 * Input: [0,3,0]
 * Output: 3
 * Explanation: From the left child of the root, we move two coins to the root [taking two moves].  Then, we move one coin from the root of the tree to the right child.
 */
public class DistributeCoinsInBinaryTree {
    int totalMovesRequired;

    /**
     * Approach: Tricky question similar to {@link DeleteNodesAndReturnForest} {@link LongestUnivaluePath}
     * <p>
     * Happy to solve this question on my own, remember the basics, what information is needed from the child or from the parent?
     * Since here child can transfer coins to the parent, information is required from the child
     * <p>
     * If a node has x (>= 1) coins, it has a surplus of coins, end goal is 1 coin, so anyways that node has to transfer excess coins in (x-1) moves
     * Total moves done at that node is x-1
     * If a node has x < 1 coins, it needs 1 coin, so it need to perform 1 move.
     * <p>
     * Repeat the same process for all nodes with subtrees, consider the surplus/deficit of left/right subtree along with current node surplus/deficit
     */
    public int distributeCoins(TreeNode root) {
        recur(root);
        return totalMovesRequired;
    }

    private int recur(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftSurplus = recur(root.left);
            int rightSurplus = recur(root.right);
            int curNodeSurplus = root.val - 1;
            totalMovesRequired += Math.abs(leftSurplus + rightSurplus + curNodeSurplus); //take absolute value to handle deficit i.e. negative surplus
            return leftSurplus + rightSurplus + curNodeSurplus; //whatever is the surplus/deficit of entire subtree, return to parent
        }
    }
}
