/**
 * https://leetcode.com/problems/divisor-game/
 * <p>
 * Alice and Bob take turns playing a game, with Alice starting first.
 * <p>
 * Initially, there is a number N on the chalkboard.  On each player's turn, that player makes a move consisting of:
 * <p>
 * Choosing any x with 0 < x < N and N % x == 0.
 * Replacing the number N on the chalkboard with N - x.
 * Also, if a player cannot make a move, they lose the game.
 * <p>
 * Return True if and only if Alice wins the game, assuming both players play optimally.
 * <p>
 * Input: 2
 * Output: true
 * Explanation: Alice chooses 1, and Bob has no more moves.
 */
public class DivisorGame {
    /**
     * Approach: Recursion with Top Down memoization
     * Game theory problem, recursively try all possible combinations and store the results
     */
    public boolean divisorGame(int N) {
        int[] memoized = new int[N + 1];
        //1 denotes failure, 0 denotes not yet computed, 2 denotes success
        memoized[0] = 1;
        memoized[1] = 1;
        helper(N, memoized);
        return memoized[N] != 1;
    }

    private boolean helper(int N, int[] memoized) {
        if (memoized[N] != 0) {
            //if the result is computed, just use it
            return memoized[N] != 1;
        }
        for (int i = 1; i < N; i++) {
            //most important part, if i divides N and bob fails for the remaining N-i number, then we got a success
            if (N % i == 0 && !helper(N - i, memoized)) {
                //store the computed result and return
                memoized[N] = 2;
                return true;
            }
        }
        memoized[N] = 1;
        return false;
    }
}
