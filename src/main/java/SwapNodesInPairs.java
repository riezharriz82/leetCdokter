import common.ListNode;

/**
 * https://leetcode.com/problems/swap-nodes-in-pairs/
 * <p>
 * Given a linked list, swap every two adjacent nodes and return its head.
 * <p>
 * You may not modify the values in the list's nodes. Only nodes itself may be changed.
 * <p>
 * Input: head = [1,2,3,4]
 * Output: [2,1,4,3]
 * <p>
 * Input: head = []
 * Output: []
 * <p>
 * Input: head = [1]
 * Output: [1]
 */
public class SwapNodesInPairs {
    /**
     * Approach: Reverse in groups of size 2
     * Keep three pointers, start of current group, end of current group and start of next group
     * <p>
     * Use a dummy pointer to append to make life easier
     * <p>
     * {@link PrintImmutableLinkedListInReverse} related linked list problem
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(), dummyTail = dummy, firstNode = head, secondNode, next;
        while (firstNode != null && firstNode.next != null) { //check if block of size 2 is available or not
            secondNode = firstNode.next; //second node of current block
            next = secondNode.next; //first node of next block
            dummyTail.next = secondNode; //append the second node to the tail
            dummyTail = dummyTail.next;
            dummyTail.next = firstNode; //append the first node to the tail
            dummyTail = dummyTail.next;
            dummyTail.next = next; //point the tail to the new node of next block, there might be no nodes or 1 node
            firstNode = next; //update the start of the next block
        }
        return dummy.next;
    }
}
