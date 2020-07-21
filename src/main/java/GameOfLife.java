/**
 * https://leetcode.com/problems/game-of-life/
 * <p>
 * According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by
 * the British mathematician John Horton Conway in 1970."
 * <p>
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its
 * eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
 * <p>
 * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population..
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * Write a function to compute the next state (after one update) of the board given its current state.
 * The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.
 *
 * <pre>
 * Input:
 * [
 *   [0,1,0],
 *   [0,0,1],
 *   [1,1,1],
 *   [0,0,0]
 * ]
 * Output:
 * [
 *   [0,0,0],
 *   [1,0,1],
 *   [0,1,1],
 *   [0,1,0]
 * ]
 * </pre>
 */
public class GameOfLife {
    /**
     * In order to reduce additional space required to store temporary array, we can encode the state change from 1 -> 0 & 0 -> 1
     * <p>
     * Also in case of very big board which contains very few live cells, storing the result in a 2d array is waste of space
     * Can instead only store the location of live cells in a set.
     * <p>
     * Also another way of solving this problem when the entire board can't fit into memory would be to just store into disk row by row
     * For each ith row, all the adjacent 8 nodes, lies in the i-1th row or i+1st row, we can only pull those row into memory and be done with it.
     */
    public void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        int[][] res = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int val = board[i][j];
                int aliveNeighbours = 0;
                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {
                        if (x == 0 && y == 0) {
                            continue;
                        }
                        int new_x = i + x;
                        int new_y = j + y;
                        if (new_x >= 0 && new_x < m && new_y >= 0 && new_y < n) {
                            if (board[new_x][new_y] == 1) {
                                aliveNeighbours++;
                            }
                        }
                    }
                }
                if (val == 0 && aliveNeighbours == 3) {
                    res[i][j] = 1; //rule 4
                } else if (val == 1 && aliveNeighbours < 2) {
                    res[i][j] = 0; //rule 1
                } else if (val == 1 && aliveNeighbours > 3) {
                    res[i][j] = 0;
                } else {
                    res[i][j] = board[i][j];
                }
            }
        }

        for (int i = 0; i < m; i++) {
            System.arraycopy(res[i], 0, board[i], 0, n);
        }
    }
}
