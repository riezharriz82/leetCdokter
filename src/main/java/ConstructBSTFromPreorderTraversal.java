import common.TreeNode;

/**
 * https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
 * <p>
 * Return the root node of a binary search tree that matches the given preorder traversal.
 * <p>
 * Input: [8,5,1,7,10,12]
 * Output: [8,5,10,1,7,null,12]
 */
public class ConstructBSTFromPreorderTraversal {
    int preorderIndex;

    /**
     * Leverage range bound property of BST
     */
    public TreeNode bstFromPreorderInLinearTime(int[] preorder) {
        return buildRangeTree(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode buildRangeTree(int[] preorder, int minValue, int maxValue) {
        //filter out invalid root candidates
        if (preorderIndex >= preorder.length || preorder[preorderIndex] > maxValue || preorder[preorderIndex] < minValue) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preorderIndex++]);
        root.left = buildRangeTree(preorder, minValue, root.val);
        root.right = buildRangeTree(preorder, root.val, maxValue);
        return root;
    }

    public TreeNode bstFromPreorder(int[] preorder) {
        return buildTree(preorder, 0, preorder.length - 1);
    }

    /**
     * Approach: Elements in left subtree of BST would be always smaller than root.
     * So find the first largest value in preorder traversal that is greater than root --> elements from root + 1 till this index
     * will give left subarray elements
     */
    private TreeNode buildTree(int[] preorder, int left, int right) {
        if (left > right) {
            return null;
        }
        if (left == right) {
            return new TreeNode(preorder[preorderIndex++]);
        }
        TreeNode root = new TreeNode(preorder[preorderIndex++]);
        int firstGreaterValueIndex = -1;
        for (int i = left; i <= right; i++) {
            if (preorder[i] > root.val) {
                firstGreaterValueIndex = i;
                break;
            }
        }
        if (firstGreaterValueIndex == -1) {
            firstGreaterValueIndex = right + 1; //if no such greater value exists, assign it to right + 1, so that
            //it automatically handles case with no left subtree or no left and right subtree
        }
        root.left = buildTree(preorder, left + 1, firstGreaterValueIndex - 1);
        root.right = buildTree(preorder, firstGreaterValueIndex, right);
        return root;
    }
}
