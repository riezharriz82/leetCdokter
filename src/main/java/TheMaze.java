/**
 * https://leetcode.com/problems/the-maze/
 * <p>
 * There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1).
 * The ball can go through the empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall.
 * When the ball stops, it could choose the next direction.
 * <p>
 * Given the maze, the ball's start position and the destination, where start = [startrow, startcol] and destination = [destinationrow, destinationcol],
 * return true if the ball can stop at the destination, otherwise return false.
 * <p>
 * You may assume that the borders of the maze are all walls (see examples).
 * <p>
 * Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [4,4]
 * Output: true
 * Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
 * <p>
 * Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [3,2]
 * Output: false
 * Explanation: There is no way for the ball to stop at the destination. Notice that you can pass through the destination but you cannot stop there.
 */
public class TheMaze {
    /**
     * Approach: Tricky thing to note here is that the ball can only change direction when it stops rolling.
     * It stops rolling when it can't move ahead in the current direction. Then try to change directions and see whether it can stop at target.
     * In all other places, it needs to move in the same direction and can't change direction.
     * <p>
     * In my initial implementation, I did a wrong implementation and tried to change direction in each cell and it got WA
     * After a couple of attempts, I did understood what I did wrong and fixed the implementation.
     * <p>
     * A simpler thing to do would be to instead doing a DFS from each cell, just find the last cell in which the ball will stop, given the initial
     * direction, the ball is rolling in. That would also give the min distance between source and target using BFS
     * <p>
     * {@link RobotRoomCleaner} {@link SpiralMatrix} {@link TheMaze2} related problem
     */
    int[] x_offsets = new int[]{0, 1, 0, -1};
    int[] y_offsets = new int[]{1, 0, -1, 0};

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        for (int i = 0; i < 4; i++) { //initially the ball can be pushed in any of the 4 directions
            boolean[][][] visited = new boolean[maze.length][maze[0].length][4];
            if (DFS(maze, start[0], start[1], destination[0], destination[1], i, visited)) {
                return true;
            }
        }
        return false;
    }

    private boolean DFS(int[][] maze, int cur_row, int cur_col, int end_row, int end_col, int direction, boolean[][][] visited) {
        if (cur_row == end_row && cur_col == end_col) {
            int new_row = cur_row + x_offsets[direction];
            int new_col = cur_col + y_offsets[direction];
            //if current cell is target cell, return true only if the ball cant move to the next cell
            if (!isNextMoveValid(maze, new_row, new_col) || maze[new_row][new_col] == 1) {
                return true;
            }
        }
        visited[cur_row][cur_col][direction] = true;
        int new_row = cur_row + x_offsets[direction];
        int new_col = cur_col + y_offsets[direction];
        if (!isNextMoveValid(maze, new_row, new_col) || maze[new_row][new_col] == 1) { //the ball can't move to the next cell
            //change directions and see if we can stop at the target cell
            for (int i = 1; i < 4; i++) {
                direction = (direction + i) % 4;
                new_row = cur_row + x_offsets[direction];
                new_col = cur_col + y_offsets[direction];
                if (isNextMoveValid(maze, new_row, new_col) && visited[new_row][new_col][direction]) {
                    //if next move is already visited, we will go in infinite loop, if we visit it again
                    continue;
                }
                if (isNextMoveValid(maze, new_row, new_col) && maze[new_row][new_col] == 0
                        && DFS(maze, new_row, new_col, end_row, end_col, direction, visited)) {
                    //if next move is a free cell and we are able to stop at the target cell
                    return true;
                }
            }
            //we are done changing all the directions, still can't stop at the target cell, return false
            return false;
        } else if (isNextMoveValid(maze, new_row, new_col) && visited[new_row][new_col][direction]) { //infinite loop detected
            return false;
        } else if (isNextMoveValid(maze, new_row, new_col) && maze[new_row][new_col] == 0) {
            //next cell is free, try to move in and see if we can stop at the target cell, no rotations allowed
            return DFS(maze, new_row, new_col, end_row, end_col, direction, visited);
        } else {
            return false;
        }
    }

    private boolean isNextMoveValid(int[][] maze, int new_row, int new_col) {
        return new_row >= 0 && new_row < maze.length && new_col >= 0 && new_col < maze[0].length;
    }
}
