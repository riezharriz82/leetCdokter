import common.TreeNode;

import java.util.ArrayDeque;

/**
 * https://leetcode.com/problems/maximum-binary-tree/
 * <p>
 * Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:
 * <p>
 * The root is the maximum number in the array.
 * The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
 * The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
 * Construct the maximum tree by the given array and output the root node of this tree.
 * <p>
 * Example 1:
 * Input: [3,2,1,6,0,5]
 * Output: return the tree root node representing the following tree:
 * <pre>
 *       6
 *     /   \
 *    3     5
 *     \    /
 *      2  0
 *       \
 *        1
 * </pre>
 */
public class ConstructMaximumBinaryTree {
    /**
     * Similar to finding next greater element {@link NextGreaterElement}
     * Monotonic stack is helpful in problems dealing with finding large element
     * Consider a tree already built from index 0 to i, for index i + 1, where will you put this node?
     */
    public TreeNode constructMaximumBinaryTreeInLinearTime(int[] nums) {
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        for (int num : nums) {
            TreeNode current = new TreeNode(num);
            TreeNode lastPopped = null;
            while (!stack.isEmpty() && stack.peek().val < num) {
                lastPopped = stack.pop();
            }
            //last popped node is smaller than current node and occurs on the left part, so update the left subtree
            current.left = lastPopped;
            if (!stack.isEmpty()) {
                //top of the stack is now greater than current element
                //current element lies on right of top node, so update the right subtree of top
                stack.peek().right = current;
            }
            stack.push(current);
        }
        return stack.peekLast();
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return buildTree(nums, 0, nums.length - 1);
    }

    //My initial n^2 solution
    private TreeNode buildTree(int[] nums, int low, int high) {
        if (low > high) {
            return null;
        }
        if (low == high) {
            return new TreeNode(nums[low]);
        }
        int maxIndex = findMaxIndex(nums, low, high);
        TreeNode root = new TreeNode(nums[maxIndex]);
        root.left = buildTree(nums, low, maxIndex - 1);
        root.right = buildTree(nums, maxIndex + 1, high);
        return root;
    }

    private int findMaxIndex(int[] nums, int low, int high) {
        int maxIndex = low;
        while (low <= high && low < nums.length) {
            if (nums[low] > nums[maxIndex]) {
                maxIndex = low;
            }
            low++;
        }
        return maxIndex;
    }
}
