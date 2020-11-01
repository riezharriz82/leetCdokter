import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/sudoku-solver/
 * <p>
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * <p>
 * A sudoku solution must satisfy all of the following rules:
 * <p>
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 * The '.' character indicates empty cells.
 * <p>
 * Input: board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
 * Output: [["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","5","2","8","6","1","7","9"]]
 * Explanation: The input board is shown above and the only valid solution is shown below:
 */
public class SudokuSolver {
    /**
     * Approach: Good old backtracking. Try to place a number on a free cell and recur. If recur fails, then try to place another number on the cell and recur.
     * Care must be taken to properly reset all the associated data structures.
     * Spent a lot of time debugging because I failed to add back the poppedIndex in case current cell can't be assigned any number
     * <p>
     * {@link ValidSudoku} for sudoku related problems
     */
    public void solveSudoku(char[][] board) {
        ArrayList<HashSet<Integer>> columns = new ArrayList<>(9);
        ArrayList<HashSet<Integer>> rows = new ArrayList<>(9);
        ArrayList<HashSet<Integer>> subGrid = new ArrayList<>(9);
        for (int i = 0; i < 9; i++) {
            columns.add(new HashSet<>());
            rows.add(new HashSet<>());
            subGrid.add(new HashSet<>());
        }
        LinkedList<Pair<Integer, Integer>> emptyIndices = new LinkedList<>(); //not really required
        // in my initial implementation i just iterated over the entire grid and found the first empty cell
        // since the grid is 9*9 it really does not improve the run time but it cost me a lot of debugging time
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    columns.get(j).add(board[i][j] - '0');
                    rows.get(i).add(board[i][j] - '0');
                    int subgridIndex = (i / 3) * 3 + (j / 3);
                    subGrid.get(subgridIndex).add(board[i][j] - '0');
                } else {
                    emptyIndices.add(new Pair<>(i, j));
                }
            }
        }
        recur(board, columns, rows, subGrid, emptyIndices);
    }

    private boolean recur(char[][] board, ArrayList<HashSet<Integer>> columns, ArrayList<HashSet<Integer>> rows,
                          ArrayList<HashSet<Integer>> subGrid, LinkedList<Pair<Integer, Integer>> emptyIndices) {
        if (emptyIndices.isEmpty()) {
            return true;
        }
        Pair<Integer, Integer> emptyIndex = emptyIndices.removeFirst();
        int emptyRow = emptyIndex.getKey();
        int emptyCol = emptyIndex.getValue();
        for (int candidate = 1; candidate <= 9; candidate++) { //try to assign candidates incrementally
            if (isSafe(columns, rows, subGrid, emptyRow, emptyCol, candidate)) {
                //if it's safe to assign, update the board and block the candidate from getting assigned to same row, column and subgrid
                board[emptyRow][emptyCol] = (char) ('0' + candidate);
                columns.get(emptyCol).add(candidate);
                rows.get(emptyRow).add(candidate);
                int subgridIndex = (emptyRow / 3) * 3 + (emptyCol / 3);
                subGrid.get(subgridIndex).add(candidate);
                if (recur(board, columns, rows, subGrid, emptyIndices)) {
                    return true;
                }
                //oops, assignment failed, need to backtrack
                board[emptyRow][emptyCol] = '.';
                columns.get(emptyCol).remove(candidate);
                rows.get(emptyRow).remove(candidate);
                subGrid.get(subgridIndex).remove(candidate);
            }
        }
        emptyIndices.addFirst(emptyIndex); //add this index back to the emptyIndices
        //oops, none of the candidates can lead to a successful assignment at this cell, need to backtrack and change previously set candidates
        return false;
    }

    //safe to assign if the candidate is not present in the same row or column or subGrid
    private boolean isSafe(ArrayList<HashSet<Integer>> columns, ArrayList<HashSet<Integer>> rows, ArrayList<HashSet<Integer>> subGrid,
                           int emptyRow, int emptyCol, int candidate) {
        int subgridIndex = (emptyRow / 3) * 3 + (emptyCol / 3);
        if (columns.get(emptyCol).contains(candidate)) {
            return false;
        } else if (rows.get(emptyRow).contains(candidate)) {
            return false;
        } else return !subGrid.get(subgridIndex).contains(candidate);
    }
}
