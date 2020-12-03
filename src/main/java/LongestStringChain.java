import java.util.*;

/**
 * https://leetcode.com/problems/longest-string-chain/
 * <p>
 * Given a list of words, each word consists of English lowercase letters.
 * <p>
 * Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make it equal to word2.
 * For example, "abc" is a predecessor of "abac".
 * <p>
 * A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of word_2, word_2 is a predecessor of word_3, and so on.
 * <p>
 * Return the longest possible length of a word chain with words chosen from the given list of words.
 * <p>
 * Input: words = ["a","b","ba","bca","bda","bdca"]
 * Output: 4
 * Explanation: One of the longest word chain is "a","ba","bda","bdca".
 * <p>
 * Input: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
 * Output: 5
 */
public class LongestStringChain {
    /**
     * Approach: Sort words by length and for each word, try to generate possible previous words in the chain and get its length
     * <p>
     * i.e {abcd} can extend {bcd},{acd},{abd},{abc}. Check whether this words are present in the input, if yes, get their length
     * and extend current length by 1
     * <p>
     * {@link WordLadder} related problem
     */
    public int longestStrChainAlternate(String[] words) {
        Arrays.sort(words, ((o1, o2) -> Integer.compare(o1.length(), o2.length())));
        HashMap<String, Integer> map = new HashMap<>();
        int longestChain = 0;
        for (int i = 0; i < words.length; i++) {
            if (!map.containsKey(words[i])) { //skip duplicates
                int curLongestChain = 1;
                List<String> candidates = enumeratePreviousWords(words[i]);
                for (String candidate : candidates) {
                    if (map.containsKey(candidate)) {
                        curLongestChain = Math.max(curLongestChain, 1 + map.get(candidate));
                    }
                }
                map.put(words[i], curLongestChain);
                longestChain = Math.max(longestChain, curLongestChain);
            }
        }
        return longestChain;
    }

    private List<String> enumeratePreviousWords(String word) {
        List<String> previousWords = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            previousWords.add(new StringBuilder(word).deleteCharAt(i).toString()); //using stringbuilder to delete specific index reduces boiler plate code
        }
        return previousWords;
    }

    /**
     * Approach: DFS + Memoization
     * Opposite of previous approach, instead of looking for a smaller word from a longer word, look for a larger word from
     * smaller word.
     * In order to generate larger word, try all possible combinations of 'a' to 'z' at each character index and see whether it exists or not
     * <p>
     * Although it's slow but it did get me AC
     */
    public int longestStrChain(String[] words) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, -1);
        }
        int result = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == -1) {
                result = Math.max(result, DFS(map, entry.getKey()));
            }
        }
        return result;
    }

    private int DFS(HashMap<String, Integer> map, String key) {
        if (map.get(key) != -1) { //result for the key has already been computed
            return map.get(key);
        }
        int longestChain = 1;
        for (int i = 0; i <= key.length(); i++) {
            String prefix = key.substring(0, i);
            String suffix = "";
            if (i != key.length()) {
                suffix = key.substring(i);
            }
            for (char c = 'a'; c <= 'z'; c++) { //generate all possible candidates that can extend current word
                String candidate = prefix + c + suffix;
                if (map.containsKey(candidate)) { //if candidate exists, do DFS
                    longestChain = Math.max(longestChain, 1 + DFS(map, candidate));
                }
            }
        }
        map.put(key, longestChain);
        return longestChain;
    }
}
