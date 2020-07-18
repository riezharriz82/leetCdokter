import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/valid-sudoku/
 * <p>
 * Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 * <p>
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 */
public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        //instead of a list of set of characeters, we can simply create 2d arrays of 9*9 instead as sudoku has only 9 distinct characters
        List<Set<Character>> rowSet = new ArrayList<>(9);
        List<Set<Character>> colSet = new ArrayList<>(9);
        List<Set<Character>> subGridSet = new ArrayList<>(9);
        for (int i = 0; i < 9; i++) {
            rowSet.add(new HashSet<>());
            colSet.add(new HashSet<>());
            subGridSet.add(new HashSet<>());
        }
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == '.') {
                    continue;
                }
                if (!rowSet.get(row).add(board[row][col])) {
                    return false; //duplicate character for the row found
                }
                if (!colSet.get(col).add(board[row][col])) {
                    return false; //duplicate character for the column found
                }
                int subgridIndex = (row / 3) * 3 + (col / 3); //this is the most difficult part of the question
                if (!subGridSet.get(subgridIndex).add(board[row][col])) {
                    return false;
                }
            }
        }
        return true;
    }
}
