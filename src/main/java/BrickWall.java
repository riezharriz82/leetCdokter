import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/brick-wall/
 * <pre>
 * There is a brick wall in front of you. The wall is rectangular and has several rows of bricks. The bricks have the same height but different width.
 * You want to draw a vertical line from the top to the bottom and cross the least bricks.
 *
 * The brick wall is represented by a list of rows. Each row is a list of integers representing the width of each brick in this row from left to right.
 *
 * If your line go through the edge of a brick, then the brick is not considered as crossed.
 * You need to find out how to draw the line to cross the least bricks and return the number of crossed bricks.
 *
 * You cannot draw a line just along one of the two vertical edges of the wall, in which case the line will obviously cross no bricks.
 *
 * Input: [[1,2,2,1],
 *         [3,1,2],
 *         [1,3,2],
 *         [2,4],
 *         [3,1,2],
 *         [1,3,1,1]]
 * Output: 2
 * </pre>
 * Note:
 * The width sum of bricks in different rows are the same and won't exceed INT_MAX.
 * The number of bricks in each row is in range [1,10,000]. The height of wall is in range [1,10,000]. Total number of bricks of the wall won't exceed 20,000.
 */
public class BrickWall {
    /**
     * Approach: Greedy, Try to solve the problem by reducing the problem to 1 row. If there were only one row, where would you put the line?
     * At the end of the bricks? Because if you try to put in between the bricks, you will always get a suboptimal answer.
     * Now if we had multiple rows, we can vote for the index by placing a vote at the end of the brick.
     * The index with the most votes will cross the least amount of bricks.
     * <p>
     * {@link EmployeeFreeTime} {@link MinDominoRotationsForEqualRow} {@link MajorityElement} {@link CarFleet}
     */
    public int leastBricks(List<List<Integer>> wall) {
        //Don't use an integer array to count votes, because problem statement mentioned that the sum of length of bricks <= 10^9 which will cause Memory Limit Exceeded
        Map<Integer, Integer> lines = new HashMap<>(); //map of index -> votes
        for (List<Integer> row : wall) {
            int curIndex = 0;
            for (int i = 0; i < row.size() - 1; i++) { //notice we don't count vote for the last brick in the row as line can't be placed on the edge of the wall
                curIndex += row.get(i);
                lines.put(curIndex, lines.getOrDefault(curIndex, 0) + 1); //vote for the index at the end of the brick
            }
        }
        int maxVotes = 0;
        for (int line : lines.values()) {
            maxVotes = Math.max(maxVotes, line);
        }
        return wall.size() - maxVotes; //return the no of bricks intersected
    }
}
