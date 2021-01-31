import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/print-binary-tree/
 * <p>
 * Print a binary tree in an m*n 2D string array following these rules:
 * <p>
 * The row number m should be equal to the height of the given binary tree.
 * The column number n should always be an odd number.
 * The root node's value (in string format) should be put in the exactly middle of the first row it can be put.
 * The column and the row where the root node belongs will separate the rest space into two parts (left-bottom part and right-bottom part).
 * You should print the left subtree in the left-bottom part and print the right subtree in the right-bottom part.
 * The left-bottom part and the right-bottom part should have the same size. Even if one subtree is none while the other is not,
 * you don't need to print anything for the none subtree but still need to leave the space as large as that for the other subtree.
 * However, if two subtrees are none, then you don't need to leave space for both of them.
 * <p>
 * Each unused space should contain an empty string "".
 * Print the subtrees following the same rules.
 * <pre>
 * Input:
 *      1
 *     /
 *    2
 * Output:
 * [["", "1", ""],
 *  ["2", "", ""]]
 *
 * Input:
 *      1
 *     / \
 *    2   3
 *     \
 *      4
 * Output:
 * [["", "", "", "1", "", "", ""],
 *  ["", "2", "", "", "", "3", ""],
 *  ["", "", "4", "", "", "", ""]]
 *
 * Input:
 *       1
 *      / \
 *     2   5
 *    /
 *   3
 *  /
 * 4
 * Output:
 * [["",  "",  "", "",  "", "", "", "1", "",  "",  "",  "",  "", "", ""]
 *  ["",  "",  "", "2", "", "", "", "",  "",  "",  "",  "5", "", "", ""]
 *  ["",  "3", "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]
 *  ["4", "",  "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]]
 * </pre>
 */
public class PrintBinaryTree {
    /**
     * Approach: Recursion, recursively divide the left and right portion of a row and add the current node value to the middle
     * <p>
     * {@link QuadTree} {@link MaximumWidthOfBinaryTree}
     */
    public List<List<String>> printTree(TreeNode root) {
        int height = getHeightOfTree(root);
        List<List<String>> list = new ArrayList<>(height);
        int width = (int) Math.pow(2, height) - 1; //a complete tree of height 3, will have total 7 nodes
        for (int i = 0; i < height; i++) {
            List<String> inner = new ArrayList<>(width);
            for (int j = 0; j < width; j++) {
                inner.add("");
            }
            list.add(inner);
        }
        recur(root, list, 0, 0, width - 1); //note, width - 1 due to 0 based indexing
        return list;
    }

    private void recur(TreeNode root, List<List<String>> list, int row, int low, int high) {
        if (root != null) {
            int mid = low + (high - low) / 2;
            list.get(row).set(mid, Integer.toString(root.val));
            recur(root.left, list, row + 1, low, mid);
            recur(root.right, list, row + 1, mid + 1, high); //recursively divide the right section of the next row
        }
    }

    private int getHeightOfTree(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return Math.max(getHeightOfTree(root.left), getHeightOfTree(root.right)) + 1;
        }
    }
}
