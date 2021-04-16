import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * https://leetcode.com/problems/zigzag-iterator/
 * <pre>
 * Given two vectors of integers v1 and v2, implement an iterator to return their elements alternately.
 *
 * Implement the ZigzagIterator class:
 *
 * ZigzagIterator(List<int> v1, List<int> v2) initializes the object with the two vectors v1 and v2.
 * boolean hasNext() returns true if the iterator still has elements, and false otherwise.
 * int next() returns the current element of the iterator and moves the iterator to the next element.
 *
 * Input: v1 = [1,2], v2 = [3,4,5,6]
 * Output: [1,3,2,4,5,6]
 * Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,3,2,4,5,6].
 *
 * Input: v1 = [1], v2 = []
 * Output: [1]
 *
 * Input: v1 = [], v2 = [1]
 * Output: [1]
 * </pre>
 * Constraints:
 * 0 <= v1.length, v2.length <= 1000
 * 1 <= v1.length + v2.length <= 2000
 * -2^31 <= v1[i], v2[i] <= 2^31 - 1
 * <p>
 * Follow up: What if you are given k vectors? How well can your code be extended to such cases?
 * Clarification for the follow-up question:
 * The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic".
 * <p>
 * Example:
 * Input: v1 = [1,2,3], v2 = [4,5,6,7], v3 = [8,9]
 * Output: [1,4,8,2,5,9,3,6,7]
 */
public class ZigZagIterator {
    /**
     * Approach: In my initial solution, I maintained two indices and a boolean flag which indicates whether to pick first list or not.
     * In order to solve it for K lists, solve it similar to {@link MergeKSortedLists} by maintaining a deque of K list directly containing the iterator.
     * <p>
     * If you don't want to put the iterators, you need to maintain a pair of list index and current index in the list. Using iterator simplifies the code.
     * This logic of pushing back the current iterator to the end of the queue is similar to {@link RearrangeStringKDistanceApart} where K = 0
     * <p>
     * {@link PeekingIterator} {@link Flatten2DVector} {@link BinarySearchTreeIterator} {@link FlattenNestedListIteratorIterative}
     */
    ArrayDeque<Iterator<Integer>> queue = new ArrayDeque<>(); //alternative would be store a pair<index in k-list, index in current list>

    public ZigZagIterator(List<Integer> v1, List<Integer> v2) {
        if (!v1.isEmpty()) {
            queue.add(v1.iterator());
        }
        if (!v2.isEmpty()) {
            queue.add(v2.iterator());
        }
    }

    public int next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        } else {
            Iterator<Integer> head = queue.removeFirst();
            int val = head.next();
            if (head.hasNext()) { //push back the current iterator at the end of the queue
                queue.add(head);
            }
            return val;
        }
    }

    public boolean hasNext() {
        return !queue.isEmpty();
    }
}
