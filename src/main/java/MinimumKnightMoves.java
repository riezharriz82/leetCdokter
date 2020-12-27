import javafx.util.Pair;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/minimum-knight-moves/
 * <p>
 * In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].
 * <p>
 * A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.
 * <p>
 * Return the minimum number of steps needed to move the knight to the square [x, y].  It is guaranteed the answer exists.
 * <p>
 * Input: x = 2, y = 1
 * Output: 1
 * Explanation: [0, 0] → [2, 1]
 * <p>
 * Input: x = 5, y = 5
 * Output: 4
 * Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]
 */
public class MinimumKnightMoves {
    private final int[] x_offsets = new int[]{1, 2, 2, 1, -1, -2, -2, -1};
    private final int[] y_offsets = new int[]{2, 1, -1, -2, -2, -1, 1, 2};

    /**
     * Approach: BiDirectional BFS because normal BFS times out. Alternatively A* can also be used
     * <p>
     * Gotcha: If you add int[] in a set, it won't work because array.equals is based on object.equals() which only compares object references
     * <p>
     * This solution is the slowest, it can be optimized in O(1) using freaking maths or by BFS if we look only in first
     * quadrant as the result is symmetric.
     * <p>
     * {@link KnightDialer} {@link KnightProbabilityInChessboard} {@link JumpGame4} knight related problems
     */
    public int minKnightMoves(int x, int y) {
        if (x == 0 && y == 0) {
            return 0;
        }
        Set<Pair<Integer, Integer>> forwardQueue = new HashSet<>(); //remember to use set instead of dequeue because of contains() check
        Set<Pair<Integer, Integer>> backwardQueue = new HashSet<>();
        forwardQueue.add(new Pair<>(0, 0));
        backwardQueue.add(new Pair<>(x, y));
        int moves = 0;
        HashSet<Pair<Integer, Integer>> visited = new HashSet<>();
        visited.add(new Pair<>(0, 0));
        visited.add(new Pair<>(x, y));
        while (!forwardQueue.isEmpty()) {
            if (forwardQueue.size() > backwardQueue.size()) { //ensures that we alternate between forwardQueue and backwardQueue
                Set<Pair<Integer, Integer>> temp = forwardQueue;
                forwardQueue = backwardQueue;
                backwardQueue = temp;
            }
            Set<Pair<Integer, Integer>> nextLevel = new HashSet<>();
            for (Pair<Integer, Integer> node : forwardQueue) {
                for (int j = 0; j < 8; j++) {
                    int new_row = node.getKey() + x_offsets[j];
                    int new_col = node.getValue() + y_offsets[j];
                    Pair<Integer, Integer> candidate = new Pair<>(new_row, new_col);
                    if (backwardQueue.contains(candidate)) { //always check whether the neighbour is present in other queue or not
                        return moves + 1;
                    }
                    if (!visited.contains(candidate)) {
                        nextLevel.add(candidate);
                        visited.add(candidate);
                    }
                }
            }
            forwardQueue = nextLevel; //update the reference of current queue to point to nodes of next level
            moves++;
        }
        return moves;
    }
}
