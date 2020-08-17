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
    int result = 0;

    public int sumEvenGrandparentSimplified(TreeNode root) {
        return DFS(root, 1, 1); //init it will any odd value
    }

    //Approach: Let nodes be aware of their grandparent so they can add the current node value to the result
    // Update parent and grandparent for next iteration
    // Top Down approach
    private int DFS(TreeNode root, int parent, int grandParent) {
        if (root == null) {
            return 0;
        } else {
            int left = DFS(root.left, root.val, parent);
            int right = DFS(root.right, root.val, parent);
            if (grandParent % 2 == 0) {
                return left + right + root.val;
            } else {
                return left + right;
            }
        }
    }

    public int sumEvenGrandparent(TreeNode root) {
        DFS(root);
        return result;
    }

    //Bottom up approach
    //Each node return their child and grandchild sum to the parent (Special case for leaf nodes)
    //Parent mark the child sum of their child as their grandchild sum and update the child sum too
    private Result DFS(TreeNode root) {
        if (root == null) {
            return new Result();
        } else {
            if (root.left == null && root.right == null) {
                Result result = new Result();
                result.leaf = root.val;
                return result;
            } else {
                Result left = DFS(root.left);
                Result right = DFS(root.right);
                int childSum = 0;
                childSum += left.leaf;
                childSum += right.leaf;
                childSum += left.child;
                childSum += right.child;
                if (root.val % 2 == 0) {
                    result += left.grandChild;
                    result += right.grandChild;
                }
                Result result = new Result();
                //tricky: Update the grandchild and child as we return to the parent
                result.grandChild = childSum;
                result.child = root.val;
                return result;
            }
        }
    }

    private static class Result {
        public int child;
        public int grandChild;
        public int leaf;
    }
}
