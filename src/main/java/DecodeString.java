import java.util.ArrayDeque;

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
    public String decodeString(String s) {
        ArrayDeque<Integer> multipliers = new ArrayDeque<>();
        ArrayDeque<StringBuilder> result = new ArrayDeque<>();
        int k = 0;
        result.push(new StringBuilder());
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                k = 10 * k + ch - '0';
            } else if (ch == '[') {
                multipliers.push(k);
                k = 0; // reset k after pushing
                result.push(new StringBuilder()); // mark start of new expression
            } else if (ch == ']') {
                int cnt = multipliers.pop();
                StringBuilder multipliedString = new StringBuilder();
                StringBuilder toMultiply = result.pop(); // get the current expression
                for (int j = 0; j < cnt; j++) {
                    multipliedString.append(toMultiply);
                }
                StringBuilder prefix = result.pop(); // get the previous expression
                result.push(prefix.append(multipliedString)); // concatenate them to create a new expression
            } else {
                result.push(result.pop().append(ch)); // replace the existing expression
            }
        }
        return result.pop().toString();
    }
}
