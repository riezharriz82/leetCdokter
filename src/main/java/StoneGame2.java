import java.util.HashMap;

/**
 * https://leetcode.com/problems/stone-game-ii/
 * <p>
 * Alex and Lee continue their games with piles of stones.  There are a number of piles arranged in a row,
 * and each pile has a positive integer number of stones piles[i].  The objective of the game is to end with the most stones.
 * <p>
 * Alex and Lee take turns, with Alex starting first.  Initially, M = 1.
 * <p>
 * On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M.  Then, we set M = max(M, X).
 * <p>
 * The game continues until all the stones have been taken.
 * <p>
 * Assuming Alex and Lee play optimally, return the maximum number of stones Alex can get.
 * <p>
 * Input: piles = [2,7,9,4,4]
 * Output: 10
 * Explanation:  If Alex takes one pile at the beginning, Lee takes two piles, then Alex takes 2 piles again.
 * Alex can get 2 + 4 + 4 = 10 piles in total. If Alex takes two piles at the beginning, then Lee can take all three piles left. In this case, Alex get 2 + 7 = 9 piles in total. So we return 10 since it's larger.
 * <p>
 * Constraints:
 * 1 <= piles.length <= 100
 * 1 <= piles[i] <= 10 ^ 4
 */
public class StoneGame2 {
    /**
     * Approach: Similar to other minimax game theory problems {@link PredictTheWinner}
     * This code can be optimized a bit but yeah it works !
     * Happy to code it on my own after spending time understanding minimax implementation in the linked problem
     */
    public int stoneGameII(int[] piles) {
        HashMap<String, Integer> memo = new HashMap<>();
        return recur(piles, 0, 1, true, memo);
    }

    private int recur(int[] piles, int index, int M, boolean findMax, HashMap<String, Integer> memo) {
        if (index == piles.length) {
            return 0;
        }
        String key = index + ":" + M + ":" + findMax; //brutal memoization
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        int curScore = 0, maxScore = 0, minScore = Integer.MAX_VALUE;
        for (int X = 1; X <= 2 * M && index + X - 1 < piles.length; X++) {
            curScore += piles[index + X - 1];
            int newM = Math.max(M, X);
            if (findMax) {
                //we are interested only in maximum score of first player, so after player1 has chosen a pile, add it to curScore and get the remaining score possible
                //notice the index has been moved to index + X and not index + 1, because we have picked X indices from index so far
                maxScore = Math.max(curScore + recur(piles, index + X, newM, !findMax, memo), maxScore);
            } else {
                //we are not interested in score of second player, we assume second player is playing optimally
                //so player1 will be left with the minimum score possible from the remaining state
                //do notice that we are not adding the curScore to minScore reached so far
                minScore = Math.min(minScore, recur(piles, index + X, newM, !findMax, memo));
            }
        }
        //as part of learnings after solving CanIWin, remember to memoize the input state received
        if (findMax) {
            memo.put(key, maxScore);
            return maxScore;
        } else {
            memo.put(key, minScore);
            return minScore;
        }
    }
}
