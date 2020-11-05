/**
 * https://leetcode.com/problems/surrounded-regions/
 * <p>
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 * <p>
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 * <pre>
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * After running your function, the board should be:
 *
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * </pre>
 * Explanation:
 * <p>
 * Surrounded regions shouldn't be on the border, which means that any 'O' on the border of the board are not flipped to 'X'.
 * Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'.
 * Two cells are connected if they are adjacent cells connected horizontally or vertically.
 */
public class SurroundedRegions {
    int[] x_offset = new int[]{0, 1, 0, -1};
    int[] y_offset = new int[]{1, 0, -1, 0};

    /**
     * Approach: The trick to solve the problem was to understand that the 'O' nodes that can reach a 'O' node present in the border is not surrounded.
     * In my initial solution, I tried to do DFS from inner nodes and checked whether it can reach a 'O' node present in the border.
     * If that node is surrounded, mark that node and it's adjacent nodes as 'X' in another DFS.
     * However after creating a visited array to avoid checking the nodes again, solution gave WA.
     * It's because of circular dependency i.e. try to solve {@link PacificAtlanticWaterFlow} using this approach for the sample input
     * You will then find that the if a valid path is from 4 -> 4 so first 4 asks the other 4, can you reach the border
     * it says no, it marks that the other 4 can't reach border.
     * However other 4 could have reached border if it had went through the first 4.
     * But first 4 is already visited so it can't visit it again.
     * Too much fun !!
     * <p>
     * It can be simplified if we do a DFS from the reverse i.e start from border nodes and mark all the nodes that are connected to it.
     * Theory is same between the two approaches but the implementation is far easier.
     */
    public void solve(char[][] board) {
        int m = board.length;
        if (m == 0) {
            return;
        }
        int n = board[0].length;
        for (int j = 0; j < n; j++) { //first row and last row border
            markNodes(0, j, board, m, n);
            markNodes(m - 1, j, board, m, n);
        }
        for (int i = 0; i < m; i++) { //first col and last col border
            markNodes(i, 0, board, m, n);
            markNodes(i, n - 1, board, m, n);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '#') {
                    //reset them back to 'O' to match output
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    //mark them as surrounded
                    board[i][j] = 'X';
                }
            }
        }
    }

    //mark all nodes as # that are reachable from border 'O' nodes, these nodes can't be surrounded
    private void markNodes(int x, int y, char[][] board, int m, int n) {
        if (board[x][y] == 'O') {
            board[x][y] = '#';
            for (int i = 0; i < x_offset.length; i++) {
                int new_x = x + x_offset[i];
                int new_y = y + y_offset[i];
                if (new_x >= 0 && new_x < m && new_y >= 0 && new_y < n && board[new_x][new_y] == 'O') {
                    markNodes(new_x, new_y, board, m, n);
                }
            }
        }
    }
}
