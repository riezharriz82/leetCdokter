import java.util.ArrayDeque;

/**
 * <pre>
 * https://leetcode.com/problems/check-if-word-is-valid-after-substitutions/
 *
 * Given a string s, determine if it is valid.
 *
 * A string s is valid if, starting with an empty string t = "", you can transform t into s after performing the following operation any number of times:
 *
 * Insert string "abc" into any position in t. More formally, t becomes tleft + "abc" + tright, where t == tleft + tright. Note that tleft and tright may be empty.
 * Return true if s is a valid string, otherwise, return false.
 *
 * Input: s = "aabcbc"
 * Output: true
 * Explanation:
 * "" -> "abc" -> "aabcbc"
 * Thus, "aabcbc" is valid.
 *
 * Input: s = "abcabcababcc"
 * Output: true
 * Explanation:
 * "" -> "abc" -> "abcabc" -> "abcabcabc" -> "abcabcababcc"
 * Thus, "abcabcababcc" is valid.
 *
 * Input: s = "abccba"
 * Output: false
 * Explanation: It is impossible to get "abccba" using the operation.
 *
 * Input: s = "cababc"
 * Output: false
 * Explanation: It is impossible to get "cababc" using the operation.
 *
 * Constraints:
 * 1 <= s.length <= 2 * 10^4
 * s consists of letters 'a', 'b', and 'c'
 * </pre>
 */
public class CheckIfAWordIsValidAfterSubstitution {
    /**
     * <pre>
     * Approach: Greedy, This problem is similar to other array transformation problems, where given an array and some transformation rules,
     * we are asked whether the original array can be transformed to target array or not. Trick to solving such problems is to generally
     * reverse the rules and try to reach the source array from target array.
     *
     * Similarly, we can try to reach "" from the given string by removing "abc" from the given string.
     * So maintain a stack and whenever we see 'c', try to check top 2 characters in the stack, if they are 'b' and 'a' pop them.
     * In the end, stack should be empty for the word to be valid.
     * </pre>
     * {@link MinimumMovesToEqualArrayElements}
     */
    public boolean isValid(String s) {
        var stack = new ArrayDeque<Character>();
        for (char c : s.toCharArray()) {
            if (stack.isEmpty()) {
                stack.push(c);
            } else if (c == 'c' && stack.size() >= 2 && stack.peek() == 'b') {
                stack.pop(); //pop 'b'
                if (stack.peek() == 'a') { //abc found
                    stack.pop(); //pop 'a'
                } else { //only 'bc' found, so now this 'bc' can never be popped, hence given string is invalid
                    //alternatively we can also push back the 'b' but it's redundant
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}
