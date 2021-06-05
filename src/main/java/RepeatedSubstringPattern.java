/**
 * https://leetcode.com/problems/repeated-substring-pattern/
 * <p>
 * Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together.
 * You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.
 * <p>
 * Input: "abab"
 * Output: True
 * Explanation: It's the substring "ab" twice.
 */
public class RepeatedSubstringPattern {
    /**
     * n^2 solution because of contains().
     * To reduce it to linear time, use rolling hash or kmp
     * for rolling hash implementation see {@link RepeatedDNASequence}
     * <p>
     * {@link RepeatedStringMatch}
     */
    public boolean repeatedSubstringPatternSimplified(String s) {
        String concat = (s + s);
        return concat.substring(1, concat.length() - 1).contains(s);
    }

    /**
     * Since the string is repeated, this means that the first character needs to occurs > 1 times in the input
     * So we get all the occurrences of the first character and try to repeat the substring from the occurrence of that char till the end
     * This is not the fastest solution but my original solution.
     */
    public boolean repeatedSubstringPattern(String s) {
        int start = 1; //if start from 0, then the entire string is considered
        while (true) {
            int indexOf = s.indexOf(s.charAt(0), start++);
            if (indexOf == -1) {
                //no remaining occurrences of first char
                return false;
            }
            String candidate = s.substring(indexOf);
            if (s.length() % candidate.length() != 0) {
                continue;
            }
            int multiplier = s.length() / candidate.length();
            StringBuilder expected = new StringBuilder();
            for (int i = 0; i < multiplier; i++) {
                expected.append(candidate);
            }
            if (expected.toString().equals(s)) {
                return true;
            }
        }
    }
}
