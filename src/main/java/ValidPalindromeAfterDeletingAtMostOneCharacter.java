/**
 * https://leetcode.com/problems/valid-palindrome-ii/
 * <p>
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
 * <p>
 * Example 1:
 * Input: "aba"
 * Output: True
 * <p>
 * Example 2:
 * Input: "abca"
 * Output: True
 * Explanation: You could delete the character 'c'.
 */
public class ValidPalindromeAfterDeletingAtMostOneCharacter {
    public boolean validPalindrome(String s) {
        return recur(s, false);
    }

    /**
     * Try iterating from begin and end, in case of mismatch, you have two options, either delete the begin character or delete the end character
     * After deleting the remaining string should be palindrome.
     */
    private boolean recur(String s, boolean alreadyMismatch) {
        int begin = 0, end = s.length() - 1;
        while (begin < end) {
            if (s.charAt(begin) == s.charAt(end)) {
                begin++;
                end--;
            } else {
                if (alreadyMismatch) {
                    //if one mismatch already found, need to return false
                    return false;
                } else {
                    //in case of first mismatch try deleting the begin character or delete the end character
                    return recur(s.substring(begin, end), true) || recur(s.substring(begin + 1, end + 1), true);
                }
            }
        }
        return true;
    }
}
