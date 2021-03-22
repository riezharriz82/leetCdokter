import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/vowel-spellchecker/
 * <p>
 * Given a wordlist, we want to implement a spellchecker that converts a query word into a correct word.
 * <p>
 * For a given query word, the spell checker handles two categories of spelling mistakes:
 * <p>
 * Capitalization: If the query matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the case in the wordlist.
 * Example: wordlist = ["yellow"], query = "YellOw": correct = "yellow"
 * Example: wordlist = ["Yellow"], query = "yellow": correct = "Yellow"
 * Example: wordlist = ["yellow"], query = "yellow": correct = "yellow"
 * <p>
 * Vowel Errors: If after replacing the vowels ('a', 'e', 'i', 'o', 'u') of the query word with any vowel individually,
 * it matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the match in the wordlist.
 * <p>
 * Example: wordlist = ["YellOw"], query = "yollow": correct = "YellOw"
 * Example: wordlist = ["YellOw"], query = "yeellow": correct = "" (no match)
 * Example: wordlist = ["YellOw"], query = "yllw": correct = "" (no match)
 * In addition, the spell checker operates under the following precedence rules:
 * <p>
 * When the query exactly matches a word in the wordlist (case-sensitive), you should return the same word back.
 * When the query matches a word up to capitalization, you should return the first such match in the wordlist.
 * When the query matches a word up to vowel errors, you should return the first such match in the wordlist.
 * If the query has no matches in the wordlist, you should return the empty string.
 * Given some queries, return a list of words answer, where answer[i] is the correct word for query = queries[i].
 * <p>
 * Input: wordlist = ["KiTe","kite","hare","Hare"], queries = ["kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"]
 * Output: ["kite","KiTe","KiTe","Hare","hare","","","KiTe","","KiTe"]
 * <p>
 * Note:
 * 1 <= wordlist.length <= 5000
 * 1 <= queries.length <= 5000
 * 1 <= wordlist[i].length <= 7
 * 1 <= queries[i].length <= 7
 * All strings in wordlist and queries consist only of english letters.
 */
public class VowelSpellChecker {
    /**
     * Approach: Maintain reverse indexes for each word e.g if the word is "Yellow", it can be queried from "yellow", "Yellow" or "yxllxw"
     * As vowels can be mapped to any character, we have to mask it to * to represent any character.
     * <p>
     * In order to reduce memory consumption, we can use Trie
     * <p>
     * {@link WordLadder} {@link StreamChecker} related problems
     */
    public String[] spellchecker(String[] wordlist, String[] queries) {
        Map<String, String> reverseIndex = new HashMap<>();
        Set<String> words = new HashSet<>();
        for (String word : wordlist) {
            words.add(word);
            String caseInsensitiveKey = word.toLowerCase();
            //update reverse index map, as we are interested only in the first match, we can use putIfAbsent() instead of maintaining a list<String> per key
            reverseIndex.putIfAbsent(caseInsensitiveKey, word);
            reverseIndex.putIfAbsent(toVowelInsensitive(caseInsensitiveKey), word);
        }
        String[] result = new String[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String caseInsensitiveKey = queries[i].toLowerCase();
            String vowelInsensitive = toVowelInsensitive(caseInsensitiveKey);
            //Gotcha: Match in the correct preference order as per problem statement
            if (words.contains(queries[i])) { //exact match
                result[i] = queries[i];
            } else if (reverseIndex.containsKey(caseInsensitiveKey)) { //case insensitive match
                result[i] = reverseIndex.get(caseInsensitiveKey);
            } else if (reverseIndex.containsKey(vowelInsensitive)) { //match by replacing vowels
                result[i] = reverseIndex.get(vowelInsensitive);
            } else { //no match found
                result[i] = "";
            }
        }
        return result;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    private String toVowelInsensitive(String input) {
        char[] replaceVowels = input.toCharArray();
        for (int i = 0; i < input.length(); i++) {
            if (isVowel(input.charAt(i))) {
                replaceVowels[i] = '*';
            }
        }
        return new String(replaceVowels);
    }
}
