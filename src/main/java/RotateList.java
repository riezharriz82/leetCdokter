import common.ListNode;

/**
 * https://leetcode.com/problems/rotate-list
 * <p>
 * Given a linked list, rotate the list to the right by k places, where k is non-negative.
 * <p>
 * Input: 1->2->3->4->5->NULL, k = 2
 * Output: 4->5->1->2->3->NULL
 * Explanation:
 * rotate 1 steps to the right: 5->1->2->3->4->NULL
 * rotate 2 steps to the right: 4->5->1->2->3->NULL
 */
public class RotateList {
    /**
     * Approach: Need to take care of cases where k >= length of linked list
     * Linked list will get divided into two parts (n-k-1) (k)
     * Disconnect the end of the first half, join the second half with the head.
     * Use a dummy node to append the new head to.
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode temp = head;
        int n = 0;
        while (temp != null) {
            n++;
            temp = temp.next;
        }
        k = k % n;
        if (k == 0) {
            return head;
        }
        ListNode dummy = new ListNode(); //dummy node to append the new head
        int target = n - k - 1;

        temp = head;
        for (int i = 0; i < target; i++) {
            temp = temp.next;
        }
        dummy.next = temp.next;
        temp.next = null; //cut off the first half of the list
        temp = dummy.next;
        while (temp.next != null) { //iterate till we reach the end of the list
            temp = temp.next;
        }
        temp.next = head; //join the second half with the first half
        return dummy.next;
    }
}
