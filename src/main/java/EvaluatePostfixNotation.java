import java.util.ArrayDeque;

/**
 * https://leetcode.com/problems/evaluate-reverse-polish-notation/
 * <p>
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * <p>
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 * <p>
 * Division between two integers should truncate toward zero.
 * The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.
 * <p>
 * Input: ["2", "1", "+", "3", "*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 */
public class EvaluatePostfixNotation {
    /**
     * Approach: Postfix notation does not have brackets and can be evaluated by using stack.
     * When an operator is seen, pop two elements from the stack and evaluate them by applying the operator.
     * Finally stack would contain only element and that would be the result
     */
    public int evalRPN(String[] tokens) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (String token : tokens) {
            if (token.equals("+") || token.equals("*") || token.equals("-") || token.equals("/")) {
                int second = stack.pop();
                int first = stack.pop();
                int val;
                switch (token) { //evaluate the operands
                    case "+":
                        val = second + first;
                        break;
                    case "-":
                        val = first - second;
                        break;
                    case "*":
                        val = first * second;
                        break;
                    default:
                        val = first / second;
                        break;
                }
                stack.push(val); //push the result back on to the stack for future operations
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }
}
