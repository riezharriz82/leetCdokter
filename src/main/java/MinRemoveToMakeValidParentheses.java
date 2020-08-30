import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.HashSet;

/**
 * https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
 * <p>
 * Given a string s of '(' , ')' and lowercase English characters.
 * <p>
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string
 * is valid and return any valid string.
 * <p>
 * Formally, a parentheses string is valid if and only if:
 * <p>
 * It is the empty string, contains only lowercase characters, or
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 * <p>
 * Input: s = "lee(t(c)o)de)"
 * Output: "lee(t(c)o)de"
 * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 */
public class MinRemoveToMakeValidParentheses {

    /**
     * Approach: Maintain a stack of ( and ) and pop out closing brackets.
     * Keep track of the unbalanced indices at the end.
     * Reiterate the array and check if the index is not unbalanced.
     * {@link MakeParanthesesValid} is similar problem
     */
    public String minRemoveToMakeValid(String s) {
        ArrayDeque<Pair<Character, Integer>> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ')' || c == '(') {
                if (!stack.isEmpty() && c == ')' && stack.peek().getKey() == '(') {
                    stack.pop();
                } else {
                    stack.push(new Pair<>(c, i));
                }
            }
        }
        HashSet<Integer> set = new HashSet<>();
        while (!stack.isEmpty()) {
            set.add(stack.pop().getValue());
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!set.contains(i)) {
                result.append(s.charAt(i));
            }
        }
        return result.toString();
    }
}
