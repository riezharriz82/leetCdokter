package concurrency;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * https://leetcode.com/problems/design-bounded-blocking-queue/
 * <p>
 * Implement a thread-safe bounded blocking queue that has the following methods:
 * <p>
 * BoundedBlockingQueue(int capacity) The constructor initializes the queue with a maximum capacity.
 * void enqueue(int element) Adds an element to the front of the queue. If the queue is full, the calling thread is blocked until the queue is no longer full.
 * int dequeue() Returns the element at the rear of the queue and removes it. If the queue is empty, the calling thread is blocked until the queue is no longer empty.
 * int size() Returns the number of elements currently in the queue.
 * <p>
 * Your implementation will be tested using multiple threads at the same time.
 * Each thread will either be a producer thread that only makes calls to the enqueue method or a consumer thread that only makes calls to the dequeue method.
 * The size method will be called after every test case.
 * <p>
 * Please do not use built-in implementations of bounded blocking queue as this will not be accepted in an interview.
 */
public class BoundedBlockingQueue {
    final int MAX_SIZE;
    LinkedList<Integer> queue = new LinkedList<>();
    ReentrantLock lock = new ReentrantLock();
    Condition isNotEmpty = lock.newCondition();
    Condition isNotFull = lock.newCondition();

    /**
     * Standard producer consumer problem, use
     * <p>
     * {@link experience.meesho.InventoryManagement} Meesho interview code
     */
    public BoundedBlockingQueue(int capacity) {
        MAX_SIZE = capacity;
    }

    public void enqueue(int element) throws InterruptedException {
        try {
            lock.lock();
            while (queue.size() == MAX_SIZE) {
                isNotFull.await(); //lock is automatically released and thread is suspended until someone signals on this condition
            }
            queue.addLast(element);
            isNotEmpty.signalAll(); //signal on isNotEmpty() condition to wake up any sleeping consumers
        } finally {
            lock.unlock();
        }
    }

    public int dequeue() throws InterruptedException {
        try {
            lock.lock();
            while (queue.isEmpty()) {
                isNotEmpty.await();
            }
            int value = queue.removeFirst();
            isNotFull.signalAll(); //wake up suspended producers to signal queue is not full anymore
            return value;
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        try {
            lock.lock();
            return queue.size();
        } finally {
            lock.unlock();
        }
    }
}
