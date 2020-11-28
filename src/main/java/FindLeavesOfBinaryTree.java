import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/find-leaves-of-binary-tree/
 * <p>
 * Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.
 * <p>
 * Input: [1,2,3,4,5]
 * <pre>
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 *
 * Output: [[4,5,3],[2],[1]]
 *
 * Explanation:
 * 1. Removing the leaves [4,5,3] would result in this tree:
 *           1
 *          /
 *         2
 * 2. Now removing the leaf [2] would result in this tree:
 *           1
 * 3. Now removing the leaf [1] would result in the empty tree:
 *           []
 * [[3,5,4],[2],[1]], [[3,4,5],[2],[1]], etc, are also consider correct answers since per each level it doesn't matter the order on which elements are returned.
 * </pre>
 */
public class FindLeavesOfBinaryTree {
    /**
     * Approach: Calculate the distance of each node from the leaf nodes,
     * The maximum distance from left/right leaf, would be the index the current node should be added to in the result list
     * In my initial approach, I did a two pass solution, first pass to find the max height of the tree
     * That would give the size of the result list
     * But it can be computed on the fly as we iterate through the tree
     * <p>
     * {@link MinHeightTree} {@link RightSideViewOfBinaryTree} related problems
     */
    public List<List<Integer>> findLeaves(TreeNode root) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        DFS(root, result);
        return result;
    }

    private int DFS(TreeNode root, ArrayList<List<Integer>> result) {
        if (root == null) {
            return 0;
        } else {
            int leftDistanceFromLeaf = DFS(root.left, result);
            int rightDistanceFromLeaf = DFS(root.right, result);
            int maxDistanceFromLeaf = Math.max(leftDistanceFromLeaf, rightDistanceFromLeaf);
            if (result.size() < maxDistanceFromLeaf + 1) { //computing the result list on the fly
                result.add(new ArrayList<>());
            }
            result.get(maxDistanceFromLeaf).add(root.val); //add current node to appropriate bucket
            return maxDistanceFromLeaf + 1;
        }
    }
}
