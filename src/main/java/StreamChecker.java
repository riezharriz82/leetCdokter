import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/stream-of-characters/
 * <p>
 * Implement the StreamChecker class as follows:
 * <p>
 * StreamChecker(words): Constructor, init the data structure with the given words.
 * query(letter): returns true if and only if for some k >= 1, the last k characters queried (in order from oldest to newest,
 * including this letter just queried) spell one of the words in the given list.
 * <p>
 * StreamChecker streamChecker = new StreamChecker(["cd","f","kl"]); // init the dictionary.
 * streamChecker.query('a');          // return false
 * streamChecker.query('b');          // return false
 * streamChecker.query('c');          // return false
 * streamChecker.query('d');          // return true, because 'cd' is in the wordlist
 * streamChecker.query('e');          // return false
 * streamChecker.query('f');          // return true, because 'f' is in the wordlist
 * streamChecker.query('g');          // return false
 * streamChecker.query('k');          // return false
 * streamChecker.query('l');          // return true, because 'kl' is in the wordlist
 */
public class StreamChecker {
    private final List<Character> stream = new ArrayList<>();
    private final TrieNode root = new TrieNode();

    /**
     * reverse the word and insert into trie, reversal is important since in stream we are provided the last character
     */
    public StreamChecker(String[] words) {
        for (String word : words) {
            insertNode(reverseWord(word));
        }
    }

    /**
     * Standard trie insertion
     */
    private void insertNode(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            int character = word.charAt(i) - 'a';
            if (node.nodes[character] == null) {
                node.nodes[character] = new TrieNode();
            }
            node = node.nodes[character];
        }
        node.isEndOfWord = true;
    }

    private String reverseWord(String word) {
        char[] chars = word.toCharArray();
        int begin = 0, end = chars.length - 1;
        while (begin < end) {
            char temp = chars[begin];
            chars[begin] = chars[end];
            chars[end] = temp;
            begin++;
            end--;
        }
        return new String(chars);
    }

    private boolean findWord() {
        int index = stream.size() - 1;
        TrieNode head = root;
        while (head != null && index >= 0) { // repeat until we are out of characters from the stream or we are out of valid words in trie
            int requiredChar = stream.get(index) - 'a'; //keep getting the most recent added characters
            head = head.nodes[requiredChar];
            if (head != null && head.isEndOfWord) {
                //if you have find the word, return true
                return true;
            }
            index--;
        }
        return false;
    }

    public boolean query(char letter) {
        stream.add(letter);
        return findWord();
    }

    private static class TrieNode {
        boolean isEndOfWord;
        TrieNode[] nodes = new TrieNode[26];
    }
}
