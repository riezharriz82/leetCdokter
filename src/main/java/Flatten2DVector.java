import java.util.NoSuchElementException;

/**
 * https://leetcode.com/problems/flatten-2d-vector/
 * <p>
 * Design an iterator to flatten a 2D vector. It should support the next and hasNext operations.
 * <p>
 * Implement the Vector2D class:
 * <p>
 * Vector2D(int[][] vec) initializes the object with the 2D vector vec.
 * next() returns the next element from the 2D vector and moves the pointer one step forward. You may assume that all the calls to next are valid.
 * hasNext() returns true if there are still some elements in the vector, and false otherwise.
 * <p>
 * Input
 * ["Vector2D", "next", "next", "next", "hasNext", "hasNext", "next", "hasNext"]
 * [[[[1, 2], [3], [4]]], [], [], [], [], [], [], []]
 * Output
 * [null, 1, 2, 3, true, true, 4, false]
 * <p>
 * Explanation
 * Vector2D vector2D = new Vector2D([[1, 2], [3], [4]]);
 * vector2D.next();    // return 1
 * vector2D.next();    // return 2
 * vector2D.next();    // return 3
 * vector2D.hasNext(); // return True
 * vector2D.hasNext(); // return True
 * vector2D.next();    // return 4
 * vector2D.hasNext(); // return False
 * <p>
 * Constraints:
 * 0 <= vec.length <= 200
 * 0 <= vec[i].length <= 500
 * -500 <= vec[i][j] <= 500
 * At most 10^5 calls will be made to next and hasNext.
 * <p>
 * Follow up: As an added challenge, try to code it using only iterators in C++ or iterators in Java.
 */
public class Flatten2DVector {
    /**
     * Approach: Maintain a stack of 2d vector and the current index. Logic is exactly similar to {@link FlattenNestedListIteratorIterative} but is more simpler
     * as there is no deeper nesting involved. Since it's a 2D array, contents of a row can't be a 2D array itself, so we don't have to recurse further.
     * So we need to only maintain an outer index as well as an inner index to keep track of current element.
     * <p>
     * {@link PeekingIterator} related iterator problems
     */
    int[][] vec;
    int outerIndex = 0, innerIndex = 0;

    public Flatten2DVector(int[][] vec) {
        this.vec = vec;
    }

    public int next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        } else {
            int val = vec[outerIndex][innerIndex];
            innerIndex++;
            return val;
        }
    }

    public boolean hasNext() {
        while (outerIndex < vec.length) {
            int maxInnerIndex = vec[outerIndex].length;
            if (innerIndex == maxInnerIndex) { //if current row is done, move on to next row
                outerIndex++;
                innerIndex = 0;
            } else {
                return true;
            }
        }
        return false;
    }
}
