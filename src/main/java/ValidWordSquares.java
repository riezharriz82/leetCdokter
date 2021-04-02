import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/valid-word-square/
 * <p>
 * Given a sequence of words, check whether it forms a valid word square.
 * <p>
 * A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).
 * <p>
 * Note:
 * The number of words given is at least 1 and does not exceed 500.
 * Word length will be at least 1 and does not exceed 500.
 * Each word contains only lowercase English alphabet a-z.
 * <pre>
 * Input:
 * [
 *   "abcd",
 *   "bnrt",
 *   "crmy",
 *   "dtye"
 * ]
 *
 * Output:
 * true
 *
 * Explanation:
 * The first row and first column both read "abcd".
 * The second row and second column both read "bnrt".
 * The third row and third column both read "crmy".
 * The fourth row and fourth column both read "dtye".
 * Therefore, it is a valid word square.
 *
 * Input:
 * [
 *   "abcd",
 *   "bnrt",
 *   "crm",
 *   "dt"
 * ]
 *
 * Output:
 * true
 *
 * Explanation:
 * The first row and first column both read "abcd".
 * The second row and second column both read "bnrt".
 * The third row and third column both read "crm".
 * The fourth row and fourth column both read "dt".
 * Therefore, it is a valid word square.
 *
 * Input:
 * [
 *   "ball",
 *   "area",
 *   "read",
 *   "lady"
 * ]
 *
 * Output:
 * false
 *
 * Explanation:
 * The third row reads "read" while the third column reads "lead".
 * Therefore, it is NOT a valid word square.
 * </pre>
 */
public class ValidWordSquares {
    /**
     * Approach: The problem statement is asking for whether all rows are equivalent to its column or not. This is similar to finding transpose of a matrix.
     * (i,j) in original matrix would be represented as (j,i) in transposed matrix. So leveraging this fact, for each index, we can check whether the
     * transposed matrix location contains the same character or not.
     * <p>
     * This approach does not require any extra space and will immediately exit in case of a mismatch.
     * <p>
     * {@link LuckyNumbersInAMatrix}
     */
    public boolean validWordSquareSimplified(List<String> words) {
        for (int row = 0; row < words.size(); row++) {
            String word = words.get(row);
            for (int column = 0; column < word.length(); column++) {
                char current = word.charAt(column);
                //bound conditions ensure that we are not going out of bounds
                if (column >= words.size() || words.get(column).length() <= row || words.get(column).charAt(row) != current) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Approach: For each character, add the character to a reverse mapping of it's respective row and column
     * Then do a second pass of the final string stored at each row and column and check whether the string matches or not.
     * Used map instead of an array because the length of words are dynamic.
     * <p>
     * This approach requires extra space.
     */
    public boolean validWordSquareExtraSpace(List<String> words) {
        Map<Integer, StringBuilder> rows = new HashMap<>();
        Map<Integer, StringBuilder> cols = new HashMap<>();
        int maxIndex = 0;
        for (int row = 0; row < words.size(); row++) {
            String word = words.get(row);
            for (int col = 0; col < word.length(); col++) {
                //append the character to it's specific row and column
                rows.computeIfAbsent(row, __ -> new StringBuilder()).append(word.charAt(col));
                cols.computeIfAbsent(col, __ -> new StringBuilder()).append(word.charAt(col));
                maxIndex = Math.max(maxIndex, col);
            }
            maxIndex = Math.max(maxIndex, row);
        }
        for (int i = 0; i < maxIndex; i++) {
            //verify each row with its corresponding column
            String row = rows.getOrDefault(i, new StringBuilder()).toString();
            String col = cols.getOrDefault(i, new StringBuilder()).toString();
            if (!row.equals(col)) {
                return false;
            }
        }
        return true;
    }
}
