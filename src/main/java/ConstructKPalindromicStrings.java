/**
 * https://leetcode.com/problems/construct-k-palindrome-strings/
 * <p>
 * Given a string s and an integer k. You should construct k non-empty palindrome strings using all the characters in s.
 * <p>
 * Return True if you can use all the characters in s to construct k palindrome strings or False otherwise.
 * <p>
 * Input: s = "annabelle", k = 2
 * Output: true
 * Explanation: You can construct two palindromes using all characters in s.
 * Some possible constructions "anna" + "elble", "anbna" + "elle", "anellena" + "b"
 * <p>
 * Input: s = "leetcode", k = 3
 * Output: false
 * Explanation: It is impossible to construct 3 palindromes using all the characters of s.
 * <p>
 * Input: s = "true", k = 4
 * Output: true
 * Explanation: The only possible solution is to put each character in a separate string.
 * <p>
 * Input: s = "yzyzyzyzyzyzyzy", k = 2
 * Output: true
 * Explanation: Simply you can put all z's in one string and all y's in the other string. Both strings will be palindrome.
 * <p>
 * Input: s = "cr", k = 7
 * Output: false
 * Explanation: We don't have enough characters in s to construct 7 palindromes.
 * <p>
 * Constraints:
 * 1 <= s.length <= 10^5
 * All characters in s are lower-case English letters.
 * 1 <= k <= 10^5
 */
public class ConstructKPalindromicStrings {
    /**
     * Approach: Greedy, extremely tricky thing to note here is that the chars with odd frequency are the limiting factor as each string
     * can have at most 1 odd char, so if there are > k odd chars, we can't divide the input into k palindromic strings
     * If there are < k odd chars, eg. {abbbcc} k = 4, we can always split the even frequency or odd frequency
     * <p>
     * {@link LengthOfLongestPalindrome} {@link NumberOfSubarraysWithOddSum}
     */
    public boolean canConstruct(String s, int k) {
        if (k > s.length()) { //maximum palindromic strings can be <= s.length()
            return false;
        }
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        int odd = 0;
        for (int val : freq) {
            if (val % 2 == 1) {
                odd++;
            }
        }
        return odd <= k;
    }
}
