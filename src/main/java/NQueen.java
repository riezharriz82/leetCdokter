import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * https://leetcode.com/problems/n-queens/
 * <p>
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 * <p>
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * <p>
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
 * <p>
 * Input: 4
 * Output:
 * <pre>
 * [
 *  [".Q..",  // Solution 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // Solution 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * </pre>
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
 */
public class NQueen {
    //Time Complexity n! as we generate all possible combinations
    // Try to place queen in a safe column of a row, after doing that recur for remaining queens.
    // After recursion don't forget to backtrack
    // If all queens are placed, store the result
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for (char[] rows : board) {
            Arrays.fill(rows, '.');
        }
        List<List<String>> res = new ArrayList<>();
        HashSet<Integer> columns = new HashSet<>();
        HashSet<Integer> diagonal1 = new HashSet<>();
        HashSet<Integer> diagonal2 = new HashSet<>();

        DFS(0, board, n, res, 0, columns, diagonal1, diagonal2);
        return res;
    }

    private void DFS(int queensPlaced, char[][] board, int queensRequired, List<List<String>> result, int row,
                     HashSet<Integer> columns, HashSet<Integer> diagonal1, HashSet<Integer> diagonal2) {
        if (queensPlaced == queensRequired) {
            List<String> stateOfBoard = new ArrayList<>();
            for (char[] rows : board) {
                stateOfBoard.add(new String(rows));
            }
            result.add(stateOfBoard);
        } else {
            for (int i = 0; i < board.length; i++) {
                //try to put queen in one of the columns of this row and recur
                if (!columns.contains(i) && !diagonal1.contains(row - i) && !diagonal2.contains(row + i)) {
                    columns.add(i);
                    //very neat trick, all the elements of diagonal follow this property, so we can leverage this to avoid iterating
                    // the diagonal elements to check for presence of queen
                    diagonal1.add(row - i);
                    diagonal2.add(row + i);
                    board[row][i] = 'Q';
                    DFS(queensPlaced + 1, board, queensRequired, result, row + 1, columns, diagonal1, diagonal2);
                    board[row][i] = '.'; //backtrack
                    columns.remove(i);
                    diagonal1.remove(row - i);
                    diagonal2.remove(row + i);
                }
            }
        }
    }

    //not required because of hashset
    private boolean isSafe(char[][] board, int row, int col) {
        //check current column
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }
        //no need to check for diagonal path lying after row as it has not been finalized yet
        for (int i = row, j = col; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }
}
