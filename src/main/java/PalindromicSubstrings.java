/**
 * https://leetcode.com/problems/palindromic-substrings/
 * <p>
 * Given a string, your task is to count how many palindromic substrings in this string.
 * <p>
 * The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.
 * <p>
 * Input: "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 * <p>
 * Input: "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 */
public class PalindromicSubstrings {
    /**
     * Approach: Bruteforce solution is n^3. This is an n^2 solution, in which we keep each index as the center of the palindrome
     * and expand around the center for even and odd length substrings.
     * <p>
     * TODO Solve using Manacher's algorithm to reduce the time complexity to linear
     */
    public int countSubstrings(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) { //each index is considered twice as the center hence time complexity is 2*n*n
            res += expand(s, i, i); //consider odd length substrings
            res += expand(s, i, i + 1); //consider even length substrings
        }
        return res;
    }

    private int expand(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
        return count;
    }
}
