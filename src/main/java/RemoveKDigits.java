import java.util.ArrayDeque;

/**
 * https://leetcode.com/problems/remove-k-digits/
 * <p>
 * Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.
 * <p>
 * Note:
 * The length of num is less than 10002 and will be ≥ k.
 * The given num does not contain any leading zero.
 * <p>
 * Input: num = "1432219", k = 3
 * Output: "1219"
 * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
 * <p>
 * Input: num = "10200", k = 1
 * Output: "200"
 * Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
 * <p>
 * Input: num = "10", k = 2
 * Output: "0"
 * Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 */
public class RemoveKDigits {
    /**
     * Approach: Greedy, Removal of characters will lead to a subsequence of the number, so we can reframe the question
     * as find lexicographically smallest subsequence after removing k characters from the string
     * <p>
     * We keep adding characters to a stack and pop from it only when we see a smaller character. Pop here signifies skipping
     * that character in the result because in subsequence we have two options, either pick the character or skip it.
     * If we see a smaller character to the right, it makes sense to try to start subsequence from it, rather than current character
     * <p>
     * {@link SmallestSubsequenceOfDistinctCharacters} {@link FindTheMostCompetitiveSubsequence} {@link LongestSubstringWithAtLeastKRepeatingCharacters}
     */
    public String removeKdigits(String num, int k) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char c : num.toCharArray()) {
            while (!stack.isEmpty() && stack.peek() > c && k > 0) {
                //skip characters that are greater than current char and if within limits k > 0
                stack.pop();
                k--;
            }
            stack.push(c);
        }
        //if stack size > k, trim the characters falling out of limits e.g if stack is {1,2,3,4} and k=2, result is {1,2}
        while (k > 0) {
            stack.pop();
            k--;
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.append(stack.pop());
        }
        //remove leading 0's and make sure to return "0" if the trimmed substring is empty
        String candidate = res.reverse().toString();
        int index = 0;
        while (index < candidate.length() && candidate.charAt(index) == '0') {
            index++;
        }
        return candidate.substring(index).isEmpty() ? "0" : candidate.substring(index);
    }
}
