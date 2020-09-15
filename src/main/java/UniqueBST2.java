import common.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/unique-binary-search-trees-ii/
 * <p>
 * Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.
 * <p>
 * Example:
 * <p>
 * Input: 3
 * Output:
 * [
 * [1,null,3,2],
 * [3,2,null,1],
 * [3,1,null,null,2],
 * [2,1,3],
 * [1,null,2,null,3]
 * ]
 * Explanation:
 * The above output corresponds to the 5 unique BST's shown below:
 * <pre>
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 * </pre>
 */
public class UniqueBST2 {
    /**
     * Approach: Try to iteratively fix each node from 1 to n as root.
     * Once a node is fixed, the left node can be iteratively fixed from 1 to i-1 and right node can be fixed from i+1 to n
     * Once we have all the left and right nodes, we can create new nodes with value i and left and right pointer set to each unique combination of left & right nodes
     */
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return Arrays.asList();
        }
        return DFS(1, n);
    }

    private List<TreeNode> DFS(int low, int high) {
        if (low > high) {
            List<TreeNode> list = new ArrayList<>();
            list.add(null);
            return list;
        } else if (low == high) {
            return Arrays.asList(new TreeNode(low));
        } else {
            ArrayList<TreeNode> roots = new ArrayList<>();
            for (int i = low; i <= high; i++) {
                List<TreeNode> leftRoots = DFS(low, i - 1);
                List<TreeNode> rightRoots = DFS(i + 1, high);
                //This portion is the key, I was not able to come up with this solution on my own
                //each left and right root are unique which will make the current root as unique
                //So after updating left and right, need to add back to the result
                for (TreeNode left : leftRoots) {
                    for (TreeNode right : rightRoots) {
                        TreeNode root = new TreeNode(i);
                        root.left = left;
                        root.right = right;
                        roots.add(root);
                    }
                }
            }
            return roots;
        }
    }
}
