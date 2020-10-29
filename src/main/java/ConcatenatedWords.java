import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/concatenated-words/
 * <p>
 * Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.
 * A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.
 * <p>
 * Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 * <p>
 * Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
 * <p>
 * Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
 * "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
 * "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
 */
public class ConcatenatedWords {

    Trie root = new Trie();

    /**
     * Approach: Words can only be concatenated from smaller words, so first sort the input array and check whether the current word
     * can be made completely out of two shorter words, do note that words can be repeated
     * <p>
     * Since a dictionary is provided, thought of leveraging trie to find the longest matching prefix present in dictionary
     * In my initial implementation, instead of returning boolean, I returned the longest matching prefix instead, and then checked whether the longest
     * matching prefix length equals the current word length. I have to resort to this because the word needs to be entirely composed of two or more shorter words.
     * <p>
     * It also did work, but this is a bit concise since I am passing the prefix count to the next recursion call
     * <p>
     * Related to {@link WordBreak1} but wordBreakOptimized() gives TLE, not sure why
     */
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Arrays.sort(words, (o1, o2) -> Integer.compare(o1.length(), o2.length())); //sorting is important
        ArrayList<String> result = new ArrayList<>();
        for (String word : words) {
            boolean isConcatenated = isConcatenated(word, 0, 0);
            if (isConcatenated) {
                result.add(word);
            }
            addWord(word);
        }
        return result;
    }

    private void addWord(String word) {
        Trie temp = root;
        for (char c : word.toCharArray()) {
            if (temp.nodes[c - 'a'] == null) {
                temp.nodes[c - 'a'] = new Trie();
            }
            temp = temp.nodes[c - 'a'];
        }
        temp.isEndOfWord = true;
    }

    private boolean isConcatenated(String word, int index, int prefixWordsFound) {
        if (index == word.length()) {
            return prefixWordsFound >= 2;
        }
        //cant short-circuit in between if the prefixWordsFound >= 2 in intermediate substring, need to make sure that when entire string is covered
        //it's composed of 2 or more words.
        Trie temp = root;
        for (int i = index; i < word.length(); i++) {
            char c = word.charAt(i);
            if (temp.nodes[c - 'a'] == null) { //character not in dictionary found, string can't be made from concatenated words
                return false;
            } else {
                temp = temp.nodes[c - 'a'];
                if (temp.isEndOfWord && isConcatenated(word, i + 1, prefixWordsFound + 1)) {
                    //if current prefix is a valid word and current suffix is also valid, this string is concatenated
                    return true;
                }
            }
        }
        return false;
    }

    private static class Trie {
        boolean isEndOfWord;
        Trie[] nodes = new Trie[26];
    }
}
