import java.util.Arrays;
import java.util.HashMap;

/**
 * https://leetcode.com/problems/longest-word-in-dictionary/
 * <p>
 * Given a list of strings words representing an English Dictionary, find the longest word in words that can be built one character at a time
 * by other words in words. If there is more than one possible answer, return the longest word with the smallest lexicographical order.
 * <p>
 * If there is no answer, return the empty string.
 * <p>
 * Input:
 * words = ["w","wo","wor","worl", "world"]
 * Output: "world"
 * Explanation:
 * The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
 * <p>
 * Input:
 * words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
 * Output: "apple"
 * Explanation:
 * Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is lexicographically smaller than "apply".
 */
public class LongestWordInDictionary {
    /**
     * Approach: First sort the input and use DP to remember whether a prefix word is valid or not
     * (By prefix I meant substring except the last char)
     * <p>
     * Learnings: Whenever asked to deal with prefixes in a dictionary use fucking TRIE !
     */
    public String longestWord(String[] words) {
        Arrays.sort(words);
        int n = words.length;
        String result = "";
        boolean[] isPrefixValid = new boolean[n];
        HashMap<String, Integer> map = new HashMap<>();
        //need to find the index of the word, so that we can check whether that word is a valid word or not
        // instead of boolean[] + map combination could have used hashSet of valid words
        for (int i = 0; i < n; i++) {
            if (words[i].length() == 1) {
                isPrefixValid[i] = true;
            } else {
                String prefix = words[i].substring(0, words[i].length() - 1);
                if (map.containsKey(prefix) && isPrefixValid[map.get(prefix)]) { //check if the prefix is valid word or not
                    isPrefixValid[i] = true;
                }
            }
            if (isPrefixValid[i] && (result.isEmpty() || result.length() < words[i].length() || result.compareTo(words[i]) > 0)) {
                //if it's a valid word, update the result
                // do note that the problem statement asks for the longest word
                // if there are multiple longest word, return the lexicographically smaller one
                result = words[i];
            }
            map.put(words[i], i);
        }
        return result;
    }
}
