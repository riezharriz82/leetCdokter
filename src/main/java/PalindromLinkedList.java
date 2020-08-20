import common.ListNode;

/**
 * https://leetcode.com/problems/palindrome-linked-list/
 * <p>
 * Given a singly linked list, determine if it is a palindrome.
 * <p>
 * Input: 1->2->2->1
 * Output: true
 */
public class PalindromLinkedList {
    /**
     * Utilized learning from solving {@link ReorderLinkedList}
     * Whenever asked to traverse linked list from the end, reverse the linked list
     * Whenever asked to return a new linked list by doing something, keep a dummy node to append to, makes life easier
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next;
            fast = fast.next;
        }
        //for odd nodes first half is larger
        ListNode second = slow.next;
        slow.next = null;
        ListNode prev = null, curr = second;
        //reverse the second half
        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        //now prev is the head of the second list
        while (head != null && prev != null) {
            if (head.val != prev.val) {
                return false;
            }
            head = head.next;
            prev = prev.next;
        }
        //the way we have splitted leads to a bigger chunk in the left part (+1) for odd size linked list
        // this check ensures that we have checked all the elements in second half
        // so even if the left part is bigger by 1, it can act as a middle element and does not need to be checked
        return true;
    }
}
