/**
 * https://leetcode.com/problems/one-edit-distance/ Premium
 * <p>
 * Given two strings s and t, return true if they are both one edit distance apart, otherwise return false.
 * <p>
 * A string s is said to be one distance apart from a string t if you can:
 * <p>
 * Insert exactly one character into s to get t.
 * Delete exactly one character from s to get t.
 * Replace exactly one character of s with a different character to get t.
 * <p>
 * Input: s = "ab", t = "acb"
 * Output: true
 * Explanation: We can insert 'c' into s to get t.
 * <p>
 * Input: s = "", t = ""
 * Output: false
 * Explanation: We cannot get t from s by only one step.
 * <p>
 * Input: s = "a", t = ""
 * Output: true
 * <p>
 * Input: s = "", t = "A"
 * Output: true
 * <p>
 * Constraints:
 * 0 <= s.length <= 104
 * 0 <= t.length <= 104
 * s and t consist of lower-case letters, upper-case letters and/or digits.
 */
public class OneEditDistance {
    /**
     * Approach: Two pointers solution, keep two pointers for s and t, in case of mismatch occurs, check if the remaining substring
     * is equal or not.
     * <p>
     * In my initial solution, I tried to solve this problem by checking frequency count of two strings but it failed when
     * two strings were anagram, realized the mistake and implemented two pointer solution.
     * <p>
     * Initially my code had duplicate logic for handling (len1 - len2 == 1) but then saw discuss solutions which leveraged
     * swapping the variables and perform recursion which shortens the code
     * <p>
     * {@link DeleteOperationForTwoString} {@link EditDistance} {@link PalindromicSubstrings} {@link DistinctSubsequences}
     */
    public boolean isOneEditDistance(String s, String t) {
        int len1 = s.length();
        int len2 = t.length();
        if (Math.abs(len1 - len2) > 1) { //if length differ by more than 1, edit distance can never be 1
            return false;
        }
        if (len1 > len2) { //tricky logic to avoid duplicate code
            return isOneEditDistance(t, s);
        }
        if (len2 - len1 == 1) {
            //try inserting one character into s to get t
            for (int i = 0; i < len1; i++) {
                char c1 = s.charAt(i), c2 = t.charAt(i);
                if (c1 != c2) {
                    //in case mismatch found, increment the pointer in t and check whether the remaining substring matches or not
                    return s.substring(i).equals(t.substring(i + 1));
                }
            }
            return true;
        } else {
            //try replacing one character
            for (int i = 0; i < len1; i++) {
                char c1 = s.charAt(i), c2 = t.charAt(i);
                if (c1 != c2) {
                    //in case mismatch is found, remaining substring must be equal to have edit distance of 1
                    return s.substring(i + 1).equals(t.substring(i + 1));
                }
            }
            //in case of no mismatch, edit distance is 0
            return false;
        }
    }
}
