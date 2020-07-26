import common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/number-of-good-leaf-nodes-pairs/
 * <p>
 * Given the root of a binary tree and an integer distance. A pair of two different leaf nodes of a binary tree is said to be good
 * if the length of the shortest path between them is less than or equal to distance.
 * <p>
 * Return the number of good leaf node pairs in the tree.
 * <p>
 * Input: root = [1,2,3,4,5,6,7], distance = 3
 * Output: 2
 * <p>
 * Explanation: The good pairs are [4,5] and [6,7] with shortest path = 2.
 * The pair [4,6] is not good because the length of their shortest path between them is 4.
 */
public class NumberOfLeafPairsLyingWithinADistance {
    int res = 0;

    public int countPairs(TreeNode root, int distance) {
        DFS(root, distance);
        return res;
    }

    /**
     * Approach: Do a postorder traversal, each node will return a map of {distance to leaf node, no of leaves} to parent node
     * Parent node will merge left subarray and right subarray map after incrementing the distance by 1 and then return to parent node
     * Before merging we can traverse left and right subarray result map to find if any two leaves whose distance <= target distance
     * If present, increment the result.
     * <p>
     * Visualize this with below tree and target of 6
     * <pre>
     *               1
     *          2        3
     *        11       4   7
     *                5   8 9
     *               6 3
     * </pre>
     * {@link NumberOfNodesInTheSubTreeWithSameLabel} is very similar to this type of problem
     */
    private Map<Integer, Integer> DFS(TreeNode root, int distance) {
        if (root != null) {
            if (root.left == null && root.right == null) {
                Map<Integer, Integer> map = new HashMap<>();
                map.put(1, 1); //key is distance, value is no of leaf nodes present at that distance
                return map;
            }
            Map<Integer, Integer> left = DFS(root.left, distance);
            Map<Integer, Integer> right = DFS(root.right, distance);
            Map<Integer, Integer> result = new HashMap<>();

            if (!left.isEmpty() && !right.isEmpty()) {
                //if leaves are present on both the subarray, check count of leaves pair whose distance <= target distance
                for (Map.Entry<Integer, Integer> entry : left.entrySet()) {
                    int leftDistance = entry.getKey();
                    int leftLeavesCount = entry.getValue();
                    for (Map.Entry<Integer, Integer> rightEntry : right.entrySet()) {
                        int rightDistance = rightEntry.getKey();
                        int rightLeavesCount = rightEntry.getValue();
                        if (leftDistance + rightDistance <= distance) {
                            res += (leftLeavesCount * rightLeavesCount);
                        }
                    }
                }
            }
            //increment the leaf nodes distance by 1 and update the result
            left.forEach((k, v) -> result.put(k + 1, v));
            right.forEach((k, v) -> result.put(k + 1, result.getOrDefault(k + 1, 0) + v));
            return result;
        }
        return new HashMap<>();
    }
}
