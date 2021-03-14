/**
 * https://leetcode.com/problems/check-if-one-string-swap-can-make-strings-equal/
 * <p>
 * You are given two strings s1 and s2 of equal length. A string swap is an operation where you choose two indices in a string (not necessarily different)
 * and swap the characters at these indices.
 * <p>
 * Return true if it is possible to make both strings equal by performing at most one string swap on exactly one of the strings. Otherwise, return false.
 * <p>
 * Input: s1 = "bank", s2 = "kanb"
 * Output: true
 * Explanation: For example, swap the first character with the last character of s2 to make "bank".
 * <p>
 * Input: s1 = "attack", s2 = "defend"
 * Output: false
 * Explanation: It is impossible to make them equal with one string swap.
 * <p>
 * Input: s1 = "kelb", s2 = "kelb"
 * Output: true
 * Explanation: The two strings are already equal, so no string swap operation is required.
 * <p>
 * Input: s1 = "abcd", s2 = "dcba"
 * Output: false
 * <p>
 * Constraints:
 * 1 <= s1.length, s2.length <= 100
 * s1.length == s2.length
 * s1 and s2 consist of only lowercase English letters.
 */
public class CheckIfOneStringSwapCanMakeStringsEqual {
    /**
     * Approach: Keep track of indices where differences are observed. If such indices != 2, return false
     * Return true if the characters at those indices are mirrored.
     * <p>
     * {@link BuddyStrings}
     */
    public boolean areAlmostEqual(String s1, String s2) {
        if (s1.equals(s2)) {
            return true;
        }
        int index1 = -1, index2 = -1;
        for (int i = 0; i < s1.length(); i++) {
            char c1 = s1.charAt(i), c2 = s2.charAt(i);
            if (c1 != c2) {
                if (index2 != -1) {
                    return false;
                } else if (index1 == -1) {
                    index1 = i;
                } else if (s1.charAt(index1) == s2.charAt(i) && s1.charAt(i) == s2.charAt(index1)) {
                    index2 = i;
                } else {
                    return false;
                }
            }
        }
        //Make sure to check whether we have exactly two mismatched indices.
        return index1 != -1 && index2 != -1;
    }
}
