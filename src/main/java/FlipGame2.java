import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/flip-game-ii/
 * <p>
 * You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -,
 * you and your friend take turns to flip two consecutive "++" into "--".
 * The game ends when a person can no longer make a move and therefore the other person will be the winner.
 * <p>
 * Write a function to determine if the starting player can guarantee a win.
 * <p>
 * Input: s = "++++"
 * Output: true
 * Explanation: The starting player can guarantee a win by flipping the middle "++" to become "+--+".
 */
public class FlipGame2 {
    /**
     * Approach: Simulating a game creates a tree, brute force solution is to generate the entire tree and see if the
     * current player can win
     * <p>
     * Generate all possible next states and check if the next player loses. If yes, then current player wins !
     * If no, then current player loses :(
     * <p>
     * Care must be taken to handle return values
     * <p>
     * {@link DivisorGame} similar can the starting player win problem
     */
    public boolean canWin(String s) {
        Map<String, Boolean> memo = new HashMap<>();
        return recur(s, memo);
    }

    private boolean recur(String s, Map<String, Boolean> memo) {
        if (memo.containsKey(s)) {
            return memo.get(s);
        }
        ArrayList<String> states = nextStates(s);
        if (states.isEmpty()) { //no next state possible, this player loses
            memo.put(s, false);
            return false; //signal loss of current player
        }
        for (String state : states) {
            if (!recur(state, memo)) { //check if the next player loses in the next round
                memo.put(s, true);
                return true; //signal victory of current player
            }
        }
        memo.put(s, false);
        return false; //signal loss of current player
    }

    private ArrayList<String> nextStates(String s) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '+' && s.charAt(i + 1) == '+') {
                char[] c = s.toCharArray();
                c[i] = '-';
                c[i + 1] = '-';
                result.add(new String(c));
            }
        }
        return result;
    }
}
