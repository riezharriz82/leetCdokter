import java.util.Stack;

/**
 * https://leetcode.com/problems/print-immutable-linked-list-in-reverse/ Premium
 * <p>
 * You are given an immutable linked list, print out all values of each node in reverse with the help of the following interface:
 * <p>
 * ImmutableListNode: An interface of immutable linked list, you are given the head of the list.
 * You need to use the following functions to access the linked list (you can't access the ImmutableListNode directly):
 * <p>
 * ImmutableListNode.printValue(): Print value of the current node.
 * ImmutableListNode.getNext(): Return the next node.
 * The input is only given to initialize the linked list internally. You must solve this problem without modifying the linked list.
 * In other words, you must operate the linked list using only the mentioned APIs.
 * <p>
 * Input: head = [1,2,3,4]
 * Output: [4,3,2,1]
 * <p>
 * Input: head = [0,-4,-1,3,-5]
 * Output: [-5,3,-1,-4,0]
 * <p>
 * Input: head = [-2,0,6,4,4,-6]
 * Output: [-6,4,4,6,0,-2]
 */
public class PrintImmutableLinkedListInReverse {
    /**
     * Approach: Use recursion as the implicit stack for printing the linked list in reverse
     * This uses o(n) space
     * In order to reduce space use sqrt decomposition technique i.e. split the linked list in sqrt blocks and print
     * each block in reverse order. This will consume only sqrt(n) space
     */
    public void printLinkedListInReverse(ImmutableListNode head) {
        if (head != null) {
            printLinkedListInReverse(head.getNext());
            head.printValue();
        }
    }

    /**
     * Not my code, just copying it for reference since it's a premium question
     * Q. Why specifically the sqrt? why not any other grouping number?
     * A: If you set group size k, then you need a stack of N/k. So the space is max(k, N/k)
     * Obviously k==sqrt(N) can minimize it.
     */
    public void printLinkedListInReverseSqrtDecomposition(ImmutableListNode head) {
        // Time: O(n)
        int numNodesCount = getNumNodesCount(head);

        // Time: O(n) Space: O(sqrt(n))
        int step = (int) Math.sqrt(numNodesCount) + 1;
        Stack<ImmutableListNode> headNodes = new Stack<>();
        addNodeWithStep(head, step, headNodes);

        // Time: O(n) Space: O(sqrt(n))
        printEachHeadNodesInReverseOrder(headNodes);
    }

    private int getNumNodesCount(ImmutableListNode head) {
        int count = 0;
        ImmutableListNode node = head;
        while (node != null) {
            count++;
            node = node.getNext();
        }
        return count;
    }

    private void addNodeWithStep(ImmutableListNode head, int step, Stack<ImmutableListNode> headNodes) {
        ImmutableListNode node = head;
        int i = 0;
        while (node != null) {
            if (i % step == 0) {
                headNodes.push(node);
            }
            node = node.getNext();
            i++;
        }
    }

    private void printEachHeadNodesInReverseOrder(Stack<ImmutableListNode> headNodes) {
        ImmutableListNode startNode = null;
        ImmutableListNode endNode = null;
        ImmutableListNode tempNode = null;

        while (!headNodes.isEmpty()) {
            endNode = startNode;
            startNode = headNodes.pop();
            tempNode = startNode;

            Stack<ImmutableListNode> stack = new Stack<>();
            while (tempNode != endNode) { //need to pop till we reach the start of the next block
                stack.push(tempNode);
                tempNode = tempNode.getNext();
            }

            while (!stack.isEmpty()) {
                stack.pop().printValue();
            }
        }
    }

    private interface ImmutableListNode {
        void printValue(); // print the value of this node.

        ImmutableListNode getNext(); // return the next node.
    }
}
