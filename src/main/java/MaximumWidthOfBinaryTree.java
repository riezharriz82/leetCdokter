import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/maximum-width-of-binary-tree/
 * <p>
 * Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels.
 * The binary tree has the same structure as a full binary tree, but some nodes are null.
 * <p>
 * The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level,
 * where the null nodes between the end-nodes are also counted into the length calculation.
 */
public class MaximumWidthOfBinaryTree {
    public int widthOfBinaryTreeSimplified(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        return DFS(root, list, 1, 0);
    }

    private int DFS(TreeNode root, List<Integer> list, int index, int level) {
        if (root == null) {
            return 0;
        }
        if (list.size() == level) { //first time seeing this level
            list.add(index); //add the first index
        }
        int curWidth = index - list.get(level); //compare the width against the first index
        int leftMaxWidth = DFS(root.left, list, 2 * index, level + 1);
        int rightMaxWidth = DFS(root.right, list, 2 * index + 1, level + 1);
        return Math.max(curWidth, Math.max(leftMaxWidth, rightMaxWidth));
    }

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Map<Integer, MinMax> map = new HashMap<>();
        DFS(root, 0, map, 1);
        int maxWidth = 0;
        for (Map.Entry<Integer, MinMax> entry : map.entrySet()) {
            MinMax minMax = entry.getValue();
            maxWidth = Math.max(maxWidth, minMax.max - minMax.min);
        }
        return maxWidth;
    }

    private void DFS(TreeNode root, int level, Map<Integer, MinMax> map, int val) {
        if (root == null) {
            return;
        }
        if (!map.containsKey(level)) {
            map.put(level, new MinMax(val, val));
        } else {
            //update the min and max indexes by comparing to what is present at the level
            MinMax minMax = map.get(level);
            minMax.min = Math.min(minMax.min, val);
            minMax.max = Math.max(minMax.max, val);
        }
        DFS(root.left, level + 1, map, 2 * val); //left index is 2*i
        DFS(root.right, level + 1, map, 2 * val + 1); //right index is 2*i + 1
    }

    class MinMax {
        int min;
        int max;

        public MinMax(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }
}
