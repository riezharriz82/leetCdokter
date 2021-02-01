import common.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/diameter-of-n-ary-tree/ Premium
 * <p>
 * Given a root of an N-ary tree, you need to compute the length of the diameter of the tree.
 * <p>
 * The diameter of an N-ary tree is the length of the longest path between any two nodes in the tree. This path may or may not pass through the root.
 * <p>
 * (Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value.)
 * <p>
 * Input: root = [1,null,3,2,4,null,5,6]
 * Output: 3
 * Explanation: Diameter is shown in red color.
 * <p>
 * Input: root = [1,null,2,null,3,4,null,5,null,6]
 * Output: 4
 * <p>
 * Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * Output: 7
 * <p>
 * Constraints:
 * The depth of the n-ary tree is less than or equal to 1000.
 * The total number of nodes is between [1, 104].
 */
public class DiameterOfNAryTree {
    int maxDiameter;

    /**
     * Approach: Logic is exactly similar to {@link TreeDiameter}, here tree is already provided
     * <p>
     * {@link DiameterOfBinaryTree}
     */
    public int diameter(Node root) {
        DFS(root);
        return maxDiameter;
    }

    private int DFS(Node root) {
        if (root == null) {
            return 0;
        }
        List<Integer> depths = new ArrayList<>();
        for (Node child : root.children) {
            depths.add(DFS(child));
        }
        int longest = 0, secondLongest = 0;
        for (int depth : depths) {
            if (depth > longest) {
                secondLongest = longest;
                longest = depth;
            } else if (depth > secondLongest) {
                secondLongest = depth;
            }
        }
        maxDiameter = Math.max(maxDiameter, longest + secondLongest);
        return 1 + longest;
    }
}
