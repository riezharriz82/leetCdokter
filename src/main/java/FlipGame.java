import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/flip-game/
 * <p>
 * You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -,
 * you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and
 * therefore the other person will be the winner.
 * <p>
 * Write a function to compute all possible states of the string after one valid move.
 * <p>
 * Input: s = "++++"
 * Output:
 * <pre>
 * [
 *   "--++",
 *   "+--+",
 *   "++--"
 * ]
 * </pre>
 * Note: If there is no valid move, return an empty list [].
 */
public class FlipGame {
    /**
     * Approach: Need to generate all possibles results after only one move, not all the moves
     * So no need of recursion, just traverse the string and check presence of two consecutive ++
     */
    public List<String> generatePossibleNextMoves(String s) {
        ArrayList<String> result = new ArrayList<>();
        recur(s, result);
        return result;
    }

    private void recur(String s, ArrayList<String> result) {
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '+' && s.charAt(i + 1) == '+') {
                char[] c = s.toCharArray();
                c[i] = '-';
                c[i + 1] = '-';
                result.add(new String(c));
            }
        }
    }
}
