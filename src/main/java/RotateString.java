/**
 * https://leetcode.com/problems/rotate-string/
 * <p>
 * We are given two strings, A and B.
 * <p>
 * A shift on A consists of taking string A and moving the leftmost character to the rightmost position.
 * For example, if A = 'abcde', then it will be 'bcdea' after one shift on A. Return True if and only if A can become B after some number of shifts on A.
 * <p>
 * Input: A = 'abcde', B = 'cdeab'
 * Output: true
 * <p>
 * Input: A = 'abcde', B = 'abced'
 * Output: false
 */
public class RotateString {
    /**
     * Time Complexity here is n^2 because of contains().
     * To make it linear use KMP or Rolling hash algorithm.
     */
    public boolean rotateString(String A, String B) {
        String joined = A + A;
        return B.length() == A.length() && joined.contains(B);
    }
}
