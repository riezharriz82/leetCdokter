import java.util.ArrayDeque;

/**
 * https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/
 * <p>
 * You are given a string s that consists of lower case English letters and brackets.
 * <p>
 * Reverse the strings in each pair of matching parentheses, starting from the innermost one.
 * <p>
 * Your result should not contain any brackets.
 * <p>
 * Input: s = "(abcd)"
 * Output: "dcba"
 * <p>
 * Input: s = "(u(love)i)"
 * Output: "iloveu"
 * Explanation: The substring "love" is reversed first, then the whole string is reversed.
 * <p>
 * Input: s = "(ed(et(oc))el)"
 * Output: "leetcode"
 * Explanation: First, we reverse the substring "oc", then "etco", and finally, the whole string.
 */
public class ReverseSubstringsBetweenEachPairOfParentheses {
    /**
     * Approach: Similar to {@link DecodeString} {@link NumberOfAtoms}
     * We need to only reverse strings between parentheses, so start a new expression upon seeing (
     * Keep adding to the current expression when a character is encountered
     * Upon seeing a ) need to reverse the current expression and merge it with the previous expression
     */
    public String reverseParentheses(String s) {
        ArrayDeque<String> stack = new ArrayDeque<>();
        stack.push("");
        int index = 0;
        while (index < s.length()) {
            char c = s.charAt(index);
            if (c == '(') {
                //start a new expression
                stack.push("");
                index++;
            } else if (c == ')') {
                //reverse the current expression and merge with the previous expression
                String currentExpression = stack.pop();
                char[] chars = currentExpression.toCharArray();
                int begin = 0, end = chars.length - 1;
                while (begin < end) {
                    char temp = chars[begin];
                    chars[begin] = chars[end];
                    chars[end] = temp;
                    begin++;
                    end--;
                }
                String previousExpression = stack.pop();
                stack.push(previousExpression + new String(chars));
                index++;
            } else {
                //update the current expression
                String previousExpression = stack.pop();
                stack.push(previousExpression + c);
                index++;
            }
        }
        return stack.pop();
    }
}
