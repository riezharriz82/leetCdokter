import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/generate-parentheses/
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * <p>
 * For example, given n = 3, a solution set is:
 * <pre>
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 * </pre>
 */
public class GenerateBrackets {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        helper(0, 0, n, res, new StringBuilder());
        return res;
    }

    private void helper(int curOpen, int curClose, int maxOpen, List<String> res, StringBuilder curAnswer) {
        if (curOpen > maxOpen || curClose > curOpen) {
            return;
        } else if (curOpen + curClose == 2 * maxOpen) {
            res.add(curAnswer.toString());
        } else {
            helper(curOpen + 1, curClose, maxOpen, res, curAnswer.append("("));
            curAnswer.setLength(curAnswer.length() - 1);
            helper(curOpen, curClose + 1, maxOpen, res, curAnswer.append(")"));
            curAnswer.setLength(curAnswer.length() - 1);
        }
    }
}
