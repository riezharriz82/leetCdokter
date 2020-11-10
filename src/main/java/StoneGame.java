import java.util.Arrays;

/**
 * https://leetcode.com/problems/stone-game/
 * <p>
 * Alex and Lee play a game with piles of stones.  There are an even number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].
 * <p>
 * The objective of the game is to end with the most stones.  The total number of stones is odd, so there are no ties.
 * <p>
 * Alex and Lee take turns, with Alex starting first.  Each turn, a player takes the entire pile of stones from either the beginning or the end of the row.
 * This continues until there are no more piles left, at which point the person with the most stones wins.
 * <p>
 * Assuming Alex and Lee play optimally, return True if and only if Alex wins the game.
 * <p>
 * Input: piles = [5,3,4,5]
 * Output: true
 * Explanation:
 * Alex starts first, and can only take the first 5 or the last 5.
 * Say he takes the first 5, so that the row becomes [3, 4, 5].
 * If Lee takes 3, then the board is [4, 5], and Alex takes 5 to win with 10 points.
 * If Lee takes the last 5, then the board is [3, 4], and Alex takes 4 to win with 9 points.
 * This demonstrated that taking the first 5 was a winning move for Alex, so we return true.
 * <p>
 * <p>
 * Constraints:
 * 2 <= piles.length <= 500
 * piles.length is even.
 * 1 <= piles[i] <= 500
 * sum(piles) is odd.
 */
public class StoneGame {
    /**
     * Approach:  Please refer {@link PredictTheWinner} for approach, this is a duplicate problem
     */
    public boolean stoneGame(int[] piles) {
        int totalStones = 0;
        for (int stones : piles) {
            totalStones += stones;
        }
        int n = piles.length;
        int[][] memo = new int[n][n];
        for (int[] ints : memo) {
            Arrays.fill(ints, -1);
        }
        int firstPlayerMaxStones = recur(piles, 0, piles.length - 1, memo);
        int secondPlayerMaxStones = totalStones - firstPlayerMaxStones;
        return firstPlayerMaxStones > secondPlayerMaxStones;
    }

    private int recur(int[] piles, int left, int right, int[][] memo) {
        if (left > right) {
            return 0;
        } else if (memo[left][right] != -1) {
            return memo[left][right];
        }
        int pickLeft = piles[left] + Math.min(recur(piles, left + 2, right, memo), recur(piles, left + 1, right - 1, memo));
        int pickRight = piles[right] + Math.min(recur(piles, left, right - 2, memo), recur(piles, left + 1, right - 1, memo));
        return memo[left][right] = Math.max(pickLeft, pickRight);
    }
}
