import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * https://leetcode.com/problems/decode-string/
 * <p>
 * Given an encoded string, return its decoded string.
 * <p>
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.
 * Note that k is guaranteed to be a positive integer.
 * <p>
 * You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
 * <p>
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k.
 * For example, there won't be input like 3a or 2[4].
 * <p>
 * Example 1: Input: s = "3[a]2[bc]"
 * Output: "aaabcbc"
 * <p>
 * Example 2: Input: s = "3[a2[c]]"
 * Output: "accaccacc"
 * <p>
 * Example 3: Input: s = "2[abc]3[cd]ef"
 * Output: "abcabccdcdcdef"
 */
public class DecodeString {
    /**
     * Approach: Since this question has string parsing and parenthesis involved, we are going to use stack to coalesce partitions
     * <p>
     * It's not guaranteed that a partition be preceded by a number ie. "abcde3[aa]" is a valid input
     * <p>
     * In stack questions, we typically take actions, when we see a closing bracket, we are going to do the same thing here.
     * Keep pushing characters until we see a ']' Then start popping characters until we see '['
     * Then start popping characters until is not a digit to extract out the multiplier.
     * <p>
     * Multiply the string found in between the brackets and push it back to the stack. In case there are nested brackets, stack will take care of it.
     * Result would be characters left in the stack.
     */
    public String decodeString(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ']') {
                LinkedList<Character> currentWord = new LinkedList<>();
                //linkedlist because the current word is present in reverse in stack, so if arraylist, i have to reverse it explicitly.
                while (stack.peek() != '[') {
                    currentWord.addFirst(stack.pop());
                }
                stack.pop(); //Pop the '['
                int multiplier = 0;
                int power = 0;
                while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
                    multiplier += (Math.pow(10, power) * (stack.pop() - '0')); //digit is in reverse, so create the number accordingly
                    power++;
                }
                while (multiplier > 0) { //multiply the currentWord and push it back to the stack
                    for (char c : currentWord) {
                        stack.push(c);
                    }
                    multiplier--;
                }
            } else {
                stack.push(s.charAt(i));
            }
        }
        char[] result = new char[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) { //create the result from the characters present in the stack.
            result[i] = stack.pop();
        }
        return new String(result);
    }

    /**
     * Approach: Initially I thought that this solution of starting a new expression upon seeing a opening bracket is too hacky and I deleted that solution
     * But after solving {@link NumberOfAtoms} I realized that it's a pretty generic way of parsing nested expressions.
     * <p>
     * Keep updating the current expression as long as we are on the current expression. If an opening bracket is reached,
     * start a new expression and start updating it.
     * <p>
     * When closing bracket is reached, evaluate the current expression by multiplying and then merge it with the previous expression.
     */
    public String decodeStringAlternate(String s) {
        Stack<Integer> multiplierStack = new Stack<>();
        Stack<String> expressionStack = new Stack<>();
        expressionStack.push("");
        int index = 0;
        while (index < s.length()) {
            char c = s.charAt(index);
            if (Character.isDigit(c)) {
                int multiplier = 0;
                //keep iterating until we get all the digits
                while (index < s.length() && Character.isDigit(s.charAt(index))) {
                    multiplier = 10 * multiplier + (s.charAt(index) - '0');
                    index++;
                }
                multiplierStack.push(multiplier);
            } else if ('(' == c) {
                //start of a new expression
                expressionStack.push("");
                index++;
            } else if (')' == c) {
                //evaluate the current expression and merge with previous expression
                String currentExpression = expressionStack.pop();
                int currentMultiplier = multiplierStack.pop();
                String multipled = "";
                for (int i = 0; i < currentMultiplier; i++) {
                    multipled += currentExpression;
                }
                //merge with previous expression to leverage nested multiplication
                String previousExpression = expressionStack.pop();
                expressionStack.push(previousExpression + multipled);
                index++;
            } else {
                //update the current expression
                String currentExpression = expressionStack.pop();
                currentExpression += c;
                expressionStack.push(currentExpression);
                index++;
            }
        }
        return expressionStack.pop();
    }
}
