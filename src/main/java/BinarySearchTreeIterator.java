import common.TreeNode;

import java.util.LinkedList;

/**
 * https://leetcode.com/problems/binary-search-tree-iterator/
 * <p>
 * Implement the BSTIterator class that represents an iterator over the in-order traversal of a binary search tree (BST):
 * <p>
 * BSTIterator(TreeNode root) Initializes an object of the BSTIterator class. The root of the BST is given as part of the constructor.
 * The pointer should be initialized to a non-existent number smaller than any element in the BST.
 * <p>
 * boolean hasNext() Returns true if there exists a number in the traversal to the right of the pointer, otherwise returns false.
 * int next() Moves the pointer to the right, then returns the number at the pointer.
 * Notice that by initializing the pointer to a non-existent smallest number, the first call to next() will return the smallest element in the BST.
 * <p>
 * You may assume that next() calls will always be valid. That is, there will be at least a next number in the in-order traversal when next() is called.
 * <p>
 * Input
 * ["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
 * [[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
 * Output
 * [null, 3, 7, true, 9, true, 15, true, 20, false]
 * <p>
 * Explanation
 * BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
 * bSTIterator.next();    // return 3
 * bSTIterator.next();    // return 7
 * bSTIterator.hasNext(); // return True
 * bSTIterator.next();    // return 9
 * bSTIterator.hasNext(); // return True
 * bSTIterator.next();    // return 15
 * bSTIterator.hasNext(); // return True
 * bSTIterator.next();    // return 20
 * bSTIterator.hasNext(); // return False
 * <p>
 * Could you implement next() and hasNext() to run in average O(1) time and use O(h) memory, where h is the height of the tree?
 */
public class BinarySearchTreeIterator {
    LinkedList<Integer> list = new LinkedList<>();

    /**
     * Approach: Store all the contents of the BST in a linkedlist. Space complexity o(n)
     * <p>
     * In order to reduce space complexity to o(h), we can use iterative in order traversal i.e. when next() is called
     * go to the right of node that is at the top of the stack and iteratively push on the left nodes
     */
    public BinarySearchTreeIterator(TreeNode root) {
        recur(root);
    }

    private void recur(TreeNode root) {
        if (root != null) {
            recur(root.left);
            list.addLast(root.val);
            recur(root.right);
        }
    }

    public int next() {
        return list.pollFirst();
    }

    public boolean hasNext() {
        return !list.isEmpty();
    }
}
