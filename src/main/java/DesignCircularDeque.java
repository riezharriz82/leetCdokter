/**
 * https://leetcode.com/problems/design-circular-deque/
 *
 * <pre>
 * Design your implementation of the circular double-ended queue (deque).
 *
 * Your implementation should support following operations:
 *
 * MyCircularDeque(k): Constructor, set the size of the deque to be k.
 * insertFront(): Adds an item at the front of Deque. Return true if the operation is successful.
 * insertLast(): Adds an item at the rear of Deque. Return true if the operation is successful.
 * deleteFront(): Deletes an item from the front of Deque. Return true if the operation is successful.
 * deleteLast(): Deletes an item from the rear of Deque. Return true if the operation is successful.
 * getFront(): Gets the front item from the Deque. If the deque is empty, return -1.
 * getRear(): Gets the last item from Deque. If the deque is empty, return -1.
 * isEmpty(): Checks whether Deque is empty or not.
 * isFull(): Checks whether Deque is full or not.
 *
 * MyCircularDeque circularDeque = new MyCircularDeque(3); // set the size to be 3
 * circularDeque.insertLast(1);			// return true
 * circularDeque.insertLast(2);			// return true
 * circularDeque.insertFront(3);			// return true
 * circularDeque.insertFront(4);			// return false, the queue is full
 * circularDeque.getRear();  			// return 2
 * circularDeque.isFull();				// return true
 * circularDeque.deleteLast();			// return true
 * circularDeque.insertFront(4);			// return true
 * circularDeque.getFront();			// return 4
 *
 * Note:
 * All values will be in the range of [0, 1000].
 * The number of operations will be in the range of [1, 1000].
 * Please do not use the built-in Deque library.
 * </pre>
 */
public class DesignCircularDeque {

    /**
     * Approach: Very similar to {@link DesignCircularDeque} but here we can insert at head index as well.
     */
    Integer[] queue;
    int head, tail; //tail indicates the index at which last element needs to be inserted
    //head indicates the index of first element, so in case of insertFront(), element needs to be inserted at (head-1) index
    //in order to have parity with the convention of tail index, we can init head with (queue.length - 1) instead

    public DesignCircularDeque(int k) {
        queue = new Integer[k];
    }

    /**
     * Adds an item at the front of Deque. Return true if the operation is successful.
     */
    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        } else {
            int n = queue.length;
            //remember head indicates the index of first element, so need to insert new front element at (head-1)
            //this is similar to ArrayDeque implementation
            queue[(n + head - 1) % n] = value;
            head = (n + head - 1) % n;
            return true;
        }
    }

    /**
     * Adds an item at the rear of Deque. Return true if the operation is successful.
     */
    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        } else {
            queue[tail] = value;
            tail = (tail + 1) % queue.length;
            return true;
        }
    }

    /**
     * Deletes an item from the front of Deque. Return true if the operation is successful.
     */
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        } else {
            queue[head] = null;
            head = (head + 1) % queue.length;
            return true;
        }
    }

    /**
     * Deletes an item from the rear of Deque. Return true if the operation is successful.
     */
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        } else {
            int n = queue.length;
            queue[(n + tail - 1) % n] = null;
            tail = (n + tail - 1) % n;
            return true;
        }
    }

    /**
     * Get the front item from the deque.
     */
    public int getFront() {
        return isEmpty() ? -1 : queue[head];
    }

    /**
     * Get the last item from the deque.
     */
    public int getRear() {
        int n = queue.length;
        return isEmpty() ? -1 : queue[(n + tail - 1) % n];
    }

    /**
     * Checks whether the circular deque is empty or not.
     */
    public boolean isEmpty() {
        return head == tail && queue[head] == null;
    }

    /**
     * Checks whether the circular deque is full or not.
     */
    public boolean isFull() {
        return head == tail && queue[head] != null;
    }
}
