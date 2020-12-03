/**
 * https://leetcode.com/problems/alphabet-board-path/
 * <p>
 * On an alphabet board, we start at position (0, 0), corresponding to character board[0][0].
 * <p>
 * Here, board = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"], as shown in the diagram below.
 * <p>
 * We may make the following moves:
 * <p>
 * 'U' moves our position up one row, if the position exists on the board;
 * 'D' moves our position down one row, if the position exists on the board;
 * 'L' moves our position left one column, if the position exists on the board;
 * 'R' moves our position right one column, if the position exists on the board;
 * '!' adds the character board[r][c] at our current position (r, c) to the answer.
 * (Here, the only positions that exist on the board are positions with letters on them.)
 * <p>
 * Return a sequence of moves that makes our answer equal to target in the minimum number of moves.  You may return any path that does so.
 * <p>
 * Input: target = "leet"
 * Output: "DDR!UURRR!!DDD!"
 */
public class AlphabetBoardPath {
    /**
     * Approach: Initially I thought I would have to perform BFS from previous char to current char and return the path
     * But on closer inspection, I realized BFS is not really required, since shortest path can always be computed from
     * directly comparing location of previous index vs current location.
     * <p>
     * If previous index is lower than current location, then we have to move UP diff times
     * If previous index is on the left of current location, then we have to move RIGHT diff times
     */
    public String alphabetBoardPath(String target) {
        int prev_row = 0, prev_col = 0;
        StringBuilder path = new StringBuilder();
        for (int i = 0; i < target.length(); i++) {
            //order is important here, if next char is Z, then we have to always go left first, and then down
            //So L and D have priority, if prev char is Z, then we always go up first, then right
            //So U and R are next
            //we can make special cases for Z to avoid this, look in my previous submission history for that
            int cur_val = target.charAt(i) - 'a';
            int cur_row = cur_val / 5, cur_col = cur_val % 5;
            if (cur_col < prev_col) {
                int diff = prev_col - cur_col;
                for (int j = 0; j < diff; j++) {
                    path.append("L");
                }
            }
            if (prev_row < cur_row) {
                int diff = cur_row - prev_row;
                for (int j = 0; j < diff; j++) {
                    path.append("D");
                }
            }
            if (cur_row < prev_row) {
                int diff = prev_row - cur_row;
                for (int j = 0; j < diff; j++) {
                    path.append("U");
                }
            }
            if (prev_col < cur_col) {
                int diff = cur_col - prev_col;
                for (int j = 0; j < diff; j++) {
                    path.append("R");
                }
            }
            path.append("!");
            prev_row = cur_row;
            prev_col = cur_col;
        }
        return path.toString();
    }
}
