/**
 * https://leetcode.com/problems/longest-palindrome/
 * <p>
 * Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.
 * <p>
 * This is case sensitive, for example "Aa" is not considered a palindrome here.
 * <p>
 * Input:
 * "abccccdd"
 * <p>
 * Output:
 * 7
 * <p>
 * Explanation:
 * One longest palindrome that can be built is "dccaccd", whose length is 7.
 */
public class LengthOfLongestPalindrome {

    /**
     * Count the occurrences of each character. Even characters can be used up for palindrome as it is.
     * For odd characters, we can use the even part as it is. For the remaining 1 character, use it if no other odd character found
     * Because only one odd character can act as the center of the palindrome.
     */
    public int longestPalindrome(String s) {
        int[] cnt = new int[256]; //because input can have capitals
        for (char c : s.toCharArray()) {
            cnt[c]++;
        }
        int single = 0, pairs = 0;
        for (int count : cnt) {
            if (count != 0) {
                pairs += ((count / 2) * 2);
                if (single == 0 && count % 2 == 1) {
                    single = 1;
                }
            }
        }
        return single + pairs;
    }
}
