import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/find-root-of-n-ary-tree/ Premium
 * <p>
 * You are given all the nodes of an N-ary tree as an array of Node objects, where each node has a unique value.
 * <p>
 * Return the root of the N-ary tree.
 * <p>
 * An N-ary tree can be serialized as represented in its level order traversal where each group of children is separated by the null value (see examples).
 * <p>
 * For example, the above tree is serialized as [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14].
 * <p>
 * The testing will be done in the following way:
 * <p>
 * The input data should be provided as a serialization of the tree.
 * The driver code will construct the tree from the serialized input data and put each Node object into an array in an arbitrary order.
 * The driver code will pass the array to findRoot, and your function should find and return the root Node object in the array.
 * The driver code will take the returned Node object and serialize it. If the serialized value and the input data are the same, the test passes.
 * <p>
 * Input: tree = [1,null,3,2,4,null,5,6]
 * Output: [1,null,3,2,4,null,5,6]
 * Explanation: The tree from the input data is shown above.
 * The driver code creates the tree and gives findRoot the Node objects in an arbitrary order.
 * For example, the passed array could be [Node(5),Node(4),Node(3),Node(6),Node(2),Node(1)] or [Node(2),Node(6),Node(1),Node(3),Node(5),Node(4)].
 * The findRoot function should return the root Node(1), and the driver code will serialize it and compare with the input data.
 * The input data and serialized Node(1) are the same, so the test passes.
 */
public class FindRootOfNaryTree {
    /**
     * Approach: If we visit all nodes and its children, every node except the root node will be visited twice.
     * Then the problem reduces to finding the node that appears once where all others occurs twice.
     * It can be solved using xor.
     */
    public Node findRootOptimized(List<Node> tree) {
        long res = 0;
        for (Node node : tree) {
            res ^= node.val;
            for (Node child : node.children) {
                res ^= child.val;
            }
        }
        for (Node node : tree) {
            if (node.val == res) { //root node will appear only once
                return node;
            }
        }
        return null;
    }

    /**
     * Approach: The root node will not be part of any nodes children, so if we mark all the children nodes as visited,
     * the root node will be left unvisited
     */
    public Node findRoot(List<Node> tree) {
        Set<Node> visitedByOthers = new HashSet<>();
        for (Node node : tree) {
            visitedByOthers.addAll(node.children); //mark all the children nodes
        }
        for (Node node : tree) {
            if (!visitedByOthers.contains(node)) { //root node is not a child of any node, hence will be unmarked
                return node;
            }
        }
        return null;
    }

    private static class Node {
        public int val;
        public List<Node> children;

        public Node() {
            children = new ArrayList<>();
        }

        public Node(int _val) {
            val = _val;
            children = new ArrayList<>();
        }

        public Node(int _val, ArrayList<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}
