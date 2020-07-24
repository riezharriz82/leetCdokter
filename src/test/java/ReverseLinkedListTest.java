import common.ListNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ReverseLinkedListTest {
    @Test
    public void test() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode newHead = new ReverseLinkedList().reverseList(head);
        assertEquals(5, newHead.val);
        assertEquals(4, newHead.next.val);
        assertEquals(3, newHead.next.next.val);
        assertEquals(2, newHead.next.next.next.val);
        assertEquals(1, newHead.next.next.next.next.val);
        assertNull(newHead.next.next.next.next.next);

    }
}
