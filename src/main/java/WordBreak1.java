import java.util.HashSet;
import java.util.List;

/**
 * https://leetcode.com/problems/word-break/
 * <p>
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented
 * into a space-separated sequence of one or more dictionary words.
 * <p>
 * Note:
 * <p>
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * <p>
 * Input: s = "leetcode", wordDict = ["leet", "code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * <p>
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * Note that you are allowed to reuse a dictionary word.
 * <p>
 * See {@link PerfectSquares} for similar problem
 */
public class WordBreak1 {

    /**
     * Optimized version than my original code. Instead of generating all the possible subarray and checking if this subarray
     * exists in the dictionary, check whether current string starts from words present in the dictionary or not.
     * This approach automatically marks all the valid substring.
     * From a marked substring, repeat the same process for the remaining substring
     */
    public boolean wordBreakOptimized(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length()];
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                dp[word.length() - 1] = true;
            }
        }
        for (int i = 0; i < dp.length; i++) {
            if (dp[i]) {
                String remainingWord = s.substring(i + 1);
                for (String word : wordDict) {
                    if (remainingWord.startsWith(word)) {
                        dp[i + 1 + word.length() - 1] = true;
                    }
                }
            }
        }

        return dp[dp.length - 1];
    }

    /**
     * My original n^2 approach, similar to longest increasing subsequence DP
     * For each substring ending at index i, store whether it's possible to find all the constituent words in the dictionary
     * <p>
     * Instead of a 1D DP, 2D DP can also be used to store results for substring of length 1, 2, 3 .. s.length()
     * 2D DP will store results for all intermediate strings possible after introducing cuts for every previous index
     * https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/BreakMultipleWordsWithNoSpaceIntoSpace.java#L60
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> dictionary = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j <= i; j++) {
                //if the previous subarray was a complete word and this subarray lies in dictionary then we can make a complete word
                if (j - 1 >= 0 && dp[j - 1] && dictionary.contains(s.substring(j, i + 1))) {
                    dp[i] = true;
                    break;
                } else if (j == 0 && dictionary.contains(s.substring(j, i + 1))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length() - 1];
    }
}
