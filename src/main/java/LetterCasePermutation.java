import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/letter-case-permutation/
 * <p>
 * Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.
 * <p>
 * Return a list of all possible strings we could create. You can return the output in any order.
 * <p>
 * Input: S = "a1b2"
 * Output: ["a1b2","a1B2","A1b2","A1B2"]
 * <p>
 * Input: S = "3z4"
 * Output: ["3z4","3Z4"]
 * <p>
 * Input: S = "12345"
 * Output: ["12345"]
 */
public class LetterCasePermutation {
    /**
     * Approach: Process each char independently
     * {@link GeneralizedAbbreviations} similar problem
     */
    public List<String> letterCasePermutation(String S) {
        ArrayList<String> result = new ArrayList<>();
        recur(result, S, 0, "");
        return result;
    }

    private void recur(ArrayList<String> result, String s, int idx, String candidate) {
        if (idx == s.length()) {
            result.add(candidate);
        } else {
            if (Character.isDigit(s.charAt(idx))) {
                recur(result, s, idx + 1, candidate + s.charAt(idx));
            } else {
                recur(result, s, idx + 1, candidate + Character.toLowerCase(s.charAt(idx)));
                recur(result, s, idx + 1, candidate + Character.toUpperCase(s.charAt(idx)));
            }
        }
    }
}
