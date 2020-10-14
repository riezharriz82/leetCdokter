import javafx.util.Pair;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/robot-room-cleaner/
 * <p>
 * Given a robot cleaner in a room modeled as a grid.
 * Each cell in the grid can be empty or blocked.
 * The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.
 * When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.
 * Design an algorithm to clean the entire room using only the 4 given APIs shown below.
 * <p>
 * Input:
 * room = [
 * [1,1,1,1,1,0,1,1],
 * [1,1,1,1,1,0,1,1],
 * [1,0,1,1,1,1,1,1],
 * [0,0,0,1,0,0,0,0],
 * [1,1,1,1,1,1,1,1]
 * ],
 * row = 1,
 * col = 3
 * <p>
 * Explanation:
 * All grids in the room are marked by either 0 or 1.
 * 0 means the cell is blocked, while 1 means the cell is accessible.
 * The robot initially starts at the position of row=1, col=3.
 * <p>
 * Notes:
 * <p>
 * The input is only given to initialize the room and the robot's position internally. You must solve this problem "blindfolded".
 * In other words, you must control the robot using only the mentioned 4 APIs, without knowing the room layout and the initial robot's position.
 * <p>
 * The robot's initial position will always be in an accessible cell.
 * <p>
 * The initial direction of the robot will be facing up.
 * <p>
 * All accessible cells are connected, which means the all cells marked as 1 will be accessible by the robot.
 * <p>
 * Assume all four edges of the grid are all surrounded by wall.
 */
public class RobotRoomCleaner {
    Set<Pair<Integer, Integer>> visited = new HashSet<>();
    //need the offsets in a clockwise direction
    int[] x_offset = new int[]{0, 1, 0, -1};
    int[] y_offset = new int[]{1, 0, -1, 0};

    public void cleanRoom(Robot robot) {
        dfs(robot, new Pair<>(0, 0), 0);
    }

    /**
     * Approach: Awesome real life problem, I worked on it for a couple of hours and thought of multiple approaches like storing the coordinates along
     * with the direction as a key in the map along with thinking. I was able to think of the current backtrack solution but my approach was very crude
     * and I was unable to cleanly implement the coordinates of the next move given the current direction. I had the idea but couldn't fit the pieces together
     * <p>
     * Correctly identifying the new indices given the current index and direction is the crux to solve this problem
     */
    private void dfs(Robot robot, Pair<Integer, Integer> node, int direction) {
        if (!visited.contains(node)) {
            visited.add(node);
            int x = node.getKey();
            int y = node.getValue();
            robot.clean();
            for (int i = 0; i < 4; i++) { //this ensures we rotate in 4 directions
                //for e.g i = 0 will return the indices of next cell if robot goes straight, irrespective of current orientation
                //ie. if the current index is 3,4 and current direction is 0 and if robot goes straight it will lead to 3,5
                //but if the current index is 3,4 and current direction is 1 and if robot goes straight it will lead to 4,4

                //next three lines are the heart of the dfs logic
                //please note that we are not doing a plain dfs, ie. simply iterating over the offsets and incrementing the x/y indices
                //we need to carefully update the indices because the direction plays a very important role in figuring out the next index
                //e.g. if the current index is 0,0 and the current direction is up, next index would be 0,1
                //if the current index is 0,0 and the current direction is left, next index would be 0,-1
                int new_d = (direction + i) % 4;
                int new_x = x + x_offset[new_d];
                int new_y = y + y_offset[new_d];
                if (!visited.contains(new Pair<>(new_x, new_y)) && robot.move()) {
                    dfs(robot, new Pair<>(new_x, new_y), new_d);
                }
                robot.turnRight();
                //right hand rule in solving maze problems, turn right at the obstacle, and then go forward
            }
            //tricky backtrack, reset the robot to the location of the parent cell i.e. the cell which called this recur function
            robot.turnRight();
            robot.turnRight();
            robot.move();
            robot.turnRight();
            robot.turnRight();
        }
    }

    interface Robot {
        // Returns true if the cell in front is open and robot moves into the cell.
        // Returns false if the cell in front is blocked and robot stays in the current cell.
        boolean move();

        // Robot will stay in the same cell after calling turnLeft/turnRight.
        // Each turn will be 90 degrees.
        void turnLeft();

        void turnRight();

        // Clean the current cell.
        void clean();
    }
}
