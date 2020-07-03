import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * https://leetcode.com/problems/word-search-ii/
 * <p>
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 * <p>
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally
 * or vertically neighboring. The same letter cell may not be used more than once in a word.
 * <p>
 * Input:
 * board =
 * <pre>
 * [
 *   ['o','a','a','n'],
 *   ['e','t','a','e'],
 *   ['i','h','k','r'],
 *   ['i','f','l','v']
 * ]
 * </pre>
 * words = ["oath","pea","eat","rain"]
 * <p>
 * Output: ["eat","oath"]
 */
public class WordSearch2 {
    int[] x_offset = new int[]{0, 1, 0, -1};
    int[] y_offset = new int[]{1, 0, -1, 0};

    public List<String> findWordsWithTrie(char[][] board, String[] words) {
        TrieNode root = new TrieNode();
        buildTrie(root, words);
        List<String> res = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(res, board, root, i, j);
            }
        }
        return res;
    }

    //This DFS walks the board as well as the trie simultaneously. After each step we check whether its a valid word or not.
    private void dfs(List<String> res, char[][] board, TrieNode root, int row, int col) {
        char val = board[row][col];
        if (root.nodes[val - 'a'] == null) { //no possible path exist
            return;
        }
        root = root.nodes[val - 'a']; //increment the trie here
        if (root.word != null) {
            res.add(root.word);
            //not returning because a longer path may exist
            root.word = null; //making it null to avoid duplicate in the result array
        }
        char originalVal = board[row][col];
        board[row][col] = '-';
        for (int i = 0; i < x_offset.length; i++) {
            int new_row = row + x_offset[i];
            int new_col = col + y_offset[i];
            //increment the grid here
            if (new_row >= 0 && new_row < board.length && new_col >= 0 && new_col < board[0].length && board[new_row][new_col] != '-') {
                dfs(res, board, root, new_row, new_col);
                //not short-circuiting because we need to travel in all the 4 directions even if we found a match in one direction
            }
        }
        board[row][col] = originalVal;
    }

    private void buildTrie(TrieNode root, String[] words) {
        for (String word : words) {
            TrieNode head = root; //start from root
            for (int i = 0; i < word.length(); i++) {
                int offset = word.charAt(i) - 'a';
                if (head.nodes[offset] == null) {
                    head.nodes[offset] = new TrieNode();
                }
                head = head.nodes[offset];
            }
            head.word = word;
        }
    }

    /**
     * Do DFS for each word
     * Time complexity : The time complexity will be O(4^(m * n * l * wl)) where n is board.length, m is board[0].length,
     * l is words.length and wl is the average of length of words in 'words'.
     */
    public List<String> findWords(char[][] board, String[] words) {
        HashSet<String> resultSet = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                char val = board[i][j];
                for (int k = 0; k < words.length; k++) {
                    //check whether the word is not already in the result set to avoid adding duplicates
                    if (!resultSet.contains(words[k]) && words[k].charAt(0) == val) {
                        if (dfs(board, words[k], 0, i, j)) {
                            resultSet.add(words[k]);
                        }
                    }
                }
            }
        }
        return new ArrayList<>(resultSet);
    }

    private boolean dfs(char[][] board, String word, int indexInWord, int row, int col) {
        if (indexInWord == word.length() - 1) {
            return true;
        }
        char original = board[row][col];
        board[row][col] = '-';
        for (int i = 0; i < x_offset.length; i++) {
            int new_row = row + x_offset[i];
            int new_col = col + y_offset[i];
            if (new_row >= 0 && new_row < board.length && new_col >= 0 && new_col < board[0].length
                    && board[new_row][new_col] == word.charAt(indexInWord + 1)) {
                if (dfs(board, word, indexInWord + 1, new_row, new_col)) {
                    board[row][col] = original;
                    return true;
                }
            }
        }
        board[row][col] = original;
        return false;
    }

    class TrieNode {
        String word; //helps in returning the entire word without retracing the steps
        TrieNode[] nodes = new TrieNode[26];
    }
}
