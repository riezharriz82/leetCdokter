import common.ListNode;

import java.util.Random;

/**
 * https://leetcode.com/problems/linked-list-random-node/
 * <p>
 * Given a singly linked list, return a random node's value from the linked list. Each node must have the same probability of being chosen.
 * <p>
 * Follow up:
 * What if the linked list is extremely large and its length is unknown to you? Could you solve this efficiently without using extra space?
 * <p>
 * // Init a singly linked list [1,2,3].
 * ListNode head = new ListNode(1);
 * head.next = new ListNode(2);
 * head.next.next = new ListNode(3);
 * Solution solution = new Solution(head);
 * <p>
 * // getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
 * solution.getRandom();
 */
public class LinkedListRandomNode {
    ListNode root;

    public LinkedListRandomNode(ListNode head) {
        root = head;
    }

    /**
     * Approach: Reservoir sampling where sample size = 1
     * <p>
     * {@link ShuffleArray} for shuffling algorithm
     */
    public int getRandom() {
        int candidate = -1;
        ListNode temp = root;
        int counter = 1;
        while (temp != null) {
            int randomIndex = new Random().nextInt(counter); //pick a random index between [0, counter)
            //here reservoir sample size is 1, so we check if the random index fits in the resorvoir or not
            //if yes, we pick it, for sample size of k, we need to check if randomIndex < k or not
            //if yes, replace the element at randomIndex with the current element
            if (randomIndex == 0) {
                candidate = temp.val;
            }
            temp = temp.next;
            counter++;
        }
        return candidate;
    }
}
