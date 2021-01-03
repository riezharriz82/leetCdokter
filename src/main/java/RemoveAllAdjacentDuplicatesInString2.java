import javafx.util.Pair;

import java.util.ArrayDeque;

/**
 * https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/
 * <p>
 * Given a string s, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them
 * causing the left and the right side of the deleted substring to concatenate together.
 * <p>
 * We repeatedly make k duplicate removals on s until we no longer can.
 * <p>
 * Return the final string after all such duplicate removals have been made.
 * <p>
 * It is guaranteed that the answer is unique.
 * <p>
 * Input: s = "abcd", k = 2
 * Output: "abcd"
 * Explanation: There's nothing to delete.
 * <p>
 * Input: s = "deeedbbcccbdaa", k = 3
 * Output: "aa"
 * Explanation:
 * First delete "eee" and "ccc", get "ddbbbdaa"
 * Then delete "bbb", get "dddaa"
 * Finally delete "ddd", get "aa"
 * <p>
 * Input: s = "pbbcggttciiippooaais", k = 2
 * Output: "ps"
 */
public class RemoveAllAdjacentDuplicatesInString2 {
    /**
     * Approach: Use stack to simulate deletion of k adjacent duplicates. To know when to pop from stack, keep count of consecutive
     * characters with each element.
     * <p>
     * SideNote: Instead of stack, we could have also used linkedlist, which would have been a bit faster because it does not have to resize
     * <p>
     * {@link SmallestStringWithSwaps} {@link SmallestSubsequenceOfDistinctCharacters}
     */
    public String removeDuplicates(String s, int k) {
        ArrayDeque<Pair<Character, Integer>> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (stack.isEmpty() || stack.peek().getKey() != c) {
                stack.push(new Pair<>(c, 1));
            } else {
                if (stack.peek().getValue() + 1 == k) { //if this element is the kth adjacent duplicate character, pop all the duplicates
                    while (!stack.isEmpty() && stack.peek().getKey() == c) {
                        stack.pop();
                    }
                } else {
                    //increment the count and push it to the stack
                    stack.push(new Pair<>(c, stack.peek().getValue() + 1));
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop().getKey());
        }
        return sb.reverse().toString();
    }
}
