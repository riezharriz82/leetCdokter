import common.ListNode;

/**
 * https://leetcode.com/problems/odd-even-linked-list/
 * <p>
 * Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.
 * <p>
 * You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
 * <p>
 * Input: 1->2->3->4->5->NULL
 * Output: 1->3->5->2->4->NULL
 * <p>
 * Input: 2->1->3->5->6->4->7->NULL
 * Output: 2->3->6->7->1->5->4->NULL
 */
public class OddEvenLinkedList {
    public ListNode oddEvenList(ListNode head) {
        int index = 1;
        //marker node to store even and odd sublist
        ListNode dummyEvenNode = new ListNode(-1), dummyOddNode = new ListNode(-1);
        //tail node of even and odd sub list
        ListNode tailEvenNode = dummyEvenNode, tailOddNode = dummyOddNode;
        while (head != null) {
            if (index % 2 == 0) {
                tailEvenNode.next = head; //append this node to the tail of even sublist
                tailEvenNode = tailEvenNode.next; //update the tail
            } else {
                tailOddNode.next = head;
                tailOddNode = tailOddNode.next;
            }
            index++;
            head = head.next;
        }
        tailEvenNode.next = null; //terminate the even sublist as it might be pointing to elements in the original list
        tailOddNode.next = dummyEvenNode.next; //even sublist should be appended after the odd sublist
        return dummyOddNode.next; // return the first node of the odd sublist
    }
}
