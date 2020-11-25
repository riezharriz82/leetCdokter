import java.util.ArrayDeque;

/**
 * https://leetcode.com/problems/basic-calculator-ii/
 * <p>
 * Implement a basic calculator to evaluate a simple expression string.
 * <p>
 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.
 * <p>
 * Input: "3+2*2"
 * Output: 7
 * <p>
 * Input: " 3/2 "
 * Output: 1
 * <p>
 * Input: " 3+5 / 2 "
 * Output: 5
 */
public class BasicCalculator2 {
    /**
     * Approach: Need to understand the preference of operators while evaluating an expression
     * Rank of * and / is greater than + or -
     * In case of operators with similar rank they need to be evaluated from left to right
     * In case a higher rank operator is seen, it needs to be evaluated first rather than the lower rank operator
     * <p>
     * In my initial implementation I used two stacks, one for operator and another for operand
     * Also used an rank map to identify the rank of the operator and to decide whether to evaluate the current operator or not.
     * My initial code did work, but it was bit long and complicated.
     * <p>
     * A simplified solution would be to always evaluate * and / whenever seen because they are the highest rank operator
     * Even if we see + after them we would have to evaluate * / first
     * Or if we see * / afterwards, then also we have to evaluate previous * / first
     * <p>
     * A gotcha is to handle - as + with -ve value assigned instead to the operand. Trick learned from wikipedia
     * It handles some edge cases introduced due to 0. Check the linked code for explanation
     * <p>
     * {@link BasicCalculator} for evaluating nested expression
     */
    public int calculate(String s) {
        s = s.replaceAll("\\s+", ""); //remove whitespaces
        if (s.isEmpty()) {
            return 0;
        }
        ArrayDeque<Integer> operands = new ArrayDeque<>();
        int number = 0;
        char previousSign = '+'; //trick to ease out the stack handling logic, if expression begins with -, it will also handle it
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                number = 10 * number + (c - '0');
            } else {
                evaluateExpression(operands, number, previousSign);
                previousSign = c;
                number = 0;
            }
        }
        //process the last number of the expression
        evaluateExpression(operands, number, previousSign);
        //evaluate the stack
        int result = 0;
        while (!operands.isEmpty()) {
            result += operands.pop();
        }
        return result;
    }

    private void evaluateExpression(ArrayDeque<Integer> operands, int number, char sign) {
        if (sign == '+') {
            operands.push(number);
        } else if (sign == '-') {
            operands.push(-number); //tricky, associate - with the operand instead of the operator to handle edge cases of 0
        } else if (sign == '*') {
            operands.push(operands.pop() * number);
        } else {
            operands.push(operands.pop() / number);
        }
    }
}
