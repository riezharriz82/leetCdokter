/**
 * <pre>
 * https://leetcode.com/problems/split-a-string-in-balanced-strings/
 *
 * Balanced strings are those that have an equal quantity of 'L' and 'R' characters.
 *
 * Given a balanced string s, split it in the maximum amount of balanced strings.
 *
 * Return the maximum amount of split balanced strings.
 *
 * Input: s = "RLRRLLRLRL"
 * Output: 4
 * Explanation: s can be split into "RL", "RRLL", "RL", "RL", each substring contains same number of 'L' and 'R'.
 *
 * Input: s = "RLLLLRRRLR"
 * Output: 3
 * Explanation: s can be split into "RL", "LLLRRR", "LR", each substring contains same number of 'L' and 'R'.
 *
 * Input: s = "LLLLRRRR"
 * Output: 1
 * Explanation: s can be split into "LLLLRRRR".
 *
 * Input: s = "RLRRRLLRLL"
 * Output: 2
 * Explanation: s can be split into "RL", "RRRLLRLL", since each substring contains an equal number of 'L' and 'R'
 *
 * Constraints:
 * 1 <= s.length <= 1000
 * s[i] is either 'L' or 'R'.
 * s is a balanced string.
 * </pre>
 */
public class SplitAStringInBalancedStrings {
    /**
     * Approach: Greedy, convert L and R into ( and ) respectively. So the problem reduces to splitting a string that results in max number of balanced parentheses.
     * Max number of balanced parentheses will only result if we cut at the smallest length possible.
     * If we assign -1 and 1 to ( and ), the moment the running sum becomes 0, current substring will become balanced in the smallest length possible, so we should make a cut.
     * <p>
     * {@link MinimumDeletionsToMakeStringBalanced} {@link GenerateBrackets} {@link LongestValidParentheses} {@link AddBoldTagInString} {@link EraseNonOverlappingIntervals}
     */
    public int balancedStringSplit(String s) {
        int res = 0, curSum = 0;
        for (char c : s.toCharArray()) {
            if (c == 'L') {
                curSum--;
            } else {
                curSum++;
            }
            if (curSum == 0) { //current substring is balanced, make a cut
                res++;
            }
        }
        return res;
    }
}
