import common.TreeNode;

/**
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 * <p>
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 * <p>
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 * <p>
 * Given the sorted array: [-10,-3,0,5,9],
 * <p>
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 * <pre>
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *  </pre>
 */
public class SortedArrayToBST {
    public TreeNode sortedArrayToBST(int[] nums) {
        return buildTree(nums, 0, nums.length - 1);
    }

    //Fix middle element as the root of the BST to keep it height balanced
    private TreeNode buildTree(int[] nums, int low, int high) {
        if (low > high) {
            return null;
        }
        if (low == high) {
            return new TreeNode(nums[low]);
        }
        int mid = low + (high - low) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = buildTree(nums, low, mid - 1);
        root.right = buildTree(nums, mid + 1, high);
        return root;
    }
}
