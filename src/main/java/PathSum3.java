import common.TreeNode;

import java.util.HashMap;

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
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        return pathSumHelper(root, sum, 0, new HashMap<>());
    }

    /**
     * So the idea is similar as Two sum, using HashMap to store ( key : the prefix sum, value : how many ways get to this prefix sum),
     * and whenever reach a node, we check if prefix sum - target exists in hashmap or not, if it does, we added up the ways of prefix sum - target into res.
     */
    private int pathSumHelper(TreeNode root, int targetSum, int currentSum, HashMap<Integer, Integer> integers) {
        if (root == null) {
            return 0;
        }
        int count = 0;
        currentSum += root.val;
        if (currentSum == targetSum) {
            count++;
        }
        if (integers.containsKey(currentSum - targetSum)) {
            count += integers.get(currentSum - targetSum); //this is important e.g 0 -> 0 -> 5 (target 5)
        }

        integers.put(currentSum, integers.getOrDefault(currentSum, 0) + 1);
        count += pathSumHelper(root.left, targetSum, currentSum, integers);
        count += pathSumHelper(root.right, targetSum, currentSum, integers);
        if (integers.get(currentSum) == 1) {
            integers.remove(currentSum);
        } else {
            integers.put(currentSum, integers.get(currentSum) - 1);
        }
        return count;
    }
}
