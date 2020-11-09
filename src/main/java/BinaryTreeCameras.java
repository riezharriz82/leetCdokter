import common.TreeNode;

/**
 * https://leetcode.com/problems/binary-tree-cameras/
 * <p>
 * Given a binary tree, we install cameras on the nodes of the tree.
 * <p>
 * Each camera at a node can monitor its parent, itself, and its immediate children.
 * <p>
 * Calculate the minimum number of cameras needed to monitor all nodes of the tree.
 * <p>
 * Input: [0,0,null,0,0]
 * Output: 1
 * Explanation: One camera is enough to monitor all nodes if placed as shown.
 * <p>
 * Input: [0,0,null,0,null,0,null,null,0]
 * Output: 2
 * Explanation: At least two cameras are needed to monitor all nodes of the tree. The above image shows one of the valid configurations of camera placement.
 */
public class BinaryTreeCameras {
    /**
     * Approach: Tricky binary tree question {@link DistributeCoinsInBinaryTree} {@link DeleteNodesAndReturnForest}
     * <p>
     * This problem can also be solved similar to "Vertex Cover" problem, where either place the camera on a node
     * or skip it but the recursion becomes complicated and full of if's/else's.
     * https://www.geeksforgeeks.org/vertex-cover-problem-set-2-dynamic-programming-solution-tree
     * <p>
     * An intelligent folk solved it via logic similar to vertex cover
     * https://leetcode.com/problems/binary-tree-cameras/discuss/211223/C%2B%2B-Naive-DFS-%2B-Memo
     * https://leetcode.com/problems/binary-tree-cameras/discuss/211255/24ms-C%2B%2B-solution-(DP-with-memory)-based-on-Vertex-Cover-Problem
     * <p>
     * However the problem gets simplified if we start traversing the tree from bottom and always think about not placing the camera at the leaf node.
     * <p>
     * Critical insight is to understand that not placing the camera at the leaf node always leads to an optimal solution.
     * This leads to a greedy solution instead of a recurrence solution
     * <p>
     * Sequence of placing cameras from the bottom would be covered, uncovered, camera, covered, uncovered, camera....
     */

    int cameras;

    public int minCameraCover(TreeNode root) {
        STATE end = recur(root);
        if (end == STATE.UNCOVERED) { //edge case, if the head is uncovered, need to place a camera here
            cameras++;
        }
        return cameras;
    }

    /**
     * This is a greedy algorithm, visualize placing the cameras from the bottom of the tree, not the top
     * Leaf node shall never have any camera, because it's not optimal. Camera should be at the center not the edge.
     * Remember the sequence of placing cameras, covered, uncovered, cameras.
     */
    private STATE recur(TreeNode root) {
        if (root == null) {
            return STATE.COVERED; //null pointers are covered, tricky
        } else {
            STATE left = recur(root.left);
            STATE right = recur(root.right);
            if (left == STATE.UNCOVERED || right == STATE.UNCOVERED) {
                //place cameras if either of the children nodes are uncovered, so placing cameras here would cover them
                cameras++;
                return STATE.HAS_CAMERA;
            } else if (left == STATE.HAS_CAMERA || right == STATE.HAS_CAMERA) {
                //if either of the children nodes have camera, this node is covered
                return STATE.COVERED;
            } else {
                //if the children are covered, this node is uncovered.
                //the natural hunch would to cover this node but covering it would mean placing a camera at the edge
                //and placing camera at the edge is not optimal, it should be placed at the center
                return STATE.UNCOVERED;
            }
        }
    }

    private enum STATE {
        COVERED, UNCOVERED, HAS_CAMERA
    }
}
