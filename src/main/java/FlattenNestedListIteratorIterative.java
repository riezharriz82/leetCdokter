import javafx.util.Pair;

import java.util.ArrayDeque;
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
public class FlattenNestedListIteratorIterative implements Iterator<Integer> {

    /**
     * Approach: Maintain a pair of current list and the current index in a stack, always ensure hasNext() is called before processing next()
     * hasNext() will ensure index of the list at the top is pointing to an integer If it's a list, it will push a new list to top of stack
     * <p>
     * Always ensure that multiple calls to hasNext() is idempotent. This is handled by doing a noop if the element at the current index of the list is an integer
     * <p>
     * I was not able to cleanly implement this solution and was going in circles. Although I was able to think of keeping current list and index in the stack, but
     * was not able to cleanly push and pop from the stack. Still a long way to go.
     * <p>
     * For recursive implementation refer {@link FlattenNestedListIteratorRecursive}
     * <p>
     * {@link NestedListWeightSum} {@link PeekingIterator} {@link DecodeString}
     */
    ArrayDeque<Pair<List<NestedInteger>, Integer>> stack = new ArrayDeque<>(); //stack of pair<list, index>

    public FlattenNestedListIteratorIterative(List<NestedInteger> nestedList) {
        stack.push(new Pair<>(nestedList, 0));
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Pair<List<NestedInteger>, Integer> top = stack.peek();
        int val = top.getKey().get(top.getValue()).getInteger();
        stack.pop();
        //increment the index of current list and push back to the stack, if it's out of bound, next call to hasNext() will take care of popping it.
        stack.push(new Pair<>(top.getKey(), top.getValue() + 1));
        return val;
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            Pair<List<NestedInteger>, Integer> top = stack.peek();
            if (top.getValue() == top.getKey().size()) { //current list is fully traversed as current index is out of bounds
                stack.pop();
                continue;
            }
            if (top.getKey().get(top.getValue()).isInteger()) { //multiple calls to hasNext() will be no-op because of this
                return true;
            } else {
                //pop current element because we need to increment the current index to ensure we process the next element of the current list
                //when the new pushed element of the stack is processed
                //visualize it by understanding the recursion, when the recursive function returns, it needs to remember the position
                //in the caller stack, which is generally handled by a for loop, since there is no for loop, we explicitly push the next index in the stack
                stack.pop();
                stack.push(new Pair<>(top.getKey(), top.getValue() + 1));
                stack.push(new Pair<>(top.getKey().get(top.getValue()).getList(), 0)); //push new list
            }
        }
        return false;
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
