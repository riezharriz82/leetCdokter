import common.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * https://leetcode.com/problems/path-sum-iii/
 * You are given a binary tree in which each node contains an integer value.
 * Find the number of paths that sum to a given value.
 * <p>
 * The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
 * <p>
 * Example: root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 * <pre>
 *       10
 *      /  \
 *     5   -3
 *    / \    \
 *   3   2   11
 *  / \   \
 * 3  -2   1
 * </pre>
 * Return 3. The paths that sum to 8 are:
 * <p>
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3. -3 -> 11
 */
public class PathSum3 {
    int count = 0;

    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        return pathSumHelper(root, sum, 0, map);
    }

    /**
     * {@link SubarraySumEqualsK} Use Prefix Sum to find no of subarray that equals a target sum
     * <p>
     * Thing to understand is that path in a tree can act like a subarray
     */
    private int pathSumHelper(TreeNode root, int targetSum, int currentSum, HashMap<Integer, Integer> integers) {
        if (root == null) {
            return 0;
        }
        int count = 0;
        currentSum += root.val;
        if (integers.containsKey(currentSum - targetSum)) {
            count += integers.get(currentSum - targetSum); //this is important e.g 0 -> 0 -> 5 (target 5)
        }
        integers.put(currentSum, integers.getOrDefault(currentSum, 0) + 1); //add current nodes contribution
        count += pathSumHelper(root.left, targetSum, currentSum, integers);
        count += pathSumHelper(root.right, targetSum, currentSum, integers);
        integers.put(currentSum, integers.get(currentSum) - 1); //remove contribution of current node during backtrack
        integers.remove(currentSum, 0); //cool trick to avoid if else check during removal
        return count;
    }

    /**
     * Approach: Albeit a slower solution but still works, extend all paths from children and return the extended path to the parent
     * also start a new path before returning, this ensures that we consider intermediate paths, not just the path starting from leaf.
     */
    public int pathSumAlternate(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        pathSumHelper(root, sum);
        return count;
    }

    private List<Integer> pathSumHelper(TreeNode root, int sum) {
        if (root == null) {
            return new ArrayList<>();
        } else {
            List<Integer> leftPath = pathSumHelper(root.left, sum);
            List<Integer> rightPath = pathSumHelper(root.right, sum);
            leftPath.addAll(rightPath);
            if (root.val == sum) {
                count++;
            }
            for (int i = 0; i < leftPath.size(); i++) {
                int newPathSum = leftPath.get(i) + root.val; //extend the path from the children
                if (newPathSum == sum) {
                    count++;
                }
                leftPath.set(i, newPathSum);
            }
            leftPath.add(root.val); //the tricky part, start a new path from the current node
            return leftPath;
        }
    }
}
