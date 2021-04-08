/**
 * https://leetcode.com/problems/inorder-successor-in-bst-ii/ Premium
 * <p>
 * Given a node in a binary search tree, return the in-order successor of that node in the BST. If that node has no in-order successor, return null.
 * <p>
 * The successor of a node is the node with the smallest key greater than node.val.
 * <p>
 * You will have direct access to the node but not to the root of the tree. Each node will have a reference to its parent node. Below is the definition for Node:
 * <p>
 * class Node {
 * public int val;
 * public Node left;
 * public Node right;
 * public Node parent;
 * }
 * <p>
 * Input: tree = [2,1,3], node = 1
 * Output: 2
 * Explanation: 1's in-order successor node is 2. Note that both the node and the return value is of Node type.
 * <p>
 * Input: tree = [5,3,6,2,4,null,null,1], node = 6
 * Output: null
 * Explanation: There is no in-order successor of the current node, so the answer is null.
 * <p>
 * Input: tree = [15,6,18,3,7,17,20,2,4,null,13,null,null,null,null,null,null,null,null,9], node = 15
 * Output: 17
 * <p>
 * Input: tree = [15,6,18,3,7,17,20,2,4,null,13,null,null,null,null,null,null,null,null,9], node = 13
 * Output: 15
 * <p>
 * Input: tree = [0], node = 0
 * Output: null
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [1, 104].
 * -10^5 <= Node.val <= 10^5
 * All Nodes will have unique values.
 * <p>
 * Follow up: Could you solve it without looking up any of the node's values?
 */
public class InorderSuccessorInBST2 {
    /**
     * Approach: There are 2 cases for finding inorder successor
     * 1. If the target node has right child, find the left most node in the right subtree. This will return the smallest node greater than target node.
     * <p>
     * 2. If the target node does not have right child, find the first parent that whose left child equals the current node. This will return the first node greater than
     * target node because parent.left == current node. This means parent.val > current node.val hence we find a node greater than current node.
     * <p>
     * {@link InorderSuccessorInBST} {@link DeleteNodeInBST}
     */
    public Node inorderSuccessor(Node node) {
        if (node == null) {
            return null;
        } else if (node.right != null) {
            return findLeftMostChild(node.right);
        } else {
            return findParentWithLeftChildEqualsCurrentNode(node);
        }
    }

    private Node findParentWithLeftChildEqualsCurrentNode(Node root) {
        while (root != null) {
            Node parent = root.parent;
            if (parent != null && parent.left == root) { //parent.left == root is important, it can't be parent.left != null
                //as we need to find a node whose value is greater than current node
                //if it's value is greater, current node must be the left child of that node.
                //we could also have checked parent.val > root.val but the followup part of the problem mentioned not to inspect node's value.
                return parent;
            }
            root = parent;
        }
        return null;
    }

    private Node findLeftMostChild(Node root) {
        Node result = null;
        while (root != null) {
            result = root;
            root = root.left;
        }
        return result;
    }

    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }
}
