/**
 * https://leetcode.com/problems/word-search/
 * <p>
 * Given a 2D board and a word, find if the word exists in the grid.
 * <p>
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 * <p>
 * Example:
 * <p>
 * board =
 * <pre>
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 * </pre>
 * <p>
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 */
public class WordSearch {
    int[] x_offset = new int[]{0, 1, 0, -1};
    int[] y_offset = new int[]{1, 0, -1, 0};

    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (doDFS(board, i, j, word, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean doDFS(char[][] board, int row, int col, String word, int wordIndex) {
        if (wordIndex == word.length() - 1) { //all the words have been found
            return true;
        }
        char original = board[row][col];
        board[row][col] = '-'; //change to an invalid character
        for (int i = 0; i < x_offset.length; i++) {
            int new_row = row + x_offset[i];
            int new_col = col + y_offset[i];
            if (isValid(new_row, new_col, board.length, board[0].length) && board[new_row][new_col] == word.charAt(wordIndex + 1)) {
                if (doDFS(board, new_row, new_col, word, wordIndex + 1)) {
                    board[row][col] = original; //important to switch back the original character so that it can be used again from a different path
                    return true;
                }
            }
        }
        board[row][col] = original; //important to switch back the original character
        return false;
    }

    private boolean isValid(int new_rol, int new_col, int max_row, int max_col) {
        return new_rol >= 0 && new_rol < max_row && new_col >= 0 && new_col < max_col;
    }
}
