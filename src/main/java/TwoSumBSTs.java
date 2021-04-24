import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Iterator;

/**
 * https://leetcode.com/problems/two-sum-bsts/ Premium
 * <p>
 * Given the roots of two binary search trees, root1 and root2, return true if and only if there is a node in the first tree and a node in the second tree
 * whose values sum up to a given integer target.
 * <p>
 * Input: root1 = [2,1,4], root2 = [1,0,3], target = 5
 * Output: true
 * Explanation: 2 and 3 sum up to 5.
 * <p>
 * Input: root1 = [0,-10,10], root2 = [5,1,7,0,2], target = 18
 * Output: false
 * <p>
 * Constraints:
 * The number of nodes in each tree is in the range [1, 5000].
 * -10^9 <= Node.val, target <= 10^9
 */
public class TwoSumBSTs {
    /**
     * Approach: Iterative traversal of BST, Use inorder traversal to iterate first tree and reverse inorder traversal to iterate second tree.
     * <p>
     * If you want to solve it similar to {@link BinaryTreeIterativeInorderTraversal} then perform pop() only from the respective stack.
     * i.e. if left + right < k, then perform tree1.pop() and set tree1 = tree1.left;
     * <p>
     * Time Complexity: O(n) Space Complexity: O(height)
     * <p>
     * {@link TwoSum4} related problem
     */
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        BSTIterator tree1 = new BSTIterator(root1, DIRECTION.FORWARD);
        BSTIterator tree2 = new BSTIterator(root2, DIRECTION.BACKWARD);
        while (tree1.hasNext() && tree2.hasNext()) {
            int val1 = tree1.peek(), val2 = tree2.peek();
            if (val1 + val2 == target) {
                return true;
            } else if (val1 + val2 < target) {
                tree1.next();
            } else {
                tree2.next();
            }
        }
        return false;
    }

    private enum DIRECTION {
        FORWARD, BACKWARD
    }

    private static class BSTIterator implements Iterator<Integer> {
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        DIRECTION direction;

        public BSTIterator(TreeNode root, DIRECTION direction) {
            this.direction = direction;
            while (root != null) {
                stack.push(root);
                root = (direction == DIRECTION.FORWARD) ? root.left : root.right;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Integer next() {
            if (hasNext()) {
                TreeNode top = stack.pop();
                int val = top.val;
                TreeNode root = (direction == DIRECTION.FORWARD) ? top.right : top.left;
                while (root != null) {
                    stack.push(root);
                    root = (direction == DIRECTION.FORWARD) ? top.left : top.right;
                }
                return val;
            } else {
                return -1;
            }
        }

        public Integer peek() {
            return stack.peek().val;
        }
    }
}
