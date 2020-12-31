import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/design-a-leaderboard/ Premium
 * <p>
 * Design a Leaderboard class, which has 3 functions:
 * <p>
 * addScore(playerId, score): Update the leaderboard by adding score to the given player's score.
 * If there is no player with such id in the leaderboard, add him to the leaderboard with the given score.
 * <p>
 * top(K): Return the score sum of the top K players.
 * reset(playerId): Reset the score of the player with the given id to 0 (in other words erase it from the leaderboard). It is guaranteed that the player was added to the leaderboard before calling this function.
 * Initially, the leaderboard is empty.
 * <p>
 * Input:
 * ["Leaderboard","addScore","addScore","addScore","addScore","addScore","top","reset","reset","addScore","top"]
 * [[],[1,73],[2,56],[3,39],[4,51],[5,4],[1],[1],[2],[2,51],[3]]
 * Output:
 * [null,null,null,null,null,null,73,null,null,null,141]
 * <p>
 * Explanation:
 * Leaderboard leaderboard = new Leaderboard ();
 * leaderboard.addScore(1,73);   // leaderboard = [[1,73]];
 * leaderboard.addScore(2,56);   // leaderboard = [[1,73],[2,56]];
 * leaderboard.addScore(3,39);   // leaderboard = [[1,73],[2,56],[3,39]];
 * leaderboard.addScore(4,51);   // leaderboard = [[1,73],[2,56],[3,39],[4,51]];
 * leaderboard.addScore(5,4);    // leaderboard = [[1,73],[2,56],[3,39],[4,51],[5,4]];
 * leaderboard.top(1);           // returns 73;
 * leaderboard.reset(1);         // leaderboard = [[2,56],[3,39],[4,51],[5,4]];
 * leaderboard.reset(2);         // leaderboard = [[3,39],[4,51],[5,4]];
 * leaderboard.addScore(2,51);   // leaderboard = [[2,51],[3,39],[4,51],[5,4]];
 * leaderboard.top(3);           // returns 141 = 51 + 51 + 39;
 * <p>
 * Constraints:
 * 1 <= playerId, K <= 10000
 * It's guaranteed that K is less than or equal to the current number of players.
 * 1 <= score <= 100
 * There will be at most 1000 function calls.
 */
public class DesignALeaderboard {
    Map<Integer, Integer> map = new HashMap<>();
    TreeMap<Integer, Integer> scores = new TreeMap<>(Comparator.reverseOrder()); //score -> frequency, descending order

    /**
     * Approach: To maintain a mapping of player id to score, need a hashmap. To find the top k scores, need a priority queue
     * But priority queue supports deletion in O(n) time. As learnt earlier, treemap needs to be used instead of priority queue
     * where deletion is required. So we create another treemap of score to frequency in order to find the top k scores
     * <p>
     * {@link SlidingWindowMedian} {@link TopKFrequentElements} related problems
     */
    public DesignALeaderboard() {

    }

    //decrement frequency of existing score of the player and increment frequency of the new score of the player
    public void addScore(int playerId, int score) {
        int currentScore = map.getOrDefault(playerId, 0);
        if (currentScore > 0) { //decrement frequency
            scores.put(currentScore, scores.get(currentScore) - 1);
            scores.remove(currentScore, 0);
        }
        int newScore = currentScore + score;
        map.put(playerId, newScore);
        scores.put(newScore, scores.getOrDefault(newScore, 0) + 1); //increment frequency of the new score
    }

    public int top(int K) {
        int totalScore = 0;
        for (Map.Entry<Integer, Integer> entry : scores.entrySet()) { //iterate the map in order based on the comparator i.e. descending
            if (K < 0) {
                break;
            }
            int freq = entry.getValue();
            totalScore += entry.getKey() * Math.min(freq, K);
            K -= Math.min(freq, K);
        }
        return totalScore;
    }

    //decrement frequency of existing score of the player
    public void reset(int playerId) {
        Integer currentScore = map.get(playerId);
        scores.put(currentScore, scores.get(currentScore) - 1);
        scores.remove(currentScore, 0);
        map.remove(playerId);
    }
}
