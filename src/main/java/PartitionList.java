import common.ListNode;

/**
 * https://leetcode.com/problems/partition-list/
 * <p>
 * Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 * <p>
 * You should preserve the original relative order of the nodes in each of the two partitions.
 * <p>
 * Input: head = [1,4,3,2,5,2], x = 3
 * Output: [1,2,2,4,3,5]
 * <p>
 * Input: head = [2,1], x = 2
 * Output: [1,2]
 * <p>
 * Constraints:
 * The number of nodes in the list is in the range [0, 200].
 * -100 <= Node.val <= 100
 * -200 <= x <= 200
 */
public class PartitionList {
    /**
     * Approach: As learnt earlier, whenever asked to reorder a linked list use dummy pointers. Here we can visualize the problem
     * as splitting the list into two parts, one containing all smaller elements and other containing >= elements.
     * In the end, concatenate the list.
     * <p>
     * This problem is very similar to quick sort or quick select but remember that quick select isn't stable
     * <p>
     * {@link ReorderLinkedList} {@link RotateList}
     */
    public ListNode partition(ListNode head, int x) {
        ListNode smallerNode = new ListNode(), greaterNode = new ListNode();
        ListNode smallerNodeTail = smallerNode, greaterNodeTail = greaterNode; //keep appending to tail
        while (head != null) {
            if (head.val < x) {
                smallerNodeTail.next = head;
                smallerNodeTail = smallerNodeTail.next;
            } else {
                greaterNodeTail.next = head;
                greaterNodeTail = greaterNodeTail.next;
            }
            head = head.next;
        }
        smallerNodeTail.next = greaterNode.next; //link the first list with the second list, remember to link it with the .next() node
        //because greaterNode is just a dummyNode
        greaterNodeTail.next = null; //terminate the last node to avoid possible cycles
        return smallerNode.next;
    }
}
