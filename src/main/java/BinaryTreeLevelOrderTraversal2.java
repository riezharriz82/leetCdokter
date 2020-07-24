import common.TreeNode;

import java.util.*;

/**
 * https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
 * <p>
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
 * <p>
 * For example:
 * <pre>
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its bottom-up level order traversal as:
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 * </pre>
 */
public class BinaryTreeLevelOrderTraversal2 {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        ArrayDeque<List<Integer>> queue = doBFS(root);
        //pop the stack and add to the result list
        while (!queue.isEmpty()) {
            List<Integer> top = queue.pop();
            res.add(top);
        }
        return res;
    }

    private ArrayDeque<List<Integer>> doBFS(TreeNode root) {
        ArrayDeque<List<Integer>> stack = new ArrayDeque<>(); //array dequeue can act as both as stack and a queue
        //if needed as stack use push/pop methods
        //if needed as queue use add/remove methods
        Queue<TreeNode> queue = new LinkedList<>(); //queue used for bfs traversal
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) { //process all the levels at once
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            //one level is done, add to stack
            stack.push(level);
        }
        return stack;
    }
}
