import java.util.ArrayDeque;

/**
 * https://leetcode.com/problems/score-of-parentheses/
 * <p>
 * Given a balanced parentheses string S, compute the score of the string based on the following rule:
 * <p>
 * () has score 1
 * AB has score A + B, where A and B are balanced parentheses strings.
 * (A) has score 2 * A, where A is a balanced parentheses string.
 * <p>
 * Input: "()"
 * Output: 1
 * <p>
 * Input: "(())"
 * Output: 2
 * <p>
 * Input: "()()"
 * Output: 2
 * <p>
 * Input: "(()(()))"
 * Output: 6
 * <p>
 * Note:
 * S is a balanced parentheses string, containing only ( and ).
 * 2 <= S.length <= 50
 */
public class ScoreOfParentheses {
    /**
     * Approach: Use stack to solve nested brackets related problem
     * <p>
     * Whenever ) is seen, pop all elements until ( is observed, inner elements should be doubled and pushed back to stack
     * If inner sum is 0, then push 1 else push 2 * inner sum
     * <p>
     * {@link ReverseSubstringsBetweenEachPairOfParentheses} {@link LongestValidParentheses} {@link DecodeString} {@link NumberOfAtoms}
     */
    public int scoreOfParentheses(String S) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char c : S.toCharArray()) {
            if (c == ')') {
                int poppedSum = 0;
                while (!stack.isEmpty() && stack.peek() != '(') {
                    //keep track of total inner sum
                    poppedSum += (stack.pop() - '0');
                }
                stack.pop(); //pop the '('
                poppedSum *= 2; //double the inner sum
                //if popped sum is 0, this means the expression is (), hence we need to push 1
                stack.push(poppedSum == 0 ? '1' : (char) (poppedSum + '0'));
            } else {
                stack.push('(');
            }
        }
        int totalSum = 0;
        while (!stack.isEmpty()) {
            totalSum += (stack.pop() - '0');
        }
        return totalSum;
    }
}
