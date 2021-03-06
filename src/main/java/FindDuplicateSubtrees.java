import common.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/find-duplicate-subtrees/
 * <p>
 * Given the root of a binary tree, return all duplicate subtrees.
 * <p>
 * For each kind of duplicate subtrees, you only need to return the root node of any one of them.
 * <p>
 * Two trees are duplicate if they have the same structure with the same node values.
 * <p>
 * Input: root = [1,2,3,4,null,2,4,null,null,4]
 * Output: [[2,4],[4]]
 */
public class FindDuplicateSubtrees {
    /**
     * Approach: Create unique hashes of each subtree as you iterate the nodes in the tree
     * Hash can be generated by preorder traversal
     * Since you have to return the root node of any of the duplicate subtree, you can simply overwrite the root of the previous hash found
     * No need to worry about returning the first root node
     * <p>
     * {@link DeleteNodesAndReturnForest} {@link DistributeCoinsInBinaryTree} {@link SerializeAndDeserializeBinaryTree}
     */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, Integer> count = new HashMap<>(); //count of occurrences of a hash of subtree
        Map<String, TreeNode> parent = new HashMap<>();
        List<TreeNode> res = new ArrayList<>();
        DFS(root, count, parent);
        for (Map.Entry<String, Integer> entry : count.entrySet()) {
            if (entry.getValue() > 1) { //if duplicate, find the root node of the hash and add it to result
                res.add(parent.get(entry.getKey()));
            }
        }
        return res;
    }

    private String DFS(TreeNode root, Map<String, Integer> count, Map<String, TreeNode> parent) {
        if (root == null) {
            return "N";
        } else {
            String left = DFS(root.left, count, parent);
            String right = DFS(root.right, count, parent);
            String currentSubtree = root.val + "," + left + "," + right;
            //use preorder traversal, if inorder traversal is used you have to use brackets to distinguish between left and right subtree
            count.put(currentSubtree, count.getOrDefault(currentSubtree, 0) + 1);
            parent.putIfAbsent(currentSubtree, root); //can also overwrite it, because we have to return any of the duplicate root node
            return currentSubtree;
        }
    }
}
