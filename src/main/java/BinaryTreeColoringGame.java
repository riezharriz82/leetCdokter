import common.TreeNode;

/**
 * https://leetcode.com/problems/binary-tree-coloring-game/
 * <p>
 * Two players play a turn based game on a binary tree.  We are given the root of this binary tree, and the number of nodes n in the tree.
 * n is odd, and each node has a distinct value from 1 to n.
 * <p>
 * Initially, the first player names a value x with 1 <= x <= n, and the second player names a value y with 1 <= y <= n and y != x.
 * The first player colors the node with value x red, and the second player colors the node with value y blue.
 * <p>
 * Then, the players take turns starting with the first player.  In each turn, that player chooses a node of their color (red if player 1, blue if player 2) a
 * nd colors an uncolored neighbor of the chosen node (either the left child, right child, or parent of the chosen node.)
 * <p>
 * If (and only if) a player cannot choose such a node in this way, they must pass their turn.
 * If both players pass their turn, the game ends, and the winner is the player that colored more nodes.
 * <p>
 * You are the second player.  If it is possible to choose such a y to ensure you win the game, return true.  If it is not possible, return false.
 * <p>
 * Input: root = [1,2,3,4,5,6,7,8,9,10,11], n = 11, x = 3
 * Output: true
 * Explanation: The second player can choose the node with value 2.
 */
public class BinaryTreeColoringGame {
    /**
     * Approach: Greedy, best way for y to pick a node would be to pick a node that is adjacent to x. This way the entire subtree
     * would be blocked and x can't pick any nodes from that subtree. There are only three nodes to be picked as y, left child of x,
     * right child of x or the parent of x.
     * <p>
     * {@link BinaryTreeCameras} related tree coloring problem
     */
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        TreeNode xNode = recur(root, x); //find the node that is represented by x as the nodes are distinct
        int leftChildren = count(xNode.left);
        int rightChildren = count(xNode.right);
        int parentsChildren = n - leftChildren - rightChildren - 1;
        //there are three best options to be colored by y, check whether the size of nodes under any of those subtrees > half of nodes
        //since n is always odd, no possibility of ties
        int largestNodesYCanColor = Math.max(leftChildren, Math.max(rightChildren, parentsChildren));
        return largestNodesYCanColor > n / 2;
    }

    private int count(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return 1 + count(root.left) + count(root.right);
        }
    }

    private TreeNode recur(TreeNode root, int x) {
        if (root == null) {
            return null;
        } else {
            if (root.val == x) {
                return root;
            }
            TreeNode left = recur(root.left, x);
            if (left != null) {
                return left;
            }
            return recur(root.right, x);
        }
    }
}
