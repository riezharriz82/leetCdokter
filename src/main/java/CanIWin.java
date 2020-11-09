import java.util.Arrays;
import java.util.HashMap;

/**
 * https://leetcode.com/problems/can-i-win/
 * <p>
 * In the "100 game" two players take turns adding, to a running total, any integer from 1 to 10.
 * The player who first causes the running total to reach or exceed 100 wins.
 * <p>
 * What if we change the game so that players cannot re-use integers?
 * <p>
 * For example, two players might take turns drawing from a common pool of numbers from 1 to 15 without replacement until they reach a total >= 100.
 * <p>
 * Given two integers maxChoosableInteger and desiredTotal, return true if the first player to move can force a win, otherwise return false.
 * Assume both players play optimally.
 * <p>
 * Input: maxChoosableInteger = 10, desiredTotal = 11
 * Output: false
 * Explanation:
 * No matter which integer the first player choose, the first player will lose.
 * The first player can choose an integer from 1 up to 10.
 * If the first player choose 1, the second player can only choose integers from 2 up to 10.
 * The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
 * Same with other integers chosen by the first player, the second player will always win.
 */
public class CanIWin {
    /**
     * Approach: Game theory question, related to {@link DivisorGame} {@link FlipGame2}
     * Need to simulate the game by generating tree, This is a bit tough due to memoization of state and pre-filtering of invalid input
     * <p>
     * Each player tries to set a number and then see whether the second player loses or not. If yes, then the current player wins.
     * If the player tries setting all possible number and still the second player could not lose, then the current player loses.
     * <p>
     * State of the game is the choices each player have made so far, choices can be represented in boolean array whose string form
     * can act as a key
     * <p>
     * Very important to understand what needs to be put as the key in memoized map, it's not what choice led to victory or loss, but what
     * was the initial state before making that choice. Because that is what is the initial state of the board before the current player.
     */
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal == 0) {
            return true;
        }
        int maxTotal = (maxChoosableInteger * (maxChoosableInteger + 1)) / 2;
        //very important to filter out invalid inputs, otherwise lots of edge cases can give WA or TLE
        if (desiredTotal > maxTotal) {
            return false;
        }
        boolean[] chosen = new boolean[maxChoosableInteger + 1];
        HashMap<String, Boolean> map = new HashMap<>();
        return recur(chosen, maxChoosableInteger, desiredTotal, 0, map);
    }

    private boolean recur(boolean[] chosen, int maxChoosableInteger, int desiredTotal, int curSum, HashMap<String, Boolean> map) {
        String key = Arrays.toString(chosen); //very important to understand this is the key, that needs to be memoized
        //in my initial implementation, I tried to memoize the choice taken along with initial state as well but it gave WA
        if (map.containsKey(key)) {
            return map.get(key);
        }
        for (int i = 1; i <= maxChoosableInteger; i++) {
            //try choosing every available number iteratively and see if the current player can win by making the running sum >= target or other player loses
            //if none of them are possible, current player will loose.
            if (!chosen[i]) {
                if (i + curSum >= desiredTotal) {
                    map.put(key, true); //memoize the key here as well
                    return true;
                }
                chosen[i] = true;
                if (!recur(chosen, maxChoosableInteger, desiredTotal, curSum + i, map)) {
                    //standard game tree pattern, if the next player loses, I win
                    chosen[i] = false; //another gotcha, generally if we find a result in recursion, we just return true and call it a day
                    //but it's very critical to understand that this is one of the state, not the end state.
                    //because we don't know which player is this. If it's the second player and we say that second player won and return
                    //without resetting the board, first player can't pick up that choice again, so resetting this choice is critical
                    //in my initial implementation, I got WA because of this.
                    map.put(key, true); //memoize the key
                    return true; //current player wins, because next player loses
                }
                chosen[i] = false;
            }
        }
        map.put(key, false); //memoize the key
        return false; //current player loses, because either the next player wins in all possible scenarios or there is no valid case left
    }
}
