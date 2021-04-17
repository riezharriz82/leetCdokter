/**
 * https://leetcode.com/problems/implement-trie-prefix-tree/
 * <p>
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings.
 * There are various applications of this data structure, such as autocomplete and spellchecker.
 * <p>
 * Implement the Trie class:
 * 1. Trie() Initializes the trie object.
 * 2. void insert(String word) Inserts the string word into the trie.
 * 3. boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
 * 4. boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
 * <p>
 * Input
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * Output
 * [null, null, true, false, true, null, true]
 * <p>
 * Explanation
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // return True
 * trie.search("app");     // return False
 * trie.startsWith("app"); // return True
 * trie.insert("app");
 * trie.search("app");     // return True
 * <p>
 * Constraints:
 * 1 <= word.length, prefix.length <= 2000
 * word and prefix consist only of lowercase English letters.
 * At most 3 * 10^4 calls in total will be made to insert, search, and startsWith.
 */
public class ImplementTrie {
    private final TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public ImplementTrie() {
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

    private static class TrieNode {
        boolean isEndOfWord;
        TrieNode[] trieNodes = new TrieNode[26];
    }
}
