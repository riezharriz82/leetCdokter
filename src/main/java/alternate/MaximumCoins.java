package alternate;

import java.util.Scanner;

/**
 * https://codingcompetitions.withgoogle.com/kickstart/round/00000000001a0069/0000000000414a23
 * <p>
 * Mike has a square matrix with N rows and N columns. Cell (i,j) denotes the cell present at row i and column j.
 * Cell (1,1) denotes the top left corner of the matrix. Each cell has some amount of coins associated with it and Mike can collect them
 * only if he visits that cell. Ci,j represents the number of coins in cell with row i and column j.
 * From a cell (i,j), Mike can decide to go to cell (i+1,j+1) or cell (i-1,j-1),
 * as long as the cell lies within the boundaries of the matrix and has not been visited yet.
 * He can choose to start the journey from any cell and choose to stop at any point. Mike wants to maximize the number of coins he can collect.
 * Please help him determine the maximum number of coins he can collect.
 * <p>
 * Input
 * The first line of the input gives the number of test cases, T. T test cases follow. Each test case begins with a line containing the integer N.
 * The next N lines contain N integers each. The j-th integer in the i-th line represents the number of coins Ci,j in cell (i,j).
 * <p>
 * Output
 * For each test case, output one line containing Case #x: y, where x is the test case number (starting from 1) and y is the maximum number of coins
 * Mike can collect.
 * <p>
 * Input
 * 2
 * 3
 * 1 2 5
 * 3 6 1
 * 12 2 7
 * 5
 * 0 0 0 0 0
 * 1 1 1 1 0
 * 2 2 2 8 0
 * 1 1 1 0 0
 * 0 0 0 0 0
 * <p>
 * Output
 * Case #1: 14
 * Case #2: 9
 * <p>
 * In Sample Case #1, the maximum number of coins collected can be 14, if Mike follows this path: (1,1) -> (2,2) -> (3,3)
 * <p>
 * In Sample Case #2, the maximum number of coins collected can be 9, if Mike follows this path: (2,3) -> (3,4).
 */
public class MaximumCoins {
    /**
     * Approach: Mike can move only in diagonals, since all the value in diagonal in positive, we can just take the max value present
     * in the diagonal.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int test = 0; test < t; test++) {
            int val = scanner.nextInt();
            int[][] grid = new int[val][val];
            for (int i = 0; i < val; i++) {
                for (int j = 0; j < val; j++) {
                    grid[i][j] = scanner.nextInt();
                }
            }
            long[][] dp = new long[val][val];
            long result = 0;
            for (int i = 0; i < val; i++) {
                dp[0][i] = grid[0][i];
                result = Math.max(result, dp[0][i]); //man i forgot to update the result for first row and first col
                //what a stupid mistake dude !
            }
            for (int i = 0; i < val; i++) {
                dp[i][0] = grid[i][0];
                result = Math.max(result, dp[i][0]);
            }
            for (int i = 1; i < val; i++) {
                for (int j = 1; j < val; j++) {
                    dp[i][j] = dp[i - 1][j - 1] + grid[i][j];
                    result = Math.max(result, dp[i][j]);
                }
            }
            System.out.println("Case #" + (test + 1) + ": " + result);
        }
    }
}
