import common.TreeNode;

/**
 * https://leetcode.com/problems/inorder-successor-in-bst/ Premium
 * <p>
 * Given the root of a binary search tree and a node p in it, return the in-order successor of that node in the BST.
 * If the given node has no in-order successor in the tree, return null.
 * <p>
 * The successor of a node p is the node with the smallest key greater than p.val.
 * <p>
 * Input: root = [2,1,3], p = 1
 * Output: 2
 * Explanation: 1's in-order successor node is 2. Note that both p and the return value is of TreeNode type.
 * <p>
 * Input: root = [5,3,6,2,4,null,null,1], p = 6
 * Output: null
 * Explanation: There is no in-order successor of the current node, so the answer is null.
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [1, 104].
 * -10^5 <= Node.val <= 10^5
 * All Nodes will have unique values.
 */
public class InorderSuccessorInBST {
    TreeNode inorderSuccessor;

    /**
     * Approach: Finding Inorder successor is finding a smallest node greater than target node.
     * So whenever we find a node greater than target, keep it as a candidate and recur left to find a even smaller node.
     * If you find a even smaller node, return it otherwise return the current candidate.
     * <p>
     * In case current node is smaller than target, need to go into right subtree to increase the value of root.
     * <p>
     * {@link DeleteNodeInBST} related problem
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        } else if (root.val > p.val) {
            TreeNode mayBeInorderSuccessor = inorderSuccessor(root.left, p);
            return mayBeInorderSuccessor != null ? mayBeInorderSuccessor : root;
        } else {
            return inorderSuccessor(root.right, p);
        }
    }

    /**
     * Approach: Recursion, Use global variable to keep track of inorder successor whenever we visit the left subtree.
     */
    public TreeNode inorderSuccessorGlobalVariable(TreeNode root, TreeNode p) {
        recur(root, p);
        return inorderSuccessor;
    }

    private void recur(TreeNode root, TreeNode target) {
        if (root != null) {
            if (root.val > target.val) {
                //root is greater than target, see if you can find a node smaller than root and greater than target
                inorderSuccessor = root;
                recur(root.left, target);
            } else {
                recur(root.right, target);
            }
        }
    }
}
