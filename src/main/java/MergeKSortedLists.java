import common.ListNode;

import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/merge-k-sorted-lists/
 * <p>
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 * <p>
 * Merge all the linked-lists into one sorted linked-list and return it.
 * <p>
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * merging them into one sorted list:
 * 1->1->2->3->4->4->5->6
 */
public class MergeKSortedLists {
    /**
     * Approach: Use priority queue to find the smallest node. Once found, add to the priority queue next node of the polled node
     * Use a dummy pointer to append to.
     * {@link KthSmallestElementInSortedMatrix} related priority queue problem
     */
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(), temp = dummy;
        PriorityQueue<ListNode> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.val, o2.val));
        for (ListNode head : lists) {
            if (head != null)
                pq.add(head);
        }
        while (!pq.isEmpty()) {
            ListNode smallestNode = pq.poll();
            temp.next = smallestNode;
            temp = temp.next;
            if (smallestNode.next != null) {
                pq.add(smallestNode.next);
            }
        }
        return dummy.next;
    }
}
