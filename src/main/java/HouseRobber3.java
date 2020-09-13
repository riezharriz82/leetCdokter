import common.TreeNode;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/house-robber-iii/
 * <p>
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root."
 * Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree".
 * It will automatically contact the police if two directly-linked houses were broken into on the same night.
 * <p>
 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
 * <p>
 * Input: [3,2,3,null,3,null,1]
 * <pre>
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 * </pre>
 * Output: 7
 * Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 */
public class HouseRobber3 {
    /**
     * Approach: Every node has two options, it can be robbed or can't be robbed. Return the max of them.
     * If a node can be robbed, the child can't be robbed.
     * If a node can't be robbed, there are two options for it's child -- child can be robbed or child can't be robbed. Return the max of them.
     * I missed the option that child can't be robbed during my initial submission
     * Example of where child should not be robbed
     * <pre>
     *      4
     *     1
     *    2
     *   3
     * Result is 7: 4 + 3
     * </pre>
     */
    public int rob(TreeNode root) {
        Map<Pair<TreeNode, Boolean>, Integer> map = new HashMap<>(); //memoize to avoid recomputations of subproblems
        return Math.max(DFS(root, true, map), DFS(root, false, map));
    }

    /**
     * We can avoid the boolean flag if we directly recurse to the grandchildren i.e recur directly to the next nodes that can be robbed
     * https://leetcode.com/problems/house-robber-iii/discuss/79330/Step-by-step-tackling-of-the-problem
     */
    private int DFS(TreeNode root, boolean canRob, Map<Pair<TreeNode, Boolean>, Integer> map) {
        if (root == null) {
            return 0;
        } else if (map.containsKey(new Pair<>(root, canRob))) {
            return map.get(new Pair<>(root, canRob));
        } else {
            int robRoot = 0, cantRobRoot = 0;
            if (canRob) {
                //child can't be robbed
                robRoot = root.val + DFS(root.left, false, map) + DFS(root.right, false, map);
            } else {
                //return the max after robbing or not robbing the child
                int left = Math.max(DFS(root.left, true, map), DFS(root.left, false, map));
                int right = Math.max(DFS(root.right, true, map), DFS(root.right, false, map));
                cantRobRoot = left + right;
            }
            int result = Math.max(robRoot, cantRobRoot);
            map.put(new Pair<>(root, canRob), result);
            return result;
        }
    }
}
