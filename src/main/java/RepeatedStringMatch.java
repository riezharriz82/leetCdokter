/**
 * https://leetcode.com/problems/repeated-string-match/
 * <p>
 * Given two strings A and B, find the minimum number of times A has to be repeated such that B is a substring of it.
 * If no such solution, return -1.
 * <p>
 * Input: a = "abcd", b = "cdabcdab"
 * Output: 3
 * Explanation: We return 3 because by repeating a three times "abcdabcdabcd", b is a substring of it.
 * <p>
 * Input: a = "a", b = "aa"
 * Output: 2
 * <p>
 * Input: a = "a", b = "a"
 * Output: 1
 * <p>
 * Input: a = "abc", b = "wxyz"
 * Output: -1
 * <p>
 * Constraints:
 * 1 <= a.length <= 10^4
 * 1 <= b.length <= 10^4
 * a and b consist of lower-case English letters.
 */
public class RepeatedStringMatch {
    /**
     * Approach: Greedy, make the length of string A >= length of string B by concatenating string a multiple times.
     * Check if the B is now a substring of concatenated string.
     * If no, concatenate A one more time to handle cases when A is rotated and wrapped around.
     * If still no, its not possible.
     *
     * {@link RepeatedSubstringPattern}
     */
    public int repeatedStringMatch(String A, String B) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        while (sb.length() < B.length()) {
            sb.append(A);
            count++;
        }
        if (sb.toString().contains(B)) {
            return count;
        } else if (sb.append(A).toString().contains(B)) { //handle wrapping around case
            return count + 1;
        }
        return -1;
    }
}
