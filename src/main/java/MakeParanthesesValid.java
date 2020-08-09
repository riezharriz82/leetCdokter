import java.util.ArrayDeque;

/**
 * https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
 * <p>
 * Given a string S of '(' and ')' parentheses, we add the minimum number of parentheses ( '(' or ')', and in any positions )
 * so that the resulting parentheses string is valid.
 * <p>
 * Formally, a parentheses string is valid if and only if:
 * <p>
 * It is the empty string, or
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 * Given a parentheses string, return the minimum number of parentheses we must add to make the resulting string valid.
 * <p>
 * Input: "())"
 * Output: 1
 */
public class MakeParanthesesValid {
    // a string is valid if its balance is 0 or every prefix has positive balance
    public int minAddToMakeValidUsingBalance(String S) {
        int balance = 0, result = 0;
        for (char c : S.toCharArray()) {
            if (c == '(') {
                balance += 1;
            } else {
                balance -= 1;
            }
            if (balance == -1) {
                balance += 1;
                result++;
            }
        }
        return result + balance; // if the balance in the end is positive (((
    }

    public int minAddToMakeValid(String S) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char c : S.toCharArray()) {
            if (!stack.isEmpty()) {
                //pop out already balanced brackets
                if (c == ')' && stack.peek() == '(') {
                    stack.pop();
                    continue;
                }
            }
            stack.push(c);
        }
        return stack.size(); //for each unbalanced bracket need to add corresponding bracket
    }
}
