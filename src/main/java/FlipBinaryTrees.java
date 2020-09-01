import common.TreeNode;

/**
 * https://leetcode.com/problems/flip-equivalent-binary-trees/
 * <p>
 * For a binary tree T, we can define a flip operation as follows: choose any node, and swap the left and right child subtrees.
 * <p>
 * A binary tree X is flip equivalent to a binary tree Y if and only if we can make X equal to Y after some number of flip operations.
 * <p>
 * Given the roots of two binary trees root1 and root2, return true if the two trees are flip equivelent or false otherwise.
 * <pre>
 *       1
 *    2    3
 *  4  5  6  7
 *
 *       1
 *    3     2
 *  6  7  5   4
 *  </pre>
 * Input: root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,null,null,null,null,8,7]
 * Output: true
 * Explanation: We flipped at nodes with values 1, 3, and 5.
 */
public class FlipBinaryTrees {
    /**
     * {@link SymmetricTree} for similar problem
     * At every non null root, there are two ways of comparing the children
     * Either they are not flipped i.e compare left child of one root with left child of another root
     * They are flipped i.e. compare left child of one root with right child of another root
     * <p>
     * During implementation I got confused and went on to darker side but still I managed to solve it on my own nonetheless
     */
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 == null || root2 == null) {
            return false;
        } else {
            return root1.val == root2.val &&
                    ((flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right))  //not flipped
                            ||
                            (flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left))); //flipped
        }
    }
}
