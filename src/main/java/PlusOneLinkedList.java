import common.ListNode;

/**
 * https://leetcode.com/problems/plus-one-linked-list/ Premium
 * <p>
 * Given a non-negative integer represented as a linked list of digits, plus one to the integer.
 * <p>
 * The digits are stored such that the most significant digit is at the head of the list.
 * <p>
 * Input: head = [1,2,3]
 * Output: [1,2,4]
 * <p>
 * Input: head = [0]
 * Output: [1]
 * <p>
 * Constraints:
 * The number of nodes in the linked list is in the range [1, 100].
 * 0 <= Node.val <= 9
 * The number represented by the linked list does not contain leading zeros except for the zero itself.
 */
public class PlusOneLinkedList {
    /**
     * Approach: Use recursion as an implicit stack to carry the results if required
     * <p>
     * Special case if the last digit has a carry. Need to create a new node to handle it.
     */
    public ListNode plusOne(ListNode head) {
        int carry = recur(head);
        if (carry == 1) {
            ListNode newHead = new ListNode(1);
            newHead.next = head;
            return newHead;
        } else {
            return head;
        }
    }

    private int recur(ListNode node) {
        if (node == null) {
            return 1;
        } else {
            int carry = recur(node.next);
            int curVal = node.val;
            node.val = (curVal + carry) % 10;
            carry = (curVal + carry) / 10;
            return carry;
        }
    }
}
