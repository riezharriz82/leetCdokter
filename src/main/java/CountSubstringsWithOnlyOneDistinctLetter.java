/**
 * https://leetcode.com/problems/count-substrings-with-only-one-distinct-letter/ Premium
 * <p>
 * Given a string S, return the number of substrings that have only one distinct letter.
 * <p>
 * Input: S = "aaaba"
 * Output: 8
 * Explanation: The substrings with one distinct letter are "aaa", "aa", "a", "b".
 * "aaa" occurs 1 time.
 * "aa" occurs 2 times.
 * "a" occurs 4 times.
 * "b" occurs 1 time.
 * So the answer is 1 + 2 + 4 + 1 = 8.
 * <p>
 * Input: S = "aaaaaaaaaa"
 * Output: 55
 * <p>
 * Constraints:
 * 1 <= S.length <= 1000
 * S[i] consists of only lowercase English letters.
 */
public class CountSubstringsWithOnlyOneDistinctLetter {
    /**
     * Approach: Find the length of longest substring with no other characters. Once you have that, it can internally be
     * broken down into multiple smaller substrings with no other characters. Keep repeating it for other characters.
     * <p>
     * {@link MinimumWindowSubstring}
     */
    public int countLetters(String S) {
        int index = 0, ans = 0;
        while (index < S.length()) {
            int temp = index + 1;
            while (temp < S.length() && S.charAt(index) == S.charAt(temp)) { //keep incrementing the temp until we find another character
                temp++;
            }
            int cnt = temp - index; //length of longest substring with no other character
            ans += ((cnt * (cnt + 1)) / 2); //count of smaller substrings
            index = temp;
        }
        return ans;
    }
}
