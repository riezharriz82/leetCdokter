import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


/**
 * https://leetcode.com/problems/flatten-nested-list-iterator/
 * <p>
 * You are given a nested list of integers nestedList. Each element is either an integer or a list whose elements may also be integers or other lists.
 * Implement an iterator to flatten it.
 * <p>
 * Implement the NestedIterator class:
 * <p>
 * 1. NestedIterator(List<NestedInteger> nestedList) Initializes the iterator with the nested list nestedList.
 * 2. int next() Returns the next integer in the nested list.
 * 3. boolean hasNext() Returns true if there are still some integers in the nested list and false otherwise.
 * <p>
 * Input: nestedList = [[1,1],2,[1,1]]
 * Output: [1,1,2,1,1]
 * Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].
 * <p>
 * Input: nestedList = [1,[4,[6]]]
 * Output: [1,4,6]
 * Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].
 * <p>
 * Constraints:
 * 1 <= nestedList.length <= 500
 * The values of the integers in the nested list is in the range [-10^6, 10^6].
 */
public class FlattenNestedListIteratorRecursive implements Iterator<Integer> {

    /**
     * Approach: Flatten the list in constructor and store the flattened list in a list. Keep track of current index in the list for handling next()
     * <p>
     * Remember that multiple hasNext() can be invoked as well as multiple next() can be invoked without invoking hasNext()
     * <p>
     * Requires additional space. In order to reduce space complexity to O(1) refer {@link FlattenNestedListIteratorIterative}
     * {@link NestedListWeightSum} {@link PeekingIterator}
     */
    List<Integer> flattenedList = new ArrayList<>();
    int index;

    public FlattenNestedListIteratorRecursive(List<NestedInteger> nestedList) {
        recur(nestedList);
    }

    private void recur(List<NestedInteger> nestedList) {
        for (NestedInteger nestedInteger : nestedList) {
            if (!nestedInteger.isInteger()) {
                recur(nestedInteger.getList());
            } else {
                flattenedList.add(nestedInteger.getInteger());
            }
        }
    }

    @Override
    public Integer next() {
        //in real world there is no guarantee that user invokes hasNext() before calling next()
        if (index == flattenedList.size()) {
            throw new NoSuchElementException();
        }
        return flattenedList.get(index++);
    }

    @Override
    public boolean hasNext() {
        return index != flattenedList.size();
    }

    interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        List<NestedInteger> getList();
    }
}
