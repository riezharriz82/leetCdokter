import common.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/reorder-list/
 * <p>
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 * <p>
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 * <p>
 * Given 1->2->3->4, reorder it to 1->4->2->3.
 * <p>
 * Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
 */
public class ReorderLinkedList {

    /**
     * Find the middle point, reverse the linked list after the middle point
     * Keep a dummy node and start appending elements alternatively from first and second part
     */
    public void reorderListSpaceOptimized(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode slow = head, fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next;
            fast = fast.next;
        }
        ListNode second = slow.next;
        slow.next = null; //terminate the first half
        //start reversing the second half
        ListNode prev = null, curr = second;
        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        //head of the second half is now at prev pointer (not curr)
        //keep appending the elements of first and second half alternatively to dummy node
        ListNode dummy = new ListNode();
        while (head != null && prev != null) {
            dummy.next = head; //add first half
            ListNode tmpFirst = head.next;
            head.next = prev; //add second half;
            dummy = prev; //update the dummy for next loop
            head = tmpFirst;
            prev = prev.next;
        }

    }

    //Uses extra space (My initial solution, me dumb)
    public void reorderList(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        ListNode temp = head;
        while (temp != null) {
            list.add(temp);
            temp = temp.next;
        }
        int begin = 0, end = list.size() - 1;
        ListNode dummyTail = new ListNode();
        while (begin <= end) {
            ListNode front = list.get(begin);
            ListNode back = list.get(end);
            dummyTail.next = front;
            front.next = back;
            dummyTail = back;
            begin++;
            end--;
        }
        //break the cycle
        dummyTail.next = null;
    }
}
