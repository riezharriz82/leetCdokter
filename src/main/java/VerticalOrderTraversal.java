import common.TreeNode;
import javafx.util.Pair;

import java.util.*;

/**
 * https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
 *
 * Given a binary tree, return the vertical order traversal of its nodes values.
 * <p>
 * For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and (X+1, Y-1).
 * <p>
 * Running a vertical line from X = -infinity to X = +infinity, whenever the vertical line touches some nodes,
 * we report the values of the nodes in order from top to bottom (decreasing Y coordinates).
 * <p>
 * If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.
 * <p>
 * Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.
 * <p>
 * Input: [1,2,3,4,5,6,7]
 * Output: [[4],[2],[1,5,6],[3],[7]]
 * Explanation:
 * The node with value 5 and the node with value 6 have the same position according to the given scheme.
 * However, in the report "[1,5,6]", the node value of 5 comes first since 5 is smaller than 6.
 */
public class VerticalOrderTraversal {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        //pair of (y, value), result should be in increasing value of y, for same y, smaller value should be returned first
        //treemap because we need to return the list in increasing order of x
        Map<Integer, PriorityQueue<Pair<Integer, Integer>>> map = new TreeMap<>();
        DFS(root, 0, 0, map);
        List<List<Integer>> res = new ArrayList<>();
        for (Map.Entry<Integer, PriorityQueue<Pair<Integer, Integer>>> entry : map.entrySet()) {
            List<Integer> tmp = new ArrayList<>();
            PriorityQueue<Pair<Integer, Integer>> pq = entry.getValue();
            while (!pq.isEmpty()) {
                tmp.add(pq.poll().getValue());
            }
            res.add(tmp);
        }
        return res;
    }

    private void DFS(TreeNode root, int x, int y, Map<Integer, PriorityQueue<Pair<Integer, Integer>>> map) {
        if (root != null) {
            PriorityQueue<Pair<Integer, Integer>> pq = map.getOrDefault(x, new PriorityQueue<>((o1, o2) -> {
                if (o1.getKey().equals(o2.getKey())) { //if y is same, compare on basis of actual value
                    return Integer.compare(o1.getValue(), o2.getValue());
                } else { //smaller y should come first
                    return Integer.compare(o1.getKey(), o2.getKey());
                }
            }));
            map.put(x, pq);
            pq.add(new Pair<>(y, root.val));
            DFS(root.left, x - 1, y - 1, map);
            DFS(root.right, x + 1, y - 1, map);
        }
    }
}
