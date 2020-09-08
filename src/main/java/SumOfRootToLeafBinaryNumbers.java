import common.TreeNode;

/**
 * https://leetcode.com/problems/sum-of-root-to-leaf-binary-numbers/
 * <p>
 * Given a binary tree, each node has value 0 or 1.  Each root-to-leaf path represents a binary number starting with the most significant bit.
 * For example, if the path is 0 -> 1 -> 1 -> 0 -> 1, then this could represent 01101 in binary, which is 13.
 * <p>
 * For all leaves in the tree, consider the numbers represented by the path from the root to that leaf.
 * <p>
 * Return the sum of these numbers.
 * <p>
 * Input: [1,0,1,0,1,0,1]
 * Output: 22
 * Explanation: (100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
 */
public class SumOfRootToLeafBinaryNumbers {
    public int sumRootToLeaf(TreeNode root) {
        return DFS(root, 0);
    }

    /**
     * The tricky part was to convert the running path into base 2.
     * In my initial approach, I stored the path in a list and at the leaf, used to iterate the entire list and convert into base 2
     * It can be simplified by storing the path in base 2 itself.
     */
    private int DFS(TreeNode root, int base2CurSum) {
        if (root == null) {
            return 0;
        } else {
            if (root.left == null && root.right == null) {
                return 2 * base2CurSum + root.val;
            } else {
                return DFS(root.left, 2 * base2CurSum + root.val) + DFS(root.right, 2 * base2CurSum + root.val);
            }
        }
    }
}
