import common.ListNode;

/**
 * https://leetcode.com/problems/merge-two-sorted-lists/
 * <p>
 * Merge two sorted linked lists and return it as a new sorted list. The new list should be made by splicing together the nodes of the first two lists.
 * <p>
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 */
public class MergeTwoSortedLists {
    //Approach: Use a dummy node to keep appending the smaller of list1 and list2
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode dummyTail = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                dummyTail.next = l1;
                l1 = l1.next;
            } else {
                dummyTail.next = l2;
                l2 = l2.next;
            }
            dummyTail = dummyTail.next;
        }
        //If the lists are of uneven length, point the tail to the non null list
        if (l1 != null) {
            dummyTail.next = l1;
        } else if (l2 != null) {
            dummyTail.next = l2;
        }
        return dummy.next;
    }
}
