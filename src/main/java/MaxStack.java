import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/max-stack/ Premium
 * <p>
 * Design a max stack that supports push, pop, top, peekMax and popMax.
 * <p>
 * push(x) -- Push element x onto stack.
 * pop() -- Remove the element on top of the stack and return it.
 * top() -- Get the element on the top.
 * peekMax() -- Retrieve the maximum element in the stack.
 * popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements, only remove the top-most one.
 * <p>
 * MaxStack stack = new MaxStack();
 * stack.push(5);
 * stack.push(1);
 * stack.push(5);
 * stack.top(); -> 5
 * stack.popMax(); -> 5
 * stack.top(); -> 1
 * stack.peekMax(); -> 5
 * stack.pop(); -> 1
 * stack.top(); -> 5
 * <p>
 * Note:
 * -1e7 <= x <= 1e7
 * Number of operations won't exceed 10000.
 * The last four operations won't be called when stack is empty.
 */
public class MaxStack {
    Node head, tail;
    TreeMap<Integer, LinkedList<Node>> maxVal;

    /**
     * Approach:
     * Stack can be modelled as doubly linked list but it does not supports finding max element.
     * In order to identify and remove the max element we need to use treeMap, but how to remove the max element from the actual stack?
     * We can do that by calling removeFirstOccurrence() on the stack but that's a linear operation.
     * In order to remove in constant time, we can store the node address directly in treeMap, so that we can delete that node directly
     * <p>
     * Similar to LRUCache, keep dummy head and tail pointer to easily handle null cases
     * {@link LRUCache} {@link MaxFrequencyStackSimplified} for related design questions
     */
    public MaxStack() {
        head = new Node(-1);
        tail = new Node(-1);
        head.next = tail;
        tail.prev = head;
        maxVal = new TreeMap<>();
    }

    public void push(int x) {
        Node cur = addNode(x);
        maxVal.computeIfAbsent(x, __ -> new LinkedList<>()).addLast(cur);
    }

    //add a new node after the dummy head pointer
    //head -> node -> tail
    private Node addNode(int x) {
        Node cur = new Node(x);
        Node temp = head.next;
        head.next = cur;
        cur.next = temp;
        temp.prev = cur;
        cur.prev = head;
        return cur;
    }

    //remove the first element after the dummy head pointer
    public int pop() {
        Node recent = removeNode();
        //keep treeMap in sync
        LinkedList<Node> nodes = maxVal.get(recent.val);
        nodes.removeLast();
        if (nodes.isEmpty()) {
            maxVal.remove(recent.val);
        }
        return recent.val;
    }

    private Node removeNode() {
        Node recent = head.next;
        Node temp = recent.next;
        head.next = temp;
        temp.prev = head;
        return recent;
    }

    public int top() {
        return head.next.val;
    }

    public int peekMax() {
        return maxVal.lastKey();
    }

    public int popMax() {
        Map.Entry<Integer, LinkedList<Node>> largestVal = maxVal.lastEntry();
        Node target = largestVal.getValue().getLast();
        //delete the target node by joining the prev and next node
        Node prev = target.prev;
        Node next = target.next;
        prev.next = next;
        next.prev = prev;
        largestVal.getValue().removeLast();
        if (largestVal.getValue().isEmpty()) {
            maxVal.remove(largestVal.getKey());
        }
        return largestVal.getKey();
    }

    //don't need custom equals and hashcode method because by default equals compares by reference and that's what we need
    private static class Node {
        int val;
        Node prev;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }
}
