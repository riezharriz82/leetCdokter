import javafx.util.Pair;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/longest-palindromic-substring/
 * <p>
 * Given a string s, return the longest palindromic substring in s.
 * <p>
 * Input: s = "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * <p>
 * Input: s = "cbbd"
 * Output: "bb"
 */
public class LongestPalindromicSubstring {
    int res = 0;
    Pair<Integer, Integer> bounds;

    /**
     * Approach: Expand around the center similar to {@link PalindromicSubstrings}
     * Time complexity is n^2
     */
    public String longestPalindrome(String s) {
        int res = Integer.MIN_VALUE;
        Pair<Integer, Integer> bounds = null;
        for (int i = 0; i < s.length(); i++) {
            Pair<Integer, Integer> opt1 = expand(s, i, i); //consider odd length palindromic substring centered at ith index
            Pair<Integer, Integer> opt2 = expand(s, i, i + 1); //consider even length palindromic substring centered at i and i+1 index
            //can simplify the code a bit by using global variables and updating the maxLength directly in expand() function
            int len1 = opt1.getValue() - opt1.getKey() + 1;
            int len2 = opt2.getValue() - opt2.getKey() + 1;
            if (len1 > len2 && len1 > res) {
                res = len1;
                bounds = opt1;
            } else if (len2 > len1 && len2 > res) {
                res = len2;
                bounds = opt2;
            }
        }
        return s.substring(bounds.getKey(), bounds.getValue() + 1);
    }

    private Pair<Integer, Integer> expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return new Pair<>(left + 1, right - 1);
    }

    /**
     * Approach: Top down with memoization, if s[left] == s[right], recur for [left+1, right-1] and check if it's palindrome
     * If it's palindrome, then only update the current candidate length
     */
    public String longestPalindromeTopDown(String s) {
        int[][] memoized = new int[s.length()][s.length()];
        for (int[] ints : memoized) {
            Arrays.fill(ints, -1);
        }
        recur(s, 0, s.length() - 1, memoized);
        return s.substring(bounds.getKey(), bounds.getValue() + 1);
    }

    //why return boolean instead of length ? In my initial implementation i returned length but it was wrong, because
    // if s[left] == s[right] we need to update the current palindrome length to right - left + 1 only if [left+1, right-1] is palindrome as well
    // if we return an int, we won't be sure whether the string is a palindrome or not e.g. {bbbab}, {bba} will return 2 and we will update the result to 5
    private boolean recur(String s, int left, int right, int[][] memoized) {
        if (memoized[left][right] != -1) {
            return memoized[left][right] == 1;
        }
        if (left == right) { //base case for odd length
            if (1 > res) {
                res = 1;
                bounds = new Pair<>(left, right);
            }
            memoized[left][right] = 1;
            return true;
        }
        if (left > right) { //base case for even length
            memoized[left][right] = 1;
            return true;
        }
        recur(s, left + 1, right, memoized);
        recur(s, left, right - 1, memoized);
        if (s.charAt(left) == s.charAt(right)) {
            boolean isRemainingPalindrome = recur(s, left + 1, right - 1, memoized);
            if (isRemainingPalindrome) { //if remaining string is palindrome, update the candidate length
                if (right - left + 1 > res) {
                    res = right - left + 1;
                    bounds = new Pair<>(left, right);
                }
                memoized[left][right] = 1;
                return true;
            }
        }
        memoized[left][right] = 0;
        return false;
    }
}
