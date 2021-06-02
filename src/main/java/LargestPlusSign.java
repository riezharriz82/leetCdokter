import java.util.Arrays;

/**
 * https://leetcode.com/problems/largest-plus-sign/
 * <p>
 * In a 2D grid from (0, 0) to (N-1, N-1), every cell contains a 1, except those cells in the given list mines which are 0.
 * What is the largest axis-aligned plus sign of 1s contained in the grid? Return the order of the plus sign. If there is none, return 0.
 * <p>
 * An "axis-aligned plus sign of 1s of order k" has some center grid[x][y] = 1 along with 4 arms of length k-1
 * going up, down, left, and right, and made of 1s. This is demonstrated in the diagrams below.
 * Note that there could be 0s or 1s beyond the arms of the plus sign, only the relevant area of the plus sign is checked for 1s.
 * <p>
 * Order 1:
 * 000
 * 010
 * 000
 * <p>
 * Order 2:
 * 00000
 * 00100
 * 01110
 * 00100
 * 00000
 */
public class LargestPlusSign {
    /**
     * Approach: DP, Keep track of count of consecutive 1's in each direction at each cell ie. what's the length of consecutive 1's in left/right/top/bottom direction
     * for every cell.
     * <p>
     * {@link ZeroOneMatrix} similar problem
     */
    public int orderOfLargestPlusSign(int N, int[][] mines) {
        int[][] grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(grid[i], 1);
        }
        int res = 0;
        for (int i = 0; i < mines.length; i++) {
            if (mines[i].length == 0) {
                break;
            }
            int x = mines[i][0];
            int y = mines[i][1];
            grid[x][y] = 0;
        }
        //need to find the longest possible arm from all 4 directions ending at each cell, once done, result for each cell would be min of those 4 values
        //left to right direction
        for (int i = 0; i < N; i++) {
            int cnt = 1;
            for (int j = 0; j < N; j++) {
                if (grid[i][j] != 0) {
                    grid[i][j] = cnt;
                    cnt++;
                } else {
                    cnt = 1; //reset the count when 0 is encountered
                }
            }
        }
        System.out.println("left " + Arrays.deepToString(grid));
        //right to left direction
        for (int i = 0; i < N; i++) {
            int cnt = 1;
            for (int j = N - 1; j >= 0; j--) {
                if (grid[i][j] != 0) {
                    grid[i][j] = Math.min(grid[i][j], cnt);
                    cnt++;
                } else {
                    cnt = 1;
                }
            }
        }
        System.out.println("right " + Arrays.deepToString(grid));
        //top to bottom direction
        for (int j = 0; j < N; j++) {
            int cnt = 1;
            for (int i = 0; i < N; i++) {
                if (grid[i][j] != 0) {
                    grid[i][j] = Math.min(grid[i][j], cnt);
                    cnt++;
                } else {
                    cnt = 1;
                }
            }
        }
        System.out.println("top to bottom " + Arrays.deepToString(grid));
        //bottom to top direction
        for (int j = 0; j < N; j++) {
            int cnt = 1;
            for (int i = N - 1; i >= 0; i--) {
                if (grid[i][j] != 0) {
                    grid[i][j] = Math.min(grid[i][j], cnt);
                    cnt++;
                } else {
                    cnt = 1;
                }
            }
        }
        System.out.println("bottom to top" + Arrays.deepToString(grid));
        //calculate the max value present in all 4 directions
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                res = Math.max(grid[i][j], res);
            }
        }
        return res;

    }
}
