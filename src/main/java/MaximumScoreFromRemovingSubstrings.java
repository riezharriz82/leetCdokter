import java.util.ArrayDeque;

/**
 * https://leetcode.com/problems/maximum-score-from-removing-substrings/
 * <p>
 * You are given a string s and two integers x and y. You can perform two types of operations any number of times.
 * <p>
 * Remove substring "ab" and gain x points.
 * For example, when removing "ab" from "cabxbae" it becomes "cxbae".
 * Remove substring "ba" and gain y points.
 * For example, when removing "ba" from "cabxbae" it becomes "cabxe".
 * Return the maximum points you can gain after applying the above operations on s.
 * <p>
 * Input: s = "cdbcbbaaabab", x = 4, y = 5
 * Output: 19
 * Explanation:
 * - Remove the "ba" underlined in "cdbcbbaaabab". Now, s = "cdbcbbaaab" and 5 points are added to the score.
 * - Remove the "ab" underlined in "cdbcbbaaab". Now, s = "cdbcbbaa" and 4 points are added to the score.
 * - Remove the "ba" underlined in "cdbcbbaa". Now, s = "cdbcba" and 5 points are added to the score.
 * - Remove the "ba" underlined in "cdbcba". Now, s = "cdbc" and 5 points are added to the score.
 * Total score = 5 + 4 + 5 + 5 = 19.
 * <p>
 * Input: s = "aabbaaxybbaabb", x = 5, y = 4
 * Output: 20
 * <p>
 * Constraints:
 * 1 <= s.length <= 105
 * 1 <= x, y <= 104
 * s consists of lowercase English letters.
 */
public class MaximumScoreFromRemovingSubstrings {
    /**
     * Approach: Greedy. First try to remove pattern with max profit, then try to remove pattern with lower profit
     * Initially I thought of using recursive DP but then realized the no of states would be too much (startIndex, endIndex)
     * There would be n^2 states and transition time would be O(n) leading to n^3 time complexity
     * <p>
     * Given the input constraints, only greedy was feasible. I am amazed to solve this question on my own in <30 minutes
     * <p>
     * {@link CarFleet} {@link BoatsToSavePeople} related tricky greedy problems
     */
    public int maximumGain(String s, int x, int y) {
        boolean lookForAB = true;
        if (x < y) {
            lookForAB = false;
        }
        //if lookForAB is true, then pop "ab" otherwise pop "ba"
        int profit = 0;
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (stack.isEmpty()) {
                stack.push(c);
            } else if (c == 'b' && stack.peek() == 'a' && lookForAB) { //if we need to pop "ab"
                stack.pop();
                profit += x; //add the profit for popping ab
            } else if (c == 'a' && stack.peek() == 'b' && !lookForAB) { //if we need to pop "ba"
                stack.pop();
                profit += y; //add profit for popping ba
            } else {
                stack.push(c);
            }
        }
        //after popping the most profitable pattern, pop pattern with lower profit
        //can easily extract out a function to reduce duplicate code
        lookForAB = !lookForAB;
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        s = sb.reverse().toString(); //s is now the string remaining after popping
        //repeat the same code
        for (char c : s.toCharArray()) {
            if (stack.isEmpty()) {
                stack.push(c);
            } else if (c == 'b' && stack.peek() == 'a' && lookForAB) {
                stack.pop();
                profit += x;
            } else if (c == 'a' && stack.peek() == 'b' && !lookForAB) {
                stack.pop();
                profit += y;
            } else {
                stack.push(c);
            }
        }
        //initially i thought of repeatedly doing this in a while loop until we can pop at least one pattern in each loop
        //but then realized that we can never pop a pattern in the second iteration of the loop because if there was such
        //pattern, it would have been already popped
        return profit;
    }
}
