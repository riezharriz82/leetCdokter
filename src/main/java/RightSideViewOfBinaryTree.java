import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-right-side-view/
 * <p>
 * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
 * <p>
 * Input: [1,2,3,null,5,null,4]
 * Output: [1, 3, 4]
 * Explanation:
 * <pre>
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 * </pre>
 */
public class RightSideViewOfBinaryTree {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        DFS(root, res, 0);
        return res;
    }

    private void DFS(TreeNode root, List<Integer> res, int currentLevel) {
        if (root != null) {
            if (currentLevel == res.size()) { //first time seeing this level, need to add
                res.add(root.val);
            } else { //number already present at this level, need to override it, so that we only keep the rightmost element present in the level
                res.set(currentLevel, root.val);
            }
            DFS(root.left, res, currentLevel + 1);
            DFS(root.right, res, currentLevel + 1);
        }
    }
}
