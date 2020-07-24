import java.util.ArrayDeque;

/**
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/
 * <p>
 * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children.
 * <p>
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 * <p>
 * Initially, all next pointers are set to NULL.
 */
public class PopulatingNextRightPointer {
    //idea is to iterate one level and keep updating next pointers of next level by creating sort of a linked list
    public Node connectUsingLevelOrderTraversal(Node root) {
        if (root == null) {
            return null;
        } else {
            Node rootReference = root; //required to return
            while (root != null) {
                Node temporaryNode = new Node(); //required to store references of nodes of next level
                Node tailNode = temporaryNode; // tail of next level, will update the next pointer of the tail
                while (root != null) {
                    if (root.left != null) {
                        tailNode.next = root.left; // append to tail
                        tailNode = tailNode.next; // update the tail
                    }
                    if (root.right != null) {
                        tailNode.next = root.right;
                        tailNode = tailNode.next;
                    }
                    root = root.next; // move on to next node in the same level
                }
                //one level is done, need to move on to next level node
                root = temporaryNode.next;
            }
            return rootReference;
        }
    }

    public Node connectUsingBFS(Node root) {
        if (root == null) {
            return null;
        }
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node prev = null; //keep track of the previous node in the level
            for (int i = 0; i < size; i++) {
                Node head = queue.remove();
                if (head.left != null) {
                    queue.add(head.left);
                }
                if (head.right != null) {
                    queue.add(head.right);
                }
                if (prev != null) {
                    prev.next = head; // update the next pointer of the previous node
                }
                head.next = null; // terminate the next pointer of the current node
                prev = head; //increment the previous pointer
            }
        }
        return root;
    }

    private static class Node {
        int val;
        Node left;
        Node right;
        Node next;
    }
}
