import common.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/flip-binary-tree-to-match-preorder-traversal/
 * <p>
 * You are given the root of a binary tree with n nodes, where each node is uniquely assigned a value from 1 to n.
 * You are also given a sequence of n values voyage, which is the desired pre-order traversal of the binary tree.
 * Any node in the binary tree can be flipped by swapping its left and right subtrees.
 * <p>
 * Flip the smallest number of nodes so that the pre-order traversal of the tree matches voyage.
 * <p>
 * Return a list of the values of all flipped nodes. You may return the answer in any order.
 * If it is impossible to flip the nodes in the tree to make the pre-order traversal match voyage, return the list [-1].
 * <p>
 * Input: root = [1,2], voyage = [2,1]
 * Output: [-1]
 * Explanation: It is impossible to flip the nodes such that the pre-order traversal matches voyage.
 * <p>
 * Input: root = [1,2,3], voyage = [1,3,2]
 * Output: [1]
 * Explanation: Flipping node 1 swaps nodes 2 and 3, so the pre-order traversal matches voyage.
 * <p>
 * Input: root = [1,2,3], voyage = [1,2,3]
 * Output: []
 * Explanation: The tree's pre-order traversal already matches voyage, so no nodes need to be flipped.
 * <p>
 * Constraints:
 * The number of nodes in the tree is n.
 * n == voyage.length
 * 1 <= n <= 100
 * 1 <= Node.val, voyage[i] <= n
 * All the values in the tree are unique.
 * All the values in voyage are unique.
 */
public class FipBinaryTreeToMatchPreorderTraversal {
    int index;
    boolean isInvalid;

    /**
     * Approach: Greedy, greedily iterate the tree and check whether the current index in the provided voyage[] matches the val.
     * If no, then it's an invalid traversal.
     * If yes, check the left node. If it's non null and the next value in voyage[] does not match with it, we need to swap it.
     * There is no other way.
     * After swapping once, proceed with the recursion as usual.
     * <p>
     * Need to take a global index while converting the preorder traversal to a tree. Lesson learnt from Joveo interview experience
     * <p>
     * This problem was a bit tough, but glad to solve it on my own.
     * <p>
     * {@link DistributeCoinsInBinaryTree} {@link SerializeAndDeserializeBinaryTree} {@link Joveo.txt}
     */
    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        List<Integer> result = new ArrayList<>();
        recur(root, voyage, result);
        if (isInvalid) {
            return Arrays.asList(-1);
        } else {
            return result;
        }
    }

    private void recur(TreeNode root, int[] voyage, List<Integer> result) {
        if (root != null) {
            if (root.val != voyage[index]) {
                isInvalid = true;
                return;
            }
            index++;
            //check if the next node in preorder traversal matches the expected value, if not, swap it.
            //root.left != null ensures that we don't perform unnecessary swaps.
            //Swap needs to be handled by the parent node, hence the check at the parent
            if (root.left != null && voyage[index] != root.left.val) {
                result.add(root.val);
                TreeNode temp = root.left;
                root.left = root.right;
                root.right = temp;
            }
            recur(root.left, voyage, result);
            recur(root.right, voyage, result);
        }
    }
}
