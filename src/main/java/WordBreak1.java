import java.util.HashSet;
import java.util.List;

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
