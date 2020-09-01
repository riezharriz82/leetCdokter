import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/knight-dialer/
 * <p>
 * The chess knight has a unique movement, it may move two squares vertically and one square horizontally,
 * or two squares horizontally and one square vertically (with both forming the shape of an L).
 * <p>
 * Given an integer n, return how many distinct phone numbers of length n we can dial.
 * <p>
 * You are allowed to place the knight on any numeric cell initially and then you should perform n - 1 jumps to dial a number of length n.
 * All jumps should be valid knight jumps.
 * <p>
 * As the answer may be very large, return the answer modulo 109 + 7.
 * <p>
 * {@link LetterCombinationsOfPhoneNumber}
 */
public class KnightDialer {
    private static final int MODULO = 1000_000_007;
    /**
     * I hard coded this but it can be generalized too
     * Read here https://leetcode.com/problems/knight-dialer/discuss/190787/How-to-solve-this-problem-explained-for-noobs!!!
     * <p>
     * Trick is to visualize the keypad as matrix and to plot the L shaped movement as variant of i,j just like we do in DFS
     * There are 8 possible moves, after offsetting i and j, check if they are valid move, else compute
     */
    List<List<Integer>> moveTo = Arrays.asList(Arrays.asList(4, 6),
            Arrays.asList(6, 8), Arrays.asList(7, 9), Arrays.asList(4, 8),
            Arrays.asList(0, 3, 9), Arrays.asList(), Arrays.asList(0, 1, 7),
            Arrays.asList(2, 6), Arrays.asList(1, 3), Arrays.asList(2, 4));

    /**
     * Very good read: https://hackernoon.com/google-interview-questions-deconstructed-the-knights-dialer-f780d516f029
     * Time Complexity of recursion: Every button have at least 2 neighbours to jump to, so if you visualize the path, it kinda becomes like a binary tree
     * So the time complexity would be at least 2^n (n being the length of steps)
     * <p>
     * After caching, the time complexity would be restricted to the size of cache as every value in the cache would be computed once
     * So the time complexity would be 10*n
     * <p>
     * Disadvantages of using top down dp with memoization vs bottom up dp -- top down approach eats up stack and since stack can grow only a finite amount,
     * it limits the max input possible, bottom up does not have this issue
     * <p>
     * Approach: Started with simple recursion then improved the run time by using memoization
     * <p>
     * Simulate the movement of knight by recursion, precompute the next neighbour of all digits
     */
    public int knightDialer(int n) {
        int result = 0;
        int[][] memoize = new int[10][n + 1];
        for (int i = 0; i < 10; i++) {
            //simulate the movement by considering all starting points
            result = (result + DFS(i, n, memoize)) % MODULO;
        }
        return result % MODULO;
    }

    private int DFS(int begin, int steps, int[][] memoize) {
        if (steps == 1) {
            return 1; // took me long time to implement the base case
        } else {
            if (memoize[begin][steps] != 0) {
                return memoize[begin][steps];
            }
            List<Integer> choices = moveTo.get(begin);
            int result = 0;
            for (int choice : choices) {
                result = (result + DFS(choice, steps - 1, memoize)) % MODULO;
            }
            return memoize[begin][steps] = (result % MODULO);
        }
    }
}
