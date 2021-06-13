/**
 * https://leetcode.com/problems/palindrome-permutation/
 * <p>
 * Given a string, determine if a permutation of the string could form a palindrome.
 * <p>
 * Input: "code"
 * Output: false
 * <p>
 * Input: "aab"
 * Output: true
 * <p>
 * Input: "carerac"
 * Output: true
 */
public class PalindromePermutation {
    /**
     * {@link LengthOfLongestPalindrome} Very similar problem to previously solved problem
     * All even frequency characters can happily contribute to palindrome, the odd count characters has a caveat that there should be only one
     * character with odd frequency, that can act as the center of the palindrome.
     * <p>
     * {@link RedistributeCharactersToMakeAllStringsEqual}
     */
    public boolean canPermutePalindrome(String s) {
        int[] cnt = new int[256];
        for (char c : s.toCharArray()) {
            cnt[c]++;
        }
        boolean isOddElementPresent = false;
        for (int i = 0; i < 256; i++) {
            if (cnt[i] > 0) {
                if (cnt[i] % 2 == 1) {
                    if (!isOddElementPresent) {
                        isOddElementPresent = true;
                    } else {
                        //already an odd element is present, can't add this character to the palindrome
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
