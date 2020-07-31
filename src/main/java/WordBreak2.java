import java.util.*;
import java.util.stream.Collectors;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.
 * <p>
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * Example 1:
 * <p>
 * Input:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * Output:
 * <pre>
 * [
 *   "cats and dog",
 *   "cat sand dog"
 * ]
 * </pre>
 */
public class WordBreak2 {
    public List<String> wordBreakMemoizedDFS(String s, List<String> wordDict) {
        return DFS(s, new HashSet<>(wordDict), new HashMap<>());
    }

    // DFS function returns an array including all substrings derived from s.
    // This returns early in cases where solution is not possible
    List<String> DFS(String s, Set<String> wordDict, HashMap<String, ArrayList<String>> map) {
        if (map.containsKey(s))
            return map.get(s);

        ArrayList<String> res = new ArrayList<>();
        if (s.length() == 0) {
            res.add("");
            return res;
        }
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String> sublist = DFS(s.substring(word.length()), wordDict, map);
                for (String sub : sublist)
                    res.add(word + (sub.isEmpty() ? "" : " ") + sub);
            }
        }
        map.put(s, res);
        return res;
    }

    /**
     * Highly based on {@link WordBreak1} For each index, store the ways to break the string until that index.
     * For the remaining substring, concatenate each of the ways to reach current index with the word that is present in the remaining substring
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        //This is required to not do processing for cases where result is not possible, otherwise gives memory limit exceeded because
        // of storing so many unique strings in intermediate results
        if (!isWordBreakPossible(s, wordDict)) {
            return Arrays.asList();
        }

        Result[] dp = new Result[s.length()];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = new Result();
        }

        for (String word : wordDict) {
            if (s.startsWith(word)) {
                dp[word.length() - 1].result.add(new StringBuilder(word));
            }
        }
        for (int i = 0; i < dp.length; i++) {
            if (!dp[i].result.isEmpty()) {
                String remaining = s.substring(i + 1);
                for (String word : wordDict) {
                    if (remaining.startsWith(word)) {
                        // if there are 3 ways to build till index i, there will be 3 ways to build till index i + word.length()
                        for (StringBuilder resultTillNow : dp[i].result) {
                            StringBuilder newBuilder = new StringBuilder(resultTillNow).append(" ").append(word);
                            dp[i + 1 + word.length() - 1].result.add(newBuilder);
                        }
                    }
                }
            }
        }
        return dp[s.length() - 1].result
                .stream()
                .map(StringBuilder::toString)
                .collect(Collectors.toList());
    }

    private boolean isWordBreakPossible(String s, List<String> wordDict) {
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

        return dp[s.length() - 1];
    }

    private class Result {
        List<StringBuilder> result = new ArrayList<>();
    }
}
