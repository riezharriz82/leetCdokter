/**
 * https://leetcode.com/problems/min-stack/
 */
public class MinStack {
    private Node head;

    public MinStack() {
    }

    public void push(int x) {
        if (head == null) {
            head = new Node(x, x, null);
        } else {
            head = new Node(x, Math.min(x, head.min), head);
        }
    }

    public void pop() {
        head = head.prev;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
    }

    //Implemented using single stack
    class Node {
        int val;
        int min;
        Node prev;

        public Node(int val, int min, Node prev) {
            this.val = val;
            this.min = min;
            this.prev = prev;
        }
    }

    /* Implemented using two stacks
    private List<Integer> internal;
    private List<Integer> minimum;

    public MinStack() {
        internal = new ArrayList<>();
        minimum = new ArrayList<>();
    }

    public void push(int x) {
        if (minimum.isEmpty()) {
            minimum.add(x);
        } else {
            minimum.add(Math.min(x, minimum.get(minimum.size() - 1)));
        }
        internal.add(x);
    }

    public void pop() {
        internal.remove(internal.size() - 1);
        minimum.remove(minimum.size() - 1);
    }

    public int top() {
        return internal.get(internal.size() - 1);
    }

    public int getMin() {
        return minimum.get(minimum.size() - 1);
    }*/
}
