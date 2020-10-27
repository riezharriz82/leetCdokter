import common.ListNode;

/**
 * https://leetcode.com/problems/linked-list-cycle-ii/
 * <p>
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * <p>
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer.
 * Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
 * <p>
 * Notice that you should not modify the linked list.
 * <p>
 * Input: head = [3,2,0,-4], pos = 1
 * Output: tail connects to node index 1
 * Explanation: There is a cycle in the linked list, where tail connects to the second node.
 */
public class LinkedListCycle2 {
    /**
     * Approach: Warshall Floyd Cycle detection algorithm
     * First detect whether a cycle is present, if yes, then find the intersection point
     * <p>
     * Care must be taken to handle null pointers
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next;
            fast = fast.next;
            if (slow == fast) {
                break;
            }
        }
        if (fast == null || fast.next == null || fast.next.next == null) {
            //if the loop terminated because fast reached a null pointer, then there is no cycle
            return null;
        }
        slow = head;
        while (slow != fast) { //find the intersection point by moving the slow pointer back to head and incrementing both of them one node at a time
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
