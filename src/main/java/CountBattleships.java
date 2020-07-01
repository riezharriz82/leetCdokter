/**
 * https://leetcode.com/problems/battleships-in-a-board/
 * <p>
 * Given an 2D board, count how many battleships are in it. The battleships are represented with 'X's,
 * empty slots are represented with '.'s. You may assume the following rules:
 * <p>
 * You receive a valid board, made of only battleships or empty slots.
 * <p>
 * Battleships can only be placed horizontally or vertically. In other words, they can only be made of the shape 1xN (1 row, N columns)
 * or Nx1 (N rows, 1 column), where N can be of any size.
 * <p>
 * At least one horizontal or vertical cell separates between two battleships - there are no adjacent battleships.
 * <p>
 * Example:
 * X..X
 * ...X
 * ...X
 * In the above board there are 2 battleships.
 */
public class CountBattleships {
    int[] x_offset = new int[]{0, 1, 0, -1};
    int[] y_offset = new int[]{1, 0, -1, 0};

    public int countBattleshipsWithoutDFS(char[][] board) {
        int res = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'X') {
                    if (i > 0 && board[i - 1][j] == 'X') {
                        continue;
                    }
                    if (j > 0 && board[i][j - 1] == 'X') {
                        continue;
                    }
                    res++;
                }
            }
        }
        return res;
    }

    public int countBattleshipsWithDFS(char[][] board) {
        int res = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'X') {
                    res++;
                    dfs(board, i, j);
                }
            }
        }
        return res;
    }

    private void dfs(char[][] board, int row, int col) {
        board[row][col] = '.';
        for (int i = 0; i < x_offset.length; i++) {
            int new_row = row + x_offset[i];
            int new_col = col + y_offset[i];
            if (isValid(new_row, new_col, board.length, board[0].length) && board[new_row][new_col] == 'X') {
                dfs(board, new_row, new_col);
            }
        }

    }

    private boolean isValid(int new_rol, int new_col, int max_row, int max_col) {
        return new_rol >= 0 && new_rol < max_row && new_col >= 0 && new_col < max_col;
    }
}
