/**
 * https://leetcode.com/problems/remove-palindromic-subsequences/
 * <p>
 * Given a string s consisting only of letters 'a' and 'b'. In a single step you can remove one palindromic subsequence from s.
 * <p>
 * Return the minimum number of steps to make the given string empty.
 * <p>
 * Input: s = "ababa"
 * Output: 1
 * Explanation: String is already palindrome
 * <p>
 * Input: s = "abb"
 * Output: 2
 * Explanation: "abb" -> "bb" -> "".
 * Remove palindromic subsequence "a" then "bb".
 * <p>
 * Input: s = "baabb"
 * Output: 2
 * Explanation: "baabb" -> "b" -> "".
 * Remove palindromic subsequence "baab" then "b".
 * <p>
 * Input: s = ""
 * Output: 0
 * <p>
 * Constraints:
 * 0 <= s.length <= 1000
 * s only consists of letters 'a' and 'b'
 */
public class RemovePalindromicSubsequence {

    /**
     * Approach: Adhoc, tricky thing to note here is that input contains only two type of characters, so the worst case would be
     * to first delete all a's, then delete all b's, so result is 2.
     * <p>
     * Result is 1 if the string is a palindrome
     * <p>
     * {@link LengthOfLongestPalindrome} {@link PalindromePartitioning}
     */
    public int removePalindromeSub(String s) {
        if (s.isEmpty()) {
            return 0;
        } else if (isPalindrome(s)) {
            return 1;
        } else {
            return 2;
        }
    }

    private boolean isPalindrome(String s) {
        int low = 0, high = s.length() - 1;
        while (low < high) {
            if (s.charAt(low) != s.charAt(high)) {
                return false;
            }
            low++;
            high--;
        }
        return true;
    }
}
