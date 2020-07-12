import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/subtree-of-another-tree/
 * <p>
 * Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s.
 * A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.
 * <p>
 * Given tree s:
 * <pre>
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 * Given tree t:
 *    4
 *   / \
 *  1   2
 * </pre>
 * <p>
 * Return true, because t has the same structure and node values with a subtree of s.
 */
public class SubTreeOfAnotherTree {
    public boolean isSubtreeSimplified(TreeNode s, TreeNode t) {
        return DFS(s, t);
    }

    private boolean DFS(TreeNode s, TreeNode t) {
        if (s != null) {
            if (s.val == t.val && isIdentical(s, t)) {
                return true; //short circuit
            }
            return DFS(s.left, t) || DFS(s.right, t);
        }
        return false;
    }

    //walk both the tree one step at a time and compare their shape and values
    private boolean isIdentical(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        } else if (s == null || t == null) {
            return false;
        } else {
            return s.val == t.val && isIdentical(s.left, t.left) && isIdentical(s.right, t.right);
        }
    }

    //calculate the preorder and inorder traversal of both the trees and check if traversal list of smaller tree is a sublist of the bigger tree
    public boolean isSubtree(TreeNode s, TreeNode t) {
        List<Integer> inorderS = new ArrayList<>();
        List<Integer> inorderT = new ArrayList<>();
        List<Integer> preorderS = new ArrayList<>();
        List<Integer> preorderT = new ArrayList<>();
        inorder(s, inorderS);
        inorder(t, inorderT);
        preorder(s, preorderS);
        preorder(t, preorderT);
        return Collections.indexOfSubList(inorderS, inorderT) != -1 && Collections.indexOfSubList(preorderS, preorderT) != -1;
    }

    private void preorder(TreeNode node, List<Integer> preorder) {
        if (node == null) {
            preorder.add(Integer.MIN_VALUE); // to handle {1,2,3} and {1,2}
        } else {
            preorder.add(node.val);
            preorder(node.left, preorder);
            preorder(node.right, preorder);
        }
    }

    private void inorder(TreeNode node, List<Integer> inorder) {
        if (node == null) {
            inorder.add(Integer.MIN_VALUE);
        } else {
            inorder(node.left, inorder);
            inorder.add(node.val);
            inorder(node.right, inorder);
        }
    }
}
