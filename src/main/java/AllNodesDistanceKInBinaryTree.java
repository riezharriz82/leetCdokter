import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
 * <p>
 * We are given a binary tree (with root node root), a target node, and an integer value K.
 * <p>
 * Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.
 * <p>
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
 * <p>
 * Output: [7,4,1]
 */
public class AllNodesDistanceKInBinaryTree {
    /**
     * Approach: Finding nodes that are at a distance K downwards is trivial.
     * Difficulty is to find nodes that are at a distance K upwards.
     * So parent needs to know whether the target node was at left subtree or at right subtree, because it needs to look up nodes accordingly
     * in the opposite subtree.
     * What will be the distance of the new nodes that needs to be looked up in the opposite subtree?
     * <p>
     * If the target node is at distance 2 from the current node and target distance is 6, we need to look for nodes present at distance 3 (0 based)
     * in the opposite subtree
     */
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> list = new ArrayList<>();
        TravelTillTarget(root, target, K, list);
        return list;
    }

    private int TravelTillTarget(TreeNode root, TreeNode target, int k, List<Integer> list) {
        if (root != null) {
            if (root == target) {
                TravelDown(target, k, list);
                return 0; //distance of the node from the target
            } else {
                int leftDistance = TravelTillTarget(root.left, target, k, list);
                if (leftDistance != -1) { //if the target was found in left subtree, we need to look for nodes in right subtree
                    if (1 + leftDistance == k) { // if the current node itself is at k distance from target
                        list.add(root.val);
                    } else {
                        TravelDown(root.right, k - 1 - (1 + leftDistance), list); // reduce the distance by 1 + existing distance
                    }
                    return 1 + leftDistance;
                }
                int rightDistance = TravelTillTarget(root.right, target, k, list);
                if (rightDistance != -1) { //if the target was found in right subtree, we need to look in left subtree
                    if (1 + rightDistance == k) {
                        list.add(root.val);
                    } else {
                        TravelDown(root.left, k - 1 - (1 + rightDistance), list);
                    }
                    return 1 + rightDistance;
                }
                return -1; // can't happen as the target node exists
            }
        } else {
            return -1;
        }
    }

    private void TravelDown(TreeNode target, int k, List<Integer> list) {
        if (target != null) {
            if (k == 0) {
                list.add(target.val);
                return;
            }
            TravelDown(target.left, k - 1, list);
            TravelDown(target.right, k - 1, list);
        }
    }
}
