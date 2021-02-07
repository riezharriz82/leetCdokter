/**
 * https://leetcode.com/problems/minimum-length-of-string-after-deleting-similar-ends/
 * <p>
 * Given a string s consisting only of characters 'a', 'b', and 'c'. You are asked to apply the following algorithm on the string any number of times:
 * <p>
 * Pick a non-empty prefix from the string s where all the characters in the prefix are equal.
 * Pick a non-empty suffix from the string s where all the characters in this suffix are equal.
 * The prefix and the suffix should not intersect at any index.
 * The characters from the prefix and suffix must be the same.
 * Delete both the prefix and the suffix.
 * Return the minimum length of s after performing the above operation any number of times (possibly zero times).
 * <p>
 * Input: s = "ca"
 * Output: 2
 * Explanation: You can't remove any characters, so the string stays as is.
 * <p>
 * Input: s = "cabaabac"
 * Output: 0
 * Explanation: An optimal sequence of operations is:
 * - Take prefix = "c" and suffix = "c" and remove them, s = "abaaba".
 * - Take prefix = "a" and suffix = "a" and remove them, s = "baab".
 * - Take prefix = "b" and suffix = "b" and remove them, s = "aa".
 * - Take prefix = "a" and suffix = "a" and remove them, s = "".
 * <p>
 * Input: s = "aabccabba"
 * Output: 3
 * Explanation: An optimal sequence of operations is:
 * - Take prefix = "aa" and suffix = "a" and remove them, s = "bccabb".
 * - Take prefix = "b" and suffix = "bb" and remove them, s = "cca".
 * <p>
 * Constraints:
 * 1 <= s.length <= 105
 * s only consists of characters 'a', 'b', and 'c'.
 */
public class MinimumLengthOfStringAfterDeletingSimilarEnds {
    /**
     * Approach: Use Two pointers to find prefix and suffix string of similar characters
     * <p>
     * {@link FindKClosestElements} {@link FindMaximumScoreBySwitchingPaths}
     */
    public int minimumLength(String s) {
        if (s.length() == 1) {
            return 1;
        }
        int begin = 0, end = s.length() - 1;
        while (begin < end) {
            //if the characters at the front and back doesn't match, we can't have non-zero prefix and suffix comprising of same characters
            if (s.charAt(begin) != s.charAt(end)) {
                break;
            }
            //keep incrementing the prefix and suffix pointers one by one
            while (begin < end && s.charAt(begin) == s.charAt(end)) {
                begin++;
                end--;
            }
            //keep incrementing the prefix pointer to keep considering all similar characters
            //tricky thing to note is to consider begin <= end instead of begin < end in order to consider cases like "bcb" and "bbb"
            while (begin <= end && begin > 0 && s.charAt(begin) == s.charAt(begin - 1)) {
                begin++;
            }
            while (begin <= end && end < s.length() - 1 && s.charAt(end) == s.charAt(end + 1)) {
                end--;
            }
        }
        return end - begin + 1; //return the size left, if begin > end, we have deleted all characters and it returns 0
        //if begin == end, it returns 1
    }
}
