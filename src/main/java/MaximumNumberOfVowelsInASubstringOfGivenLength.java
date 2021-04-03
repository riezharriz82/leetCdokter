/**
 * https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/
 * <p>
 * Given a string s and an integer k.
 * <p>
 * Return the maximum number of vowel letters in any substring of s with length k.
 * <p>
 * Input: s = "abciiidef", k = 3
 * Output: 3
 * Explanation: The substring "iii" contains 3 vowel letters.
 * <p>
 * Input: s = "aeiou", k = 2
 * Output: 2
 * Explanation: Any substring of length 2 contains 2 vowels.
 * <p>
 * Input: s = "leetcode", k = 3
 * Output: 2
 * Explanation: "lee", "eet" and "ode" contain 2 vowels.
 * <p>
 * Input: s = "rhythms", k = 4
 * Output: 0
 * Explanation: We can see that s doesn't have any vowel letters.
 * <p>
 * Input: s = "tryhard", k = 4
 * Output: 1
 * <p>
 * Constraints:
 * 1 <= s.length <= 10^5
 * s consists of lowercase English letters.
 * 1 <= k <= s.length
 */
public class MaximumNumberOfVowelsInASubstringOfGivenLength {
    /**
     * Approach: Standard sliding window problem, maintain a sliding window of fixed size of length k.
     * Keep track of count of vowels in the current window.
     */
    public int maxVowels(String s, int k) {
        int maxCount = 0, curCount = 0, n = s.length();
        for (int i = 0; i < k; i++) {
            char c = s.charAt(i);
            if (isVowel(c)) {
                curCount++;
            }
        }
        maxCount = Math.max(maxCount, curCount);
        int left = 0, right = k;
        while (right < n) {
            char c = s.charAt(right);
            if (isVowel(c)) {
                curCount++;
            }
            if (isVowel(s.charAt(left))) {
                //handle the characters falling out of the window
                curCount--;
            }
            left++;
            right++;
            maxCount = Math.max(maxCount, curCount);
        }
        return maxCount;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
