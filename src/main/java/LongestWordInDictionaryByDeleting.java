import java.util.List;

/**
 * https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/
 * <p>
 * Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters of the given string.
 * <p>
 * If there are more than one possible results, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.
 * <p>
 * Input:
 * s = "abpcplea", d = ["ale","apple","monkey","plea"]
 * Output:
 * "apple"
 * <p>
 * Input:
 * s = "abpcplea", d = ["a","b","c"]
 * Output:
 * "a"
 * <p>
 * Note:
 * All the strings in the input will only contain lower-case letters.
 * The size of the dictionary won't exceed 1,000.
 * The length of all the strings in the input won't exceed 1,000.
 */
public class LongestWordInDictionaryByDeleting {
    /**
     * The problem reduces to check whether one string is a subsequence of another string. {@link IsSubsequence} for related problem
     * If a string S is subsequence of another string W, we can delete some characters from the string W to match it to S
     * <p>
     * {@link NumberOfMatchingSubsequences} related problems but with harder constraints, current solution wont AC
     */
    public String findLongestWord(String s, List<String> words) {
        String result = null;
        for (String word : words) {
            if (isSubsequence(s, word)) {
                if (result == null) { //first match
                    result = word;
                } else if (word.length() > result.length()) { //this word is longer than the existing result, this means fewer characters to delete
                    result = word;
                } else if (result.length() == word.length() && result.compareTo(word) > 0) { //this word is lexicographically smaller than the existing result
                    result = word;
                }
            }
        }
        return result == null ? "" : result; // match the expected output
    }

    /**
     * Alternate way of checking whether a key is a subsequence of a word is to preprocess the word and create a map of character to a sorted list of index of occurrence
     * If word is appleee, then the map would be {a=0}, {p={1,2}}, {l=3}, {e={4,5,6}}
     * Now for each character in key, check whether the current character occurs after the previous characters index by performing a binary search on the list of the current
     * character
     */
    private boolean isSubsequence(String word, String key) {
        int keyIndex = 0, wordIndex = 0;
        while (keyIndex < word.length() && wordIndex < key.length()) {
            if (word.charAt(keyIndex) == key.charAt(wordIndex)) {
                keyIndex++;
                wordIndex++;
            } else {
                keyIndex++;
            }
        }
        return wordIndex == key.length(); //if all the characters in the key have been matched, return true
    }
}
