import common.TreeNode;

/**
 * https://leetcode.com/problems/delete-node-in-a-bst/
 * <p>
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.
 * <p>
 * Basically, the deletion can be divided into two stages:
 * <p>
 * Search for a node to remove.
 * If the node is found, delete the node.
 * Note: Time complexity should be O(height of tree).
 * <p>
 * root = [5,3,6,2,4,null,7]
 * key = 3
 * <pre>
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 * </pre>
 * Given key to delete is 3. So we find the node with value 3 and delete it.
 * <p>
 * One valid answer is [5,4,6,2,null,null,7], shown in the following BST.
 * <pre>
 *     5
 *    / \
 *   4   6
 *  /     \
 * 2       7
 * </pre>
 */
public class DeleteNodeInBST {
    /**
     * Approach: The node getting deleted can have
     * 1. No children (Leaf node) : Directly return null
     * 2. One children : Directly return the non-null child
     * 3. Two children : Find the inorder successor in the right subtree, copy it's value to this node and recursively delete the inorder successor.
     * <p>
     * Inorder successor is copied because it will ensure smallest reshuffling of nodes. If you copy any other nodes, no of shifts will increase as you have
     * to ensure the tree is still a BST.
     * Recursively deleting the node is also important as the inorder successor can have 0 or 1 child.
     * <p>
     * {@link InorderSuccessorInBST}
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        return delete(root, key);
    }

    private TreeNode delete(TreeNode root, int key) {
        if (root == null) {
            return null;
        } else {
            if (root.val == key) {
                if (root.left == null && root.right == null) { //both the children are null
                    return null;
                } else if (root.left == null) { //if either of the children are null, return the non null children
                    return root.right;
                } else if (root.right == null) {
                    return root.left;
                } else { //both the children are non null, replace current node with the inorder successor i.e next largest node in the right subtree
                    //here the term inorder successor is loosely defined as it's not necessary that inorder successor always lies in the right subtree
                    //   4
                    //  /
                    // 3
                    //Inorder successor of 3 is 4 but it's a parent of 3
                    //Here since the node has two children, inorder successor will always be present in the right subtree
                    TreeNode nextLargestValue = findInorderSuccessor(root.right);
                    root.val = nextLargestValue.val; //copy the value of the inorder successor
                    root.right = delete(root.right, nextLargestValue.val); //now need to recursively delete the inorder successor
                    return root;
                }
            } else if (root.val > key) {
                root.left = delete(root.left, key);
            } else {
                root.right = delete(root.right, key);
            }
            return root;
        }
    }

    private TreeNode findInorderSuccessor(TreeNode root) {
        TreeNode inorderSuccessor = root;
        while (root != null) {
            inorderSuccessor = root;
            root = root.left;
        }
        return inorderSuccessor;
    }
}
