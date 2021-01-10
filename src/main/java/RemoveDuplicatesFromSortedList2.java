import common.ListNode;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
 * <p>
 * Given the head of a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
 * Return the linked list sorted as well.
 * <p>
 * Input: head = [1,2,3,3,4,4,5]
 * Output: [1,2,5]
 * <p>
 * Input: head = [1,1,1,2,3]
 * Output: [2,3]
 * <p>
 * Constraints:
 * The number of nodes in the list is in the range [0, 300].
 * -100 <= Node.val <= 100
 * The list is guaranteed to be sorted in ascending order.
 */
public class RemoveDuplicatesFromSortedList2 {
    /**
     * Approach: Keep a dummy node and append to it it the current node isn't duplicated.
     * <p>
     * {@link SwapNodesInPairs}  {@link PalindromeLinkedList} {@link ReorderLinkedList}
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummyNode = new ListNode(), tail = dummyNode;
        while (head != null) {
            ListNode temp = head;
            while (temp.next != null && temp.next.val == head.val) { //skip the duplicates, if any
                temp = temp.next;
            }
            if (temp == head) { //if node does not have duplicates
                tail.next = head; //add current element to the dummyNode
                tail = tail.next;
                head = head.next; //move the head
            } else {
                //move the head to the next node, don't append current node to the tail
                head = temp.next;
            }
        }
        tail.next = null; //don't forget to terminate the tail
        return dummyNode.next;
    }
}
