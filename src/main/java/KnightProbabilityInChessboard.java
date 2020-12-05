import java.util.Arrays;

/**
 * https://leetcode.com/problems/knight-probability-in-chessboard/
 * <p>
 * On an NxN chessboard, a knight starts at the r-th row and c-th column and attempts to make exactly K moves.
 * The rows and columns are 0 indexed, so the top-left square is (0, 0), and the bottom-right square is (N-1, N-1).
 * <p>
 * A chess knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.
 * <p>
 * Each time the knight is to move, it chooses one of eight possible moves uniformly at random
 * (even if the piece would go off the chessboard) and moves there.
 * <p>
 * The knight continues moving until it has made exactly K moves or has moved off the chessboard.
 * Return the probability that the knight remains on the board after it has stopped moving.
 * <p>
 * Input: 3, 2, 0, 0
 * Output: 0.0625
 * Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight on the board.
 * From each of those positions, there are also two moves that will keep the knight on the board.
 * The total probability the knight stays on the board is 0.0625.
 */
public class KnightProbabilityInChessboard {
    int[] x_offsets = new int[]{-2, -2, -1, 1, 2, 2, 1, -1};
    int[] y_offsets = new int[]{-1, 1, 2, 2, 1, -1, -2, -2};

    /**
     * Approach: DFS + memoization by keeping track of length so far and calculating the probability at each step
     * <p>
     * At each step, there is 1/8 probability of the knight being inside. So if there are n valid moves, total prob = n/8
     * <p>
     * Now if the knight makes another move from one of the valid moves, its probability would be 1/8 * (n'/8)
     * <p>
     * {@link KnightDialer} {@link OutOfBoundaryPaths} related recursive problems
     */
    public double knightProbability(int N, int K, int r, int c) {
        double[][][] memoized = new double[N][N][K];
        for (double[][] doubles : memoized) {
            for (double[] aDouble : doubles) {
                Arrays.fill(aDouble, -1);
            }
        }
        //visited array isn't required because even if knight comes back to its original position, steps are finite and it can't go on infinitely
        return DFS(N, K, r, c, memoized);
    }

    private double DFS(int n, int steps, int row, int column, double[][][] memoized) {
        if (row < 0 || row >= n || column < 0 || column >= n) { //if knight has fallen out of the grid
            return 0;
        } else if (steps == 0) { //if knight is inside but there are no further moves to make
            return 1;
        } else if (memoized[row][column][steps] != -1) {
            return memoized[row][column][steps];
        } else {
            double totalProbability = 0;
            for (int i = 0; i < 8; i++) {
                int new_row = row + x_offsets[i];
                int new_col = column + y_offsets[i];
                //each step has probability of 1/8 * probability of remaining steps
                totalProbability += 0.125D * DFS(n, steps - 1, new_row, new_col, memoized);
            }
            return memoized[row][column][steps] = totalProbability;
        }
    }
}
