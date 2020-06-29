import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 * <p>
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 * <p>
 * Example:
 * <p>
 * Input: "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */
public class LetterCombinationsOfPhoneNumber {
    String[] mapping = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        ArrayList<String> res = new ArrayList<>();
        if (!digits.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            helper(res, 0, sb, digits);
        }
        return res;
    }

    private void helper(ArrayList<String> res, int offset, StringBuilder curString, String digits) {
        if (curString.length() == digits.length()) {
            res.add(curString.toString());
        } else {
            String keys = mapping[digits.charAt(offset) - '0']; //get the mapping associated with the digit present at the offset
            for (int i = 0; i < keys.length(); i++) {
                if (offset + 1 <= digits.length()) {
                    helper(res, offset + 1, curString.append(keys.charAt(i)), digits);
                    curString.setLength(curString.length() - 1);
                }
            }
        }
    }
}
