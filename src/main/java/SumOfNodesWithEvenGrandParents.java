import common.TreeNode;

/**
 * https://leetcode.com/problems/sum-of-nodes-with-even-valued-grandparent/
 * <p>
 * Given a binary tree, return the sum of values of nodes with even-valued grandparent.  (A grandparent of a node is the parent of its parent, if it exists.)
 * <p>
 * If there are no nodes with an even-valued grandparent, return 0.
 * <p>
 * Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
 * Output: 18
 */
public class SumOfNodesWithEvenGrandParents {
    public int sumEvenGrandparentSimplified(TreeNode root) {
        return DFS(root, 1, 1); //init it will any odd value
    }

    /**
     * Approach: Let nodes be aware of their grandparent so they can add the current node value to the result
     * Update parent and grandparent for next iteration
     * Top Down approach
     */
    private int DFS(TreeNode root, int parent, int grandParent) {
        if (root == null) {
            return 0;
        } else {
            int left = DFS(root.left, root.val, parent); //make root as the new parent of the children and current parent as the new grandparent
            int right = DFS(root.right, root.val, parent);
            if (grandParent % 2 == 0) {
                return left + right + root.val;
            } else {
                return left + right;
            }
        }
    }
}
