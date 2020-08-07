import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/path-sum-ii/
 * <p>
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 * <p>
 * Given the below binary tree and sum = 22,
 * <pre>
 *       5
 *      / \
 *     4   8
 *    /   / \
 *   11  13  4
 *  /  \    / \
 * 7    2  5   1
 *
 * Return:
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 * </pre>
 */
public class PathSum2 {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        DFS(root, sum, 0, result, new ArrayList<Integer>());
        return result;
    }

    //keep adding current node to the current path and update the current sum
    //if the current node is a leaf node and current sum equals target sum add current path to result
    //dont forget to remove node from the current path once a node is finished processing
    private void DFS(TreeNode root, int targetSum, int currentSum, List<List<Integer>> result, ArrayList<Integer> currentPath) {
        if (root != null) {
            currentSum += root.val;
            currentPath.add(root.val);
            if (root.left == null && root.right == null) {
                if (currentSum == targetSum) {
                    result.add(new ArrayList<>(currentPath));
                }
            } else {
                DFS(root.left, targetSum, currentSum, result, currentPath);
                DFS(root.right, targetSum, currentSum, result, currentPath);
            }
            currentPath.remove(currentPath.size() - 1);
        }
    }
}
