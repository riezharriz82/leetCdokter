import common.ListNode;

/**
 * https://leetcode.com/problems/remove-linked-list-elements/
 * <p>
 * Remove all elements from a linked list of integers that have value val.
 * <p>
 * Input:  1->2->6->3->4->5->6, val = 6
 * <p>
 * Output: 1->2->3->4->5
 */
public class RemoveLinkedListElements {
    public ListNode removeElements(ListNode head, int key) {
        //keep a dummy node to which keep on adding the filtered nodes
        ListNode dummyNode = new ListNode(-1);
        ListNode tailDummyNode = dummyNode;
        while (head != null) {
            if (head.val == key) {
                head = head.next; //simply skip this node
            } else {
                tailDummyNode.next = head; // append this node to the tail of the dummy node
                tailDummyNode = head; // update the new tail
                head = head.next; // this node is done, skip to next node
            }
        }
        tailDummyNode.next = null; //required to trim the tail (if the dummy node is an intermediate node in the original linked list,
        //it's next pointer will still be pointing to the filtered node, which we don't want in the new linked list)
        return dummyNode.next;
    }
}
