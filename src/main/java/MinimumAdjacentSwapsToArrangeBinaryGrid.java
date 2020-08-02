/**
 * https://leetcode.com/problems/minimum-swaps-to-arrange-a-binary-grid/
 * <p>
 * Given an n x n binary grid, in one step you can choose two adjacent rows of the grid and swap them.
 * <p>
 * A grid is said to be valid if all the cells above the main diagonal are zeros.
 * <p>
 * Return the minimum number of steps needed to make the grid valid, or -1 if the grid cannot be valid.
 * <p>
 * The main diagonal of a grid is the diagonal that starts at cell (1, 1) and ends at cell (n, n).
 * <p>
 * Input: grid = [[0,0,1],[1,1,0],[1,0,0]]
 * Output: 3
 */
public class MinimumAdjacentSwapsToArrangeBinaryGrid {
    /**
     * Count no of trailing zeroes for each row. Minimum no of zeroes at each row should be n - i - 1
     * If any row does not satisfy this criteria, look for the first row after i that satisfies this and bubble sort from that index till i
     * Keep track of number of swaps performed
     */
    public int minSwaps(int[][] grid) {
        int n = grid.length;
        int[] cntZeroes = new int[n];
        for (int i = 0; i < n; i++) {
            int zeroes = 0;
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 0) {
                    zeroes++;
                } else {
                    break;
                }
            }
            cntZeroes[i] = zeroes;
        }
        int answer = 0;
        for (int i = 0; i < n; i++) {
            if (cntZeroes[i] < n - i - 1) { //if no of zeroes are not sufficient
                int swaps = 0;
                for (int j = i + 1; j < n; j++) { // look for a valid row
                    if (cntZeroes[j] >= n - i - 1) {
                        while (j > i) { //swap till i
                            int temp = cntZeroes[j];
                            cntZeroes[j] = cntZeroes[j - 1];
                            cntZeroes[j - 1] = temp;
                            j--;
                            swaps++;
                        }
                        break;
                    }
                }
                if (swaps == 0) { //no such row found
                    return -1;
                } else {
                    answer += swaps;
                }
            }
        }
        return answer;
    }
}
