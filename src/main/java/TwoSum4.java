import common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * https://leetcode.com/problems/two-sum-iv-input-is-a-bst/
 * <p>
 * Given the root of a Binary Search Tree and a target number k, return true if there exist two elements in the BST such that their sum is equal to the given target.
 * <p>
 * Input: root = [5,3,6,2,4,null,7], k = 9
 * Output: true
 * <p>
 * Input: root = [5,3,6,2,4,null,7], k = 28
 * Output: false
 * <p>
 * Input: root = [2,1,3], k = 4
 * Output: true
 * <p>
 * Input: root = [2,1,3], k = 1
 * Output: false
 * <p>
 * Input: root = [2,1,3], k = 3
 * Output: true
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [1, 104].
 * -10^4 <= Node.val <= 10^4
 * root is guaranteed to be a valid binary search tree.
 * -10^5 <= k <= 10^5
 */
public class TwoSum4 {
    /**
     * Approach: Iterative traversal using explicit stack. Need to instantiate two iterators, one in forward direction and another
     * in backward direction.
     * Time Complexity: O(n), Space Complexity: O(height)
     * <p>
     * {@link FlattenNestedListIteratorIterative} {@link BinaryTreeIterativeInorderTraversal} {@link TwoSumBSTs}
     */
    public boolean findTargetIterative(TreeNode root, int k) {
        BSTIterator leftIterator = new BSTIterator(root, DIRECTION.FORWARD);
        BSTIterator rightIterator = new BSTIterator(root, DIRECTION.BACKWARD);
        while (leftIterator.hasNext() && rightIterator.hasNext()) {
            Integer left = leftIterator.peek(); //we need peek() in order to avoid missing the values after calling next()
            Integer right = rightIterator.peek();
            if (left >= right) { //we have moved past the bounds
                return false;
            } else if (left + right == k) {
                return true;
            } else if (left + right < k) {
                leftIterator.next();
            } else {
                rightIterator.next();
            }
        }
        return false;
    }

    private static class BSTIterator implements Iterator<Integer> {
        DIRECTION direction;
        ArrayDeque<TreeNode> stack = new ArrayDeque<>(); //top of the stack represents the current element

        public BSTIterator(TreeNode root, DIRECTION direction) {
            this.direction = direction;
            while (root != null) {
                stack.push(root);
                root = (direction == DIRECTION.FORWARD) ? root.left : root.right;
            }
        }

        public Integer peek() {
            return stack.peek().val;
        }

        public Integer next() {
            if (hasNext()) {
                TreeNode top = stack.pop();
                int val = top.val;
                TreeNode root = (direction == DIRECTION.FORWARD) ? top.right : top.left;
                while (root != null) {
                    stack.push(root);
                    root = (direction == DIRECTION.FORWARD) ? root.left : root.right;
                }
                return val;
            } else {
                return -1;
            }
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }
    }

    private enum DIRECTION {
        FORWARD, BACKWARD
    }

    /**
     * Approach: Do an inorder traversal of BST and store all the values in an array.
     * Use 2 pointers to find two nodes whose sum equals k
     * <p>
     * Time Complexity: O(n) Space Complexity: O(n)
     */
    public boolean findTargetExtraSpace(TreeNode root, int k) {
        List<Integer> values = new ArrayList<>();
        recur(values, root);
        int left = 0, right = values.size() - 1;
        while (left < right) {
            int candidate = values.get(left) + values.get(right);
            if (candidate == k) {
                return true;
            } else if (candidate < k) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }

    private void recur(List<Integer> values, TreeNode root) {
        if (root != null) {
            recur(values, root.left);
            values.add(root.val);
            recur(values, root.right);
        }
    }
}
