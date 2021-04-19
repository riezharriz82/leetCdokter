/**
 * https://leetcode.com/problems/implement-trie-ii-prefix-tree/
 * <p>
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings.
 * There are various applications of this data structure, such as autocomplete and spellchecker.
 * <p>
 * Implement the Trie class:
 * 1. Trie() Initializes the trie object.
 * 2. void insert(String word) Inserts the string word into the trie.
 * 3. int countWordsEqualTo(String word) Returns the number of instances of the string word in the trie.
 * 4. int countWordsStartingWith(String prefix) Returns the number of strings in the trie that have the string prefix as a prefix.
 * 5. void erase(String word) Erases the string word from the trie.
 * <p>
 * Input
 * ["Trie", "insert", "insert", "countWordsEqualTo", "countWordsStartingWith", "erase", "countWordsEqualTo", "countWordsStartingWith", "erase", "countWordsStartingWith"]
 * [[], ["apple"], ["apple"], ["apple"], ["app"], ["apple"], ["apple"], ["app"], ["apple"], ["app"]]
 * Output
 * [null, null, null, 2, 2, null, 1, 1, null, 0]
 * <p>
 * Explanation
 * Trie trie = new Trie();
 * trie.insert("apple");               // Inserts "apple".
 * trie.insert("apple");               // Inserts another "apple".
 * trie.countWordsEqualTo("apple");    // There are two instances of "apple" so return 2.
 * trie.countWordsStartingWith("app"); // "app" is a prefix of "apple" so return 2.
 * trie.erase("apple");                // Erases one "apple".
 * trie.countWordsEqualTo("apple");    // Now there is only one instance of "apple" so return 1.
 * trie.countWordsStartingWith("app"); // return 1
 * trie.erase("apple");                // Erases "apple". Now the trie is empty.
 * trie.countWordsStartingWith("app"); // return 0
 * <p>
 * Constraints:
 * 1 <= word.length, prefix.length <= 2000
 * word and prefix consist only of lowercase English letters.
 * At most 3 * 10^4 calls in total will be made to insert, countWordsEqualTo, countWordsStartingWith, and erase.
 * It is guaranteed that for any function call to erase, the string word will exist in the trie.
 */
public class ImplementTrie2 {
    /**
     * Approach: Keep the required information directly in nodes of trie instead of doing DFS for every query.
     * This was the lesson learnt earlier. So we keep two additional attributes in a Trie Node, no of words that have the current prefix
     * and no of words that have the exact string.
     * <p>
     * When asked to erase a node from Trie, we can perform a deep delete in order to reduce memory usage. This will also improve
     * the time required to perform subsequent queries as there will be less nodes to traverse.
     * <p>
     * {@link ImplementTrie} {@link DesignSearchAutocompleteSystem}
     */
    Trie root = new Trie();

    public ImplementTrie2() {

    }

    public void insert(String word) {
        Trie temp = root;
        for (char c : word.toCharArray()) {
            if (temp.children[c - 'a'] == null) {
                temp.children[c - 'a'] = new Trie();
            }
            temp = temp.children[c - 'a'];
            temp.prefixWords++;
        }
        temp.exactWords++;
    }

    public int countWordsEqualTo(String word) {
        Trie temp = root;
        for (char c : word.toCharArray()) {
            temp = temp.children[c - 'a'];
            if (temp == null) {
                return 0;
            }
        }
        return temp.exactWords;
    }

    public int countWordsStartingWith(String prefix) {
        Trie temp = root;
        for (char c : prefix.toCharArray()) {
            temp = temp.children[c - 'a'];
            if (temp == null) {
                return 0;
            }
        }
        return temp.prefixWords;
    }

    public void erase(String word) {
        Trie temp = root;
        boolean deleteNode = false;
        for (char c : word.toCharArray()) {
            Trie next = temp.children[c - 'a'];
            next.prefixWords--;
            if (next.prefixWords == 0) { //once a prefix becomes invalid, all its subsequent prefixes will be invalid
                //ie. if there are no string starting with "app", we can guarantee that there will be no strings starting with "apple"
                deleteNode = true;
            }
            if (deleteNode) { //delete the reference of temp in the parent node.
                temp.children[c - 'a'] = null;
            }
            temp = next;
        }
        temp.exactWords--;
    }

    private static class Trie {
        Trie[] children = new Trie[26];
        int prefixWords;
        int exactWords;
    }
}
