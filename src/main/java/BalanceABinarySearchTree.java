import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/balance-a-binary-search-tree/
 * <p>
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 * <p>
 * Given a binary search tree, return a balanced binary search tree with the same node values.
 * <p>
 * A binary search tree is balanced if and only if the depth of the two subtrees of every node never differ by more than 1.
 * <p>
 * If there is more than one answer, return any of them.
 * <p>
 * Input: root = [1,null,2,null,3,null,4,null,null]
 * Output: [2,1,3,null,null,null,4]
 * Explanation: This is not the only correct answer, [3,1,4,null,2,null,null] is also correct.
 * <p>
 * Constraints:
 * The number of nodes in the tree is between 1 and 10^4.
 * The tree nodes will have distinct values between 1 and 10^5.
 */
public class BalanceABinarySearchTree {
    /**
     * <pre>
     * Approach: Divide and Conquer, Reduce the problem to converting a sorted array to BST by storing the inorder traversal of BST in a list
     *
     * Time Complexity: O(n) Space Complexity: O(n)
     *
     * If we try to reduce the space complexity to O(1)
     * 1. We can convert the tree into a DLL and try to create a balanced BST from DLL, time complexity will be O(nlogn), similar to merge/quick sort
     * but space complexity will be O(1) since we are doing a in-place transformation.
     *
     * 2. In order to reduce the time complexity to O(n) and keep space complexity to O(1), need to use a specialized DSW algorithm
     * https://en.wikipedia.org/wiki/Day%E2%80%93Stout%E2%80%93Warren_algorithm
     * https://leetcode.com/problems/balance-a-binary-search-tree/discuss/541785/C%2B%2BJava-with-picture-DSW-O(n)orO(1)
     * </pre>
     * {@link ConstructTreeFromPreorderAndPostorderTraversal}
     */
    public TreeNode balanceBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        recur(root, list);
        return toBalancedBST(list, 0, list.size() - 1);
    }

    private TreeNode toBalancedBST(List<Integer> list, int low, int high) {
        if (low > high) {
            return null;
        } else if (low == high) {
            return new TreeNode(list.get(low));
        } else {
            int mid = low + (high - low) / 2;
            TreeNode root = new TreeNode(list.get(mid));
            root.left = toBalancedBST(list, low, mid - 1);
            root.right = toBalancedBST(list, mid + 1, high);
            return root;
        }
    }

    private void recur(TreeNode root, List<Integer> list) {
        if (root != null) {
            recur(root.left, list);
            list.add(root.val);
            recur(root.right, list);
        }
    }
}
