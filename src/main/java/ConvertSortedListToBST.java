import common.ListNode;
import common.TreeNode;

/**
 * https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
 * <pre>
 * Given the head of a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 *
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 *
 * Input: head = [-10,-3,0,5,9]
 * Output: [0,-3,9,-10,null,5]
 * Explanation: One possible answer is [0,-3,9,-10,null,5], which represents the shown height balanced BST.
 *
 * Input: head = []
 * Output: []
 *
 * Input: head = [0]
 * Output: [0]
 *
 * Input: head = [1,3]
 * Output: [3,1]
 *
 * Constraints:
 * The number of nodes in head is in the range [0, 2 * 10^4].
 * -10^5 <= Node.val <= 10^5
 * </pre>
 */
public class ConvertSortedListToBST {
    /**
     * Approach: Leverage the inorder traversal as the array is already sorted to construct the BST while recursively splitting the list
     * Time Complexity: O(n) Space Complexity: O(logn) due to recursion
     * <p>
     * Was not able to think this on my own.
     * <p>
     * {@link HeightBalancedBinaryTree} {@link BalanceABinarySearchTree} {@link ConstructTreeFromPreorderAndPostorderTraversal}
     */
    ListNode head;

    public TreeNode sortedListToBSTOptimized(ListNode head) {
        this.head = head;
        int size = findSize(head);
        return recur(0, size - 1);
    }

    private int findSize(ListNode head) {
        int size = 0;
        while (head != null) {
            size++;
            head = head.next;
        }
        return size;
    }

    private TreeNode recur(int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = start + (end - start) / 2;
        //tricky part, leverage the inorder traversal
        TreeNode left = recur(start, mid - 1);
        TreeNode root = new TreeNode(head.val);
        root.left = left;
        head = head.next;
        root.right = recur(mid + 1, end);
        return root;
    }

    /**
     * Approach: Divide and Conquer. Find the middle of the linked list and mark it as root of the tree.
     * Recurse left to repeat the process for all the nodes on the left of the root. Since it's a singly linked list, use a dummyHead
     * to keep track of all nodes on the left of the middle node.
     * <p>
     * Time Complexity: O(nlogn) O(n/2) (time complexity per branch) * log(n) (no of branches)
     * Space Complexity: O(logn) because of recursion
     * <p>
     * In order to reduce the time complexity to O(n) we have to trade space complexity to O(n) by storing all the elements in an arraylist
     * which will allow us to get the middle in O(1) instead of O(n/2)
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        } else {
            ListNode dummyHead = new ListNode(), dummyTail = dummyHead; //useful to keep track of nodes at the left of the middle node
            ListNode slow = head, fast = head;//slow will point to the middle node
            while (fast.next != null && fast.next.next != null) {
                dummyTail.next = slow;
                dummyTail = dummyTail.next;
                slow = slow.next;
                fast = fast.next.next;
            }
            dummyTail.next = null;
            TreeNode root = new TreeNode(slow.val); //make the middle node the root of the tree
            root.left = sortedListToBST(dummyHead.next);
            root.right = sortedListToBST(slow.next);
            return root;
        }
    }
}
