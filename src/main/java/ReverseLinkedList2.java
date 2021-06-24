import common.ListNode;

/**
 * <pre>
 * https://leetcode.com/problems/reverse-linked-list-ii/
 *
 * Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.
 *
 * Input: head = [1,2,3,4,5], left = 2, right = 4
 * Output: [1,4,3,2,5]
 *
 * Input: head = [5], left = 1, right = 1
 * Output: [5]
 *
 * Constraints:
 * The number of nodes in the list is n.
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 *
 * Follow up: Could you do it in one pass?
 * </pre>
 */
public class ReverseLinkedList2 {
    /**
     * Approach: Need to find the left-1'th node and the right+1'th node. Reverse a linked list using 3 pointers for nodes between [left,right]
     * Finally point the next node of left-1'th node to the new head (right node) and the next pointer of the left node to right+1'th node.
     * TimeComplexity: O(N) Single Pass
     * SpaceComplexity: O(1)
     * <p>
     * {@link ReverseLinkedList}
     */
    public ListNode reverseBetween(ListNode head, int left, int right) { //left and right are the indices (1 based)
        if (left == right) { //no reversal required for a single node
            return head;
        } else {
            ListNode originalHead = head;
            ListNode leftMinusOne = null; //points to the left-1'th node
            for (int i = 1; i < left; i++) {
                leftMinusOne = head;
                head = head.next;
            }
            ListNode leftNode = head; //node at the 'left' index
            ListNode prev = null, current = head, next;
            for (int i = left; i <= right; i++) { //reverse nodes between [left, right]
                next = current.next;
                current.next = prev;
                prev = current;
                current = next;
            }
            //prev is the new head of the reversed linked list, point the next node of left-1'th node to prev
            if (leftMinusOne != null) {
                leftMinusOne.next = prev;
            }
            //connect the tail of the reversed linked list to the right+1'th node
            leftNode.next = current;
            if (left == 1) {
                //prev is the new head of the reversed linked list, return it as the new head
                return prev;
            } else {
                //no change in the head of the linked list, return the original head
                return originalHead;
            }
        }
    }
}
