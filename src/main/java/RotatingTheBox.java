/**
 * <pre>
 * https://leetcode.com/problems/rotating-the-box/
 *
 * You are given an m x n matrix of characters box representing a side-view of a box. Each cell of the box is one of the following:
 *
 * A stone '#'
 * A stationary obstacle '*'
 * Empty '.'
 * The box is rotated 90 degrees clockwise, causing some of the stones to fall due to gravity. Each stone falls down until it lands on an obstacle, another stone, or the bottom of the box.
 * Gravity does not affect the obstacles' positions, and the inertia from the box's rotation does not affect the stones' horizontal positions.
 *
 * It is guaranteed that each stone in box rests on an obstacle, another stone, or the bottom of the box.
 *
 * Return an n x m matrix representing the box after the rotation described above.
 *
 * Input: box = [["#",".","#"]]
 * Output: [["."],
 *          ["#"],
 *          ["#"]]
 *
 * Input: box = [["#",".","*","."],
 *               ["#","#","*","."]]
 * Output: [["#","."],
 *          ["#","#"],
 *          ["*","*"],
 *          [".","."]]
 *
 * Input: box = [["#","#","*",".","*","."],
 *               ["#","#","#","*",".","."],
 *               ["#","#","#",".","#","."]]
 * Output: [[".","#","#"],
 *          [".","#","#"],
 *          ["#","#","*"],
 *          ["#","*","."],
 *          ["#",".","*"],
 *          ["#",".","."]]
 *
 * Constraints:
 * m == box.length
 * n == box[i].length
 * 1 <= m, n <= 500
 * box[i][j] is either '#', '*', or '.'.
 * </pre>
 */
public class RotatingTheBox {
    /**
     * Approach: Two pointers, First rotate the box and then use two pointers to simulate gravity.
     * One pointer (write) keeps track of where to put the stone and another keeps track of current cell value (read)
     * When you see a stationary obstacle at row x, update the write pointer to row (x-1)
     * When yoy see a stone, write the stone to the write pointer and decrement the write pointer.
     * <p>
     * {@link RotateImage} {@link TheMaze}
     */
    public char[][] rotateTheBox(char[][] box) {
        int m = box.length;
        int n = box[0].length;
        char[][] rotated = new char[n][m]; //notice the inverted bounds
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rotated[j][i] = box[i][j]; //transpose
            }
        }
        //perform reverse per row
        //remember matrix rotations are just a combination of transpose and reversal
        for (int i = 0; i < n; i++) {
            int low = 0, high = m - 1;
            while (low < high) {
                char temp = rotated[i][low];
                rotated[i][low] = rotated[i][high];
                rotated[i][high] = temp;
                low++;
                high--;
            }
        }
        //simulate gravity using two pointers
        for (int i = 0; i < m; i++) {
            int writeRow = n - 1, curRow = n - 1;
            while (curRow >= 0) {
                if (rotated[curRow][i] == '#') { //found a stone, move it to writeRow pointer location
                    rotated[curRow][i] = '.'; //clear current cell
                    rotated[writeRow][i] = '#';
                    writeRow--;
                } else if (rotated[curRow][i] == '*') { //found a stationary obstacle, reset the writeRow pointer to just above it.
                    writeRow = curRow - 1;
                }
                curRow--;
            }
        }
        return rotated;
    }
}
