import java.util.Stack;

/**
 * https://leetcode.com/problems/valid-parentheses/
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * <p>
 * An input string is valid if:
 * <p>
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 */
public class ValidParentheses {

    public boolean isValid(String s) {
        if (s.length() == 0)
            return true;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char val = s.charAt(i);
            if (val == '(' || val == '{' || val == '[')
                stack.push(val);
            else if (val == ')' || val == '}' || val == ']') {
                //get the top of the stack and check if it's of the corresponding pair
                if (stack.empty())
                    return false;
                char top = stack.peek();
                if (val == ')' && top != '(')
                    return false;
                if (val == ']' && top != '[')
                    return false;
                if (val == '}' && top != '{')
                    return false;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
