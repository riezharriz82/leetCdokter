/**
 * https://leetcode.com/problems/design-circular-queue/
 * <pre>
 * Design your implementation of the circular queue. The circular queue is a linear data structure in which the operations are performed based on FIFO
 * principle and the last position is connected back to the first position to make a circle. It is also called "Ring Buffer".
 *
 * One of the benefits of the circular queue is that we can make use of the spaces in front of the queue. In a normal queue, once the queue becomes full,
 * we cannot insert the next element even if there is a space in front of the queue. But using the circular queue, we can use the space to store new values.
 *
 * Implementation the MyCircularQueue class:
 *
 * MyCircularQueue(k) Initializes the object with the size of the queue to be k.
 * int Front() Gets the front item from the queue. If the queue is empty, return -1.
 * int Rear() Gets the last item from the queue. If the queue is empty, return -1.
 * boolean enQueue(int value) Inserts an element into the circular queue. Return true if the operation is successful.
 * boolean deQueue() Deletes an element from the circular queue. Return true if the operation is successful.
 * boolean isEmpty() Checks whether the circular queue is empty or not.
 * boolean isFull() Checks whether the circular queue is full or not.
 *
 * Input
 * ["MyCircularQueue", "enQueue", "enQueue", "enQueue", "enQueue", "Rear", "isFull", "deQueue", "enQueue", "Rear"]
 * [[3], [1], [2], [3], [4], [], [], [], [4], []]
 * Output
 * [null, true, true, true, false, 3, true, true, true, 4]
 *
 * Explanation
 * MyCircularQueue myCircularQueue = new MyCircularQueue(3);
 * myCircularQueue.enQueue(1); // return True
 * myCircularQueue.enQueue(2); // return True
 * myCircularQueue.enQueue(3); // return True
 * myCircularQueue.enQueue(4); // return False
 * myCircularQueue.Rear();     // return 3
 * myCircularQueue.isFull();   // return True
 * myCircularQueue.deQueue();  // return True
 * myCircularQueue.enQueue(4); // return True
 * myCircularQueue.Rear();     // return 4
 * </pre>
 * Constraints:
 * 1 <= k <= 1000
 * 0 <= value <= 1000
 * At most 3000 calls will be made to enQueue, deQueue, Front, Rear, isEmpty, and isFull
 */
public class DesignCircularQueue {
    /**
     * Approach: Instead of using LinkedList as underlying store for the queue, decided to use a fixed size array.
     * Reason being, LinkedList is slow due to cache miss. Although implementation using linked list would have been a lot simpler :)
     * <p>
     * PS: ArrayDeque uses a fixed size array for queue and it's much faster than linked list
     * https://stackoverflow.com/questions/6129805/what-is-the-fastest-java-collection-with-the-basic-functionality-of-a-queue
     * https://stackoverflow.com/questions/6163166/why-is-arraydeque-better-than-linkedlist
     * <p>
     * My code is inspired from {@link java.util.ArrayDeque} which uses two pointers head and tail
     * Solution could have been simplified if I used another attribute "int count" which denotes the no of elements.
     * <p>
     * Was asked this question in Upstox interview round
     * <p>
     * {@link DesignPhoneDirectorySimplified} {@link MaximumSumCircularSubarray} {@link DesignCircularDeque}
     */
    Integer[] queue; //Notice the Integer[] instead of int[]. This is to leverage null check just like ArrayDeque
    int head = 0, tail = 0; //tail indicates the index at which next element needs to be inserted
    //head indicates the index of element needs to be returned for front() operation

    public DesignCircularQueue(int k) {
        queue = new Integer[k];
    }

    public boolean enQueue(int value) {
        if (isFull()) { //queue is full
            return false;
        } else {
            //insert at tail and increment tail pointer
            queue[tail] = value;
            tail = (tail + 1) % queue.length;
            return true;
        }
    }

    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        } else {
            queue[head] = null;
            head = (head + 1) % queue.length;
            return true;
        }
    }

    public int Front() {
        return isEmpty() ? -1 : queue[head];
    }

    public int Rear() {
        //note that tail indicates the index to put the next element, so last placed element is at index (tail-1)
        return isEmpty() ? -1 : queue[(queue.length + tail - 1) % queue.length];
    }

    public boolean isEmpty() {
        return queue[head] == null && (head == tail);
    }

    public boolean isFull() {
        return queue[head] != null && (head == tail);
    }
}
