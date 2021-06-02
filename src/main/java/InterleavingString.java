/**
 * <pre>
 * https://leetcode.com/problems/interleaving-string/
 *
 * Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
 *
 * An interleaving of two strings s and t is a configuration where they are divided into non-empty substrings such that:
 *
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
 * Note: a + b is the concatenation of strings a and b.
 *
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * Output: true
 *
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * Output: false
 *
 * Input: s1 = "", s2 = "", s3 = ""
 * Output: true
 *
 * Constraints:
 * 0 <= s1.length, s2.length <= 100
 * 0 <= s3.length <= 200
 * s1, s2, and s3 consist of lowercase English letters.
 *
 * Follow up: Could you solve it using only O(s2.length) additional memory space?
 * </pre>
 */
public class InterleavingString {
    /**
     * Approach: Recursion with memoization. Each char from s3 is either picked up from s1 or from s2.
     * An optimization to reduce time complexity from O(n^3) to O(n^2) would be to reduce no of states by not tracking redundant states.
     * Current index at s3 is always the sum of index at s1 and s2, so need to explicitly track it.
     * <p>
     * Was not able to think of this optimization on my own. {@link CherryPickup} uses similar kind of DP State optimization to reduce no of states.
     * Time Complexity: O(n^2)
     * <p>
     * {@link ShortestWayToFormString} {@link DeleteOperationForTwoString} {@link EditDistance}
     */
    public boolean isInterleaveOptimized(String s1, String s2, String s3) {
        int[][] memo = new int[s1.length()][s2.length()];
        return recur(s1, s2, s3, 0, 0, memo);
    }

    private boolean recur(String s1, String s2, String s3, int s1Index, int s2Index, int[][] memo) {
        int s3Index = s1Index + s2Index; //tricky optimization to reduce time complexity
        //s3Index will always be the sum of chars picked up from s1 and s2
        if (s1Index == s1.length() && s2Index == s2.length()) { //if chars at both s1 and s2 are completely exhausted
            return s3Index == s3.length();
        } else if (s1Index == s1.length()) { //if chars at s1 are exhausted, check whether remaining substring of s2 matches the remaining substring of s3
            return s3.substring(s3Index).equals(s2.substring(s2Index));
        } else if (s2Index == s2.length()) { //if chars at s2 are exhausted, check whether remaining substring of s1 matches the remaining substring of s3
            return s3.substring(s3Index).equals(s1.substring(s1Index));
        } else if (s3Index == s3.length()) { //if chars of s3 are exhausted, but either s1 or s2 is still left
            return false;
        } else if (memo[s1Index][s2Index] != 0) {
            return memo[s1Index][s2Index] == 1;
        }
        char s1Char = s1.charAt(s1Index);
        char s2Char = s2.charAt(s2Index);
        char s3Char = s3.charAt(s3Index);
        boolean result;
        if (s1Char == s2Char && s2Char == s3Char) {
            //try incrementing either the index at s1 or the index at s2
            result = recur(s1, s2, s3, s1Index + 1, s2Index, memo) || recur(s1, s2, s3, s1Index, s2Index + 1, memo);
        } else if (s1Char == s3Char) {
            result = recur(s1, s2, s3, s1Index + 1, s2Index, memo); //increment the index at s1
        } else if (s2Char == s3Char) {
            result = recur(s1, s2, s3, s1Index, s2Index + 1, memo); //increment the index at s2
        } else {
            //none of the chars at s1 or s2 match the current char at s3
            result = false;
        }
        memo[s1Index][s2Index] = result ? 1 : -1;
        return result;
    }

    /**
     * Approach: Recursion with memoization, every char of s3 can either be from s1 or from s2. Need to check both combinations and recur accordingly.
     * Don't forget to consider out of bounds edge cases.
     * This was my original solution.
     * TimeComplexity: O(n^3) because of keeping s3Index as the third state.
     * <p>
     */
    public boolean isInterleave3DP(String s1, String s2, String s3) {
        int[][][] memo = new int[s1.length()][s2.length()][s3.length()];
        return recur(s1, s2, s3, 0, 0, 0, memo);
    }

    private boolean recur(String s1, String s2, String s3, int s1Index, int s2Index, int s3Index, int[][][] memo) {
        if (s1Index == s1.length() && s2Index == s2.length()) { //if chars at both s1 and s2 are completely exhausted
            return s3Index == s3.length();
        } else if (s1Index == s1.length()) { //if chars at s1 are exhausted, check whether remaining substring of s2 matches the remaining substring of s3
            return s3.substring(s3Index).equals(s2.substring(s2Index));
        } else if (s2Index == s2.length()) { //if chars at s2 are exhausted, check whether remaining substring of s1 matches the remaining substring of s3
            return s3.substring(s3Index).equals(s1.substring(s1Index));
        } else if (s3Index == s3.length()) { //if chars of s3 are exhausted, but either s1 or s2 is still left
            return false;
        } else if (memo[s1Index][s2Index][s3Index] != 0) {
            return memo[s1Index][s2Index][s3Index] == 1;
        }
        char s1Char = s1.charAt(s1Index);
        char s2Char = s2.charAt(s2Index);
        char s3Char = s3.charAt(s3Index);
        boolean result;
        if (s1Char == s2Char && s2Char == s3Char) {
            //try incrementing either the index at s1 or the index at s2
            result = recur(s1, s2, s3, s1Index + 1, s2Index, s3Index + 1, memo) || recur(s1, s2, s3, s1Index, s2Index + 1, s3Index + 1, memo);
        } else if (s1Char == s3Char) {
            result = recur(s1, s2, s3, s1Index + 1, s2Index, s3Index + 1, memo); //increment the index at s1
        } else if (s2Char == s3Char) {
            result = recur(s1, s2, s3, s1Index, s2Index + 1, s3Index + 1, memo); //increment the index at s2
        } else {
            //none of the chars at s1 or s2 match the current char at s3
            result = false;
        }
        memo[s1Index][s2Index][s3Index] = result ? 1 : -1;
        return result;
    }
}
