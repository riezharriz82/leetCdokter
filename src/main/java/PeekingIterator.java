import java.util.Iterator;

/**
 * https://leetcode.com/problems/peeking-iterator/
 * <p>
 * Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator that support the peek() operation --
 * it essentially peek() at the element that will be returned by the next call to next().
 * <p>
 * Assume that the iterator is initialized to the beginning of the list: [1,2,3].
 * <p>
 * Call next() gets you 1, the first element in the list.
 * Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.
 * You call next() the final time and it returns 3, the last element.
 * Calling hasNext() after that should return false.
 */
public class PeekingIterator implements Iterator<Integer> {
    Integer curr;
    Iterator<Integer> iterator;

    /**
     * Approach: Cache the next value as the current value, keep utilizing the cached value if we peek() the same element
     * Upon calling next(), invalidate the cache
     * <p>
     * This is a very practical example of decorator pattern, plus in order to consider cases where the underlying data stream
     * contains null element, it's not possible to just rely on whether cached value is null or not, need to consider another boolean variable
     * to disambiguate whether we have actually peeked an element or not
     * <p>
     * https://github.com/google/guava/blob/703ef758b8621cfbab16814f01ddcc5324bdea33/guava-gwt/src-super/com/google/common/collect/super/com/google/common/collect/Iterators.java#L1125
     * <p>
     * {@link DesignCompressedStringIterator} {@link CombinationIteratorRecursive} {@link RLEIterator}
     */
    public PeekingIterator(Iterator<Integer> iterator) {
        this.iterator = iterator;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if (curr == null) { //if cached value isn't present, update the cache only if there is a next element present
            if (hasNext()) {
                curr = next();
            }
        }
        return curr;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        if (curr != null) { //if cached value is present, invalidate the cache and return the prior cached value
            Integer temp = curr;
            curr = null;
            return temp;
        } else {
            return iterator.next();
        }
    }

    @Override
    public boolean hasNext() {
        if (curr != null) { //if cached value is present, return true
            return true;
        }
        return iterator.hasNext();
    }
}
