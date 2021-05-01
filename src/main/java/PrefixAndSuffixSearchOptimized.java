/**
 * https://leetcode.com/problems/prefix-and-suffix-search/
 * <p>
 * Design a special dictionary which has some words and allows you to search the words in it by a prefix and a suffix.
 * <p>
 * Implement the WordFilter class:
 * <p>
 * WordFilter(string[] words) Initializes the object with the words in the dictionary.
 * f(string prefix, string suffix) Returns the index of the word in the dictionary which has the prefix prefix and the suffix suffix.
 * If there is more than one valid index, return the largest of them. If there is no such word in the dictionary, return -1.
 * <p>
 * Input
 * ["WordFilter", "f"]
 * [[["apple"]], ["a", "e"]]
 * Output
 * [null, 0]
 * <p>
 * Explanation
 * WordFilter wordFilter = new WordFilter(["apple"]);
 * wordFilter.f("a", "e"); // return 0, because the word at index 0 has prefix = "a" and suffix = 'e".
 */
public class PrefixAndSuffixSearchOptimized {
    Trie root = new Trie();

    /**
     * Approach: Maintain reverse indexes i.e. apple can be the result for queries like "e|a", "le|a", "le|app", "ple|a"
     * So generate all possible permutations and insert it back in trie. This will optimize the time complexity for query()
     * but would increase time complexity for insert()
     * <p>
     * {@link DesignSearchAutocompleteSystem} for trie related problems
     */
    public PrefixAndSuffixSearchOptimized(String[] words) {
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                String suffix = word.substring(j); //generate all valid suffixes
                insertIntoTrie(suffix + "{" + word, i); //insert into trie "suffix{word"
            }
        }
    }

    private void insertIntoTrie(String word, int idx) {
        Trie temp = root;
        for (char c : word.toCharArray()) {
            if (temp.letters[c - 'a'] == null) {
                temp.letters[c - 'a'] = new Trie();
            }
            temp = temp.letters[c - 'a'];
            temp.index = idx; //store the highest index directly at all nodes to avoid performing DFS
        }
    }

    /**
     * Find the concatenated patten in trie by looking up in the reverse index
     */
    public int find(String prefix, String suffix) {
        String query = suffix + "{" + prefix;
        Trie temp = root;
        for (char c : query.toCharArray()) {
            temp = temp.letters[c - 'a'];
            if (temp == null) {
                return -1;
            }
        }
        return temp.index; //this will contain the largest index, since we are processing the pattern in increasing order of index
        //largest valid index will overwrite the smaller indexes in case of conflict.
    }

    private static class Trie {
        int index = -1;
        Trie[] letters = new Trie[27]; //27 because we have to insert { (delimiter) as well
    }
}
