import java.util.ArrayDeque;

/**
 * https://leetcode.com/problems/longest-valid-parentheses/
 * <p>
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 * <p>
 * Input: s = "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()".
 * <p>
 * Input: s = ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()".
 */
public class LongestValidParentheses {
    /**
     * Approach: Initially I thought of using sliding window to find longest valid substring but it didn't pan out
     * Then I thought of using nested stack solution similar to {@link NumberOfAtoms} but thought it was an overkill
     * Also thought of using prefix sum technique to find longest valid subarray with repeating sum but it gave WA,
     * because I couldn't carry forward the results
     * <p>
     * Lastly I thought of leveraging stack to mark indices that are balanced/valid. The unbalanced indices will remain unmarked.
     * Then the problem would reduce to finding the longest subarray with all valid indices.
     * <p>
     * {@link AddBoldTagInString}
     */
    public int longestValidParentheses(String s) {
        boolean[] isBalanced = new boolean[s.length()];
        ArrayDeque<Integer> stack = new ArrayDeque<>(); //stack of indices
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else if (c == ')' && !stack.isEmpty() && s.charAt(stack.peek()) == '(') {
                //mark the corresponding index as balanced
                isBalanced[i] = true;
                isBalanced[stack.peek()] = true;
                stack.pop();
            }
        }
        int index = 0, maxDiff = 0;
        //find the longest subarray with all valid index
        while (index < s.length()) {
            if (isBalanced[index]) {
                int temp = index;
                while (temp < s.length() && isBalanced[temp]) {
                    temp++;
                }
                maxDiff = Math.max(maxDiff, temp - index);
                index = temp;
            } else {
                index++;
            }
        }
        return maxDiff;
    }
}
