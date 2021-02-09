import common.TreeNode;

/**
 * https://leetcode.com/problems/convert-bst-to-greater-tree/
 * https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/
 * <p>
 * Given the root of a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to
 * the original key plus sum of all keys greater than the original key in BST.
 * <p>
 * As a reminder, a binary search tree is a tree that satisfies these constraints:
 * <p>
 * Input: root = [4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
 * Output: [30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
 * <p>
 * Input: root = [0,null,1]
 * Output: [1,null,1]
 * <p>
 * Input: root = [1,0,2]
 * Output: [3,3,2]
 * <p>
 * Input: root = [3,2,4,1]
 * Output: [7,9,4,10]
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [0, 104].
 * -104 <= Node.val <= 104
 * All the values in the tree are unique.
 * root is guaranteed to be a valid binary search tree.
 */
public class ConvertBSTToGreaterTree {
    /**
     * Approach: Reverse inorder traversal, get subtree sum from right child and pass that info to left child to increment it's value
     * <p>
     * Remember that in tree question, we have to either pass information to the child or get information back from the child
     * Here we have to do both, pass the greater nodes sum from the parent to child and return the subtree sum back from the child
     * <p>
     * This was asked to me in my first Amazon interview, I couldn't figure the reverse inorder traversal, have to get a hint.
     * Got rejected :(
     * <p>
     * {@link UniqueBST2} {@link DistributeCoinsInBinaryTree} {@link DeleteNodesAndReturnForest}
     */
    public TreeNode convertBST(TreeNode root) {
        recur(root, 0);
        return root;
    }

    private int recur(TreeNode root, int greaterSum) {
        if (root == null) {
            return 0;
        } else {
            int right = recur(root.right, greaterSum); //important to first iterate the right subtree to get the sum of greater nodes
            int left = recur(root.left, greaterSum + root.val + right); //pass the right subtree + current root val to left subtree
            int rootVal = root.val;
            root.val += (greaterSum + right); //increment current root by the greater sum received from it's parent and the right subtree
            return left + right + rootVal; //very important to return the current subtree sum back to the parent, so that this information is
            //passed back correctly as correct right or left subtree sum, was a bit confused in the return value
            //it can be simplified if we use a global variable and store the greater sum found so far
        }
    }
}
