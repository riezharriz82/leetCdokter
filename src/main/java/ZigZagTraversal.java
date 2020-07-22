import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ZigZagTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, res, 0);
        return res;
    }

    private void dfs(TreeNode root, List<List<Integer>> res, int level) {
        if (root != null) {
            if (res.size() == level) { //new level found
                res.add(new LinkedList<>());
            }
            List<Integer> elementsInLevel = res.get(level);
            if (level % 2 == 1) {
                //push right element first
                elementsInLevel.add(0, root.val);
            } else {
                //push right element at the end
                elementsInLevel.add(root.val);
            }
            dfs(root.left, res, level + 1);
            dfs(root.right, res, level + 1);
        }
    }
}
