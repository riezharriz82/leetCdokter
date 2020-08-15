import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/iterator-for-combination/
 * Design an Iterator class, which has:
 * <p>
 * A constructor that takes a string characters of sorted distinct lowercase English letters and a number combinationLength as arguments.
 * A function next() that returns the next combination of length combinationLength in lexicographical order.
 * A function hasNext() that returns True if and only if there exists a next combination.
 * <p>
 * CombinationIterator iterator = new CombinationIterator("abc", 2); // creates the iterator.
 * <p>
 * iterator.next(); // returns "ab"
 * iterator.hasNext(); // returns true
 * iterator.next(); // returns "ac"
 * iterator.hasNext(); // returns true
 * iterator.next(); // returns "bc"
 * iterator.hasNext(); // returns false
 */
public class CombinationIteratorRecursive {
    private final List<String> result;
    private int index;

    //An alternative approach to solve this problem would be to leverage Integer.bitCount() to find no of set bits from 2^characters.length() - 1 till 1
    // and check whether the no of set bits equals combination length or not
    // eg. for {abc, 2}, 6 (110), 5 (101), 3 (011) are such numbers
    // the set bits indicates the indices in the characters to use  e.g. {110} indicates to use {ab} as result
    // Refer https://leetcode.com/problems/iterator-for-combination/discuss/789180/Java-or-2-different-solution-or-With-detailed-explanation
    public CombinationIteratorRecursive(String characters, int combinationLength) {
        List<String> res = new ArrayList<>();
        DFS(combinationLength, new StringBuilder(), res, 0, characters);
        this.result = res;
    }

    //Generate all the possible combinations and store it in a list
    //Add one character and recurse for the remaining substring, reduce the remaining length by 1
    public void DFS(int lengthRemaining, StringBuilder currentString, List<String> result, int index, String characters) {
        if (lengthRemaining == 0) {
            result.add(currentString.toString());
        } else {
            for (int i = index; i < characters.length(); i++) { //generate all character possibilities
                currentString.append(characters.charAt(i));
                DFS(lengthRemaining - 1, currentString, result, i + 1, characters);
                currentString.setLength(currentString.length() - 1);
            }
        }
    }

    public String next() {
        return result.get(index++);
    }

    public boolean hasNext() {
        return index < result.size();
    }
}