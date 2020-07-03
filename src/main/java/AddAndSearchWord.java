/**
 * https://leetcode.com/problems/add-and-search-word-data-structure-design/
 * <p>
 * Design a data structure that supports the following two operations:
 * <p>
 * void addWord(word)
 * bool search(word)
 * search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.
 * <p>
 * Example:
 * <p>
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 */
public class AddAndSearchWord {
    TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public AddAndSearchWord() {
        root = new TrieNode();
    }

    /**
     * Adds a word into the data structure.
     */
    public void addWord(String word) {
        TrieNode start = root;
        for (int i = 0; i < word.length(); i++) {
            int offset = word.charAt(i) - 'a';
            if (start.nodes[offset] == null) {
                start.nodes[offset] = new TrieNode();
            }
            start = start.nodes[offset];
        }
        start.isEndOfWord = true;
    }

    /**
     * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
     */
    public boolean search(String word) {
        return dfs(root, word, 0);
    }

    private boolean dfs(TrieNode node, String word, int index) {
        TrieNode start = node;
        for (int i = index; i < word.length(); i++) {
            if (word.charAt(i) != '.') {
                int offset = word.charAt(i) - 'a';
                if (start.nodes[offset] == null) {
                    return false;
                }
                start = start.nodes[offset];
            } else {
                //need to try out all the valid child nodes
                for (int j = 0; j < 26; j++) {
                    if (start.nodes[j] != null) {
                        if (dfs(start.nodes[j], word, i + 1)) {
                            return true;
                            //if valid path exists from any child note, short circuit
                        }
                    }
                }
                //Important to have this check, if dfs from any of the children didn't return true, no valid path exists
                return false;
            }
        }
        return start.isEndOfWord;
    }

    class TrieNode {
        boolean isEndOfWord;
        TrieNode[] nodes = new TrieNode[26];
    }
}
