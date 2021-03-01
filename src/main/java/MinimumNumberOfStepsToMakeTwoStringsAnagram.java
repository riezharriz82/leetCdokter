/**
 * https://leetcode.com/problems/minimum-number-of-steps-to-make-two-strings-anagram/
 * <p>
 * Given two equal-size strings s and t. In one step you can choose any character of t and replace it with another character.
 * <p>
 * Return the minimum number of steps to make t an anagram of s.
 * <p>
 * Input: s = "bab", t = "aba"
 * Output: 1
 * Explanation: Replace the first 'a' in t with b, t = "bba" which is anagram of s.
 * <p>
 * Input: s = "leetcode", t = "practice"
 * Output: 5
 * Explanation: Replace 'p', 'r', 'a', 'i' and 'c' from t with proper characters to make t anagram of s.
 * <p>
 * Input: s = "anagram", t = "mangaar"
 * Output: 0
 * Explanation: "anagram" and "mangaar" are anagrams.
 * <p>
 * Input: s = "xxyyzz", t = "xxyyzz"
 * Output: 0
 * <p>
 * Input: s = "friend", t = "family"
 * Output: 4
 * <p>
 * Constraints:
 * 1 <= s.length <= 50000
 * s.length == t.length
 * s and t contain lower-case English letters only.
 */
public class MinimumNumberOfStepsToMakeTwoStringsAnagram {
    /**
     * Approach: Greedy, find characters that are in excess or deficit and try to greedily swap from one category to another
     * <p>
     * {@link DetermineIfTwoStringsAreClose} {@link OneEditDistance} {@link FindAllAnagramInString}
     */
    public int minSteps(String s, String t) {
        int[] cnt = new int[26];
        for (char c : t.toCharArray()) {
            cnt[c - 'a']++;
        }
        for (char c : s.toCharArray()) {
            cnt[c - 'a']--;
        }
        int res = 0;
        for (int val : cnt) {
            if (val > 0) { // this is the most important part, notice we are only looking for characters that have excess count
                //character that are extra, needs to be swapped with some other characters
                //problem statement ensures that that strings can always be made anagram
                //so in order to do it with min steps we can choose any character that are low or have -ve count
                //we can even switch the condition to consider only (val < 0)
                res += val;
            }
        }
        return res;
    }
}
