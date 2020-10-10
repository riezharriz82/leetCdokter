/**
 * https://leetcode.com/problems/dungeon-game/
 * <p>
 * The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon.
 * The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room
 * and must fight his way through the dungeon to rescue the princess.
 * <p>
 * The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.
 * <p>
 * Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms;
 * other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).
 * <p>
 * In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.
 * <p>
 * Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.
 * <p>
 * For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.
 * <pre>
 * -2 (K)	-3	    3
 * -5	    -10	    1
 * 10	     30	   -5 (P)
 * </pre>
 */
public class DungeonGame {
    /**
     * Approach: This is a tricky question similar to {@link BusRoutes}
     * The trick here is to start from the destination and reach the source, because this is what problem is asking for.
     * Min Health required at the start point -- Not the min health required at the destination point.
     * We can always identify the min health required at the destination in o(1) just by looking at the contents of the destination.
     * <p>
     * In my initial implementation, I started with a fancy DP by starting from the source to target by tracking both the current health
     * and max negative health of that path. However this led to WA because sometimes it was not clear on which path to choose, whether to maximise current health
     * or minimize the max negative health. It's very difficult to handle both the cases
     * https://leetcode.com/problems/dungeon-game/discuss/52774/C++-DP-solution/128083 one guy did posted a solution on doing that but it's full of
     * if's and else's
     */
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null) {
            return 0;
        }
        int[][] memoized = new int[dungeon.length][dungeon[0].length];
        return recur(dungeon, 0, 0, memoized);
    }

    private int recur(int[][] dungeon, int row, int col, int[][] memoized) {
        if (row >= dungeon.length || col >= dungeon[0].length) { //out of bounds
            return Integer.MAX_VALUE;
        }
        if (memoized[row][col] != 0) {
            return memoized[row][col];
        }
        if (row == dungeon.length - 1 && col == dungeon[0].length - 1) { //target state
            //if negative e.g -3, we need min 4 health before stepping in this cell
            //if positive e.g. 10, we need min 1 health before stepping in
            return memoized[row][col] = dungeon[row][col] >= 0 ? 1 : -dungeon[row][col] + 1;
        }
        int rightHealth = recur(dungeon, row, col + 1, memoized);
        int downHealth = recur(dungeon, row + 1, col, memoized);
        int minHealth = Math.min(rightHealth, downHealth);
        //this is a bit tricky, if the min health of the adjacent choices is 5 and current cell value is -3, we need min 8 health before stepping in this cell
        //if current cell value is 3, we need only 2 hp before stepping in this cell because this cell will give additional 3 hp
        //if current cell value is 5, we need only 1 hp before stepping in this cell because this cell will give everything required to reach next cell,
        int additionalHealthRequiredAtThisPoint = minHealth - dungeon[row][col];
        if (additionalHealthRequiredAtThisPoint <= 0) { //if this cell provides more than what is required
            additionalHealthRequiredAtThisPoint = 1;
        }
        return memoized[row][col] = additionalHealthRequiredAtThisPoint;
    }
}
