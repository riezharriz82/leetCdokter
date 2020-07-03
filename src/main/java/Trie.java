/**
 * https://leetcode.com/problems/implement-trie-prefix-tree/
 * <p>
 * Implement a trie with insert, search, and startsWith methods.
 * <p>
 * Example:
 * <p>
 * Trie trie = new Trie();
 * <p>
 * trie.insert("apple");
 * trie.search("apple");   // returns true
 * trie.search("app");     // returns false
 * trie.startsWith("app"); // returns true
 * trie.insert("app");
 * trie.search("app");     // returns true
 */
class TrieNode {
    boolean isEndOfWord;
    TrieNode[] trieNodes = new TrieNode[26]; //the problem statement specifies that the word will only contain lowercase letters
    //if we want to make it generic we can probably choose an hashmap as key value store
}

public class Trie {
    private final TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        root = new TrieNode();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        int index = 0;
        TrieNode head = root;
        while (index < word.length()) {
            int offset = word.charAt(index) - 'a';
            if (head.trieNodes[offset] == null) {
                head.trieNodes[offset] = new TrieNode();
            }
            index++;
            head = head.trieNodes[offset];
        }
        head.isEndOfWord = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        int index = 0;
        TrieNode head = root;
        while (index < word.length()) {
            int offset = word.charAt(index) - 'a';
            if (head.trieNodes[offset] == null) {
                return false;
            } else {
                index++;
                head = head.trieNodes[offset];
            }
        }
        return head.isEndOfWord;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        int index = 0;
        TrieNode head = root;
        while (index < prefix.length()) {
            int offset = prefix.charAt(index) - 'a';
            if (head.trieNodes[offset] == null) {
                return false;
            } else {
                index++;
                head = head.trieNodes[offset];
            }
        }
        return true;
    }
}
