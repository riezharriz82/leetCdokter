import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 * <p>
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
 * <p>
 * For example:
 * Given binary tree [3,9,20,null,null,15,7]
 * <pre>
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * </pre>
 * return its zigzag level order traversal as:
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 */
public class ZigZagTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, res, 0);
        return res;
    }

    private void dfs(TreeNode root, List<List<Integer>> res, int level) {
        if (root != null) {
            if (res.size() == level) { //new level found
                res.add(new LinkedList<>());
            }
            List<Integer> elementsInLevel = res.get(level);
            if (level % 2 == 1) {
                //push right element first
                elementsInLevel.add(0, root.val);
            } else {
                //push right element at the end
                elementsInLevel.add(root.val);
            }
            dfs(root.left, res, level + 1);
            dfs(root.right, res, level + 1);
        }
    }
}
