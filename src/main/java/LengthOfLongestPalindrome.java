import java.util.HashMap;
import java.util.Map;

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
     * Count the characters with even no of occurrences plus a character with single occurrence that can act as the center of the palindrome
     */
    public int longestPalindrome(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int single = 0, pairs = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            pairs += entry.getValue() / 2;
            if (single == 0 && entry.getValue() == 1) { //to handle input like ccc
                single = 1;
            }
        }
        return single + 2 * pairs;
    }
}
