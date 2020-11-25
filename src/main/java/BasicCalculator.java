import java.util.ArrayDeque;

/**
 * https://leetcode.com/problems/basic-calculator/
 * <p>
 * Implement a basic calculator to evaluate a simple expression string.
 * <p>
 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
 * <p>
 * Input: "1 + 1"
 * Output: 2
 * <p>
 * Input: " 2-1 + 2 "
 * Output: 3
 * <p>
 * Input: "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 */
public class BasicCalculator {
    /**
     * Approach: Nested stack solution similar to {@link NumberOfAtoms} {@link DecodeString}
     * <p>
     * A tip to solve nested string questions is to first solve expression without nesting ie. first solve 2 + 3 + 5
     * It requires a stack of integer. (It can be done without stack as well, keeping track of result found so far)
     * <p>
     * Now given a nested expression you would need stack of whatever was required to solve single expression i.e stack of stack
     * Keep track of any prefix/suffix required for the nested expression. In NumberOfAtoms we were dealing with positive suffix
     * Here we are dealing with positive/negative prefix ie. signs
     * <p>
     * 2 - 3, is evaluated to 2 + (-3) to make life easier and handles expression like 2 - 0 - 26 correctly
     * If - would have been evaluated separately, stack of operands/operator would be
     * 26
     * 0  -
     * 2  -
     * 0 - 26 = -26, 2 - (-26) = 28
     * That gives wrong answer
     * {@link BasicCalculator2} for problem without nested expression
     */
    public int calculate(String s) {
        s = s.replaceAll("\\s+", "");
        int number = 0;
        boolean isNegative = false;
        ArrayDeque<ArrayDeque<Integer>> stack = new ArrayDeque<>(); //stack of stack
        ArrayDeque<Boolean> isPositive = new ArrayDeque<>(); //stack of prefix signs
        stack.push(new ArrayDeque<>()); //push an empty stack at the start of the expression
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                number = 10 * number + (c - '0');
            } else if (c == '(') {
                isPositive.push(!isNegative);
                isNegative = false; //important to reset the sign
                stack.push(new ArrayDeque<>()); //push a new empty stack at the start of the new expression
            } else if (c == ')') {
                if (isNegative) {
                    isNegative = false;
                    number *= -1;
                }
                stack.peek().push(number); //dont forget to push the last number in the current expression
                number = 0; //reset the number
                ArrayDeque<Integer> currentExpression = stack.pop();
                int result = 0;
                while (!currentExpression.isEmpty()) { //evaluate the current expression
                    result += currentExpression.pop();
                }
                boolean isCurrentExpressionPositive = isPositive.pop();
                if (!isCurrentExpressionPositive) { //check the prefix sign of the current expression
                    result *= -1;
                }
                stack.peek().push(result); //merge the result of the current expression with previous expression
            } else {
                if (isNegative) {
                    number *= -1;
                    isNegative = false;
                }
                stack.peek().push(number);
                number = 0;
                if (c == '-') {
                    isNegative = true;
                }
            }
        }
        if (isNegative) {
            number *= -1;
        }
        stack.peek().push(number); //dont forget to push the last number of the current expression
        ArrayDeque<Integer> lastExpression = stack.pop();
        int result = 0;
        while (!lastExpression.isEmpty()) { //evaluate the single expression remaining
            result += lastExpression.pop();
        }
        //no need to worry about the prefix sign of the last expression remaining because it will be always positive
        //if it had been -ve, it would be "-(3*5)" so we would have computed second expression and would have associated -
        //with the second expression
        return result;
    }
}
