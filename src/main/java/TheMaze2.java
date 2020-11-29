import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/the-maze-ii/
 * <p>
 * There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right,
 * but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.
 * <p>
 * Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination.
 * The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included).
 * If the ball cannot stop at the destination, return -1.
 * <p>
 * The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space.
 * You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.
 * <p>
 * Input 1: a maze represented by a 2D array
 * <p>
 * 0 0 1 0 0
 * 0 0 0 0 0
 * 0 0 0 1 0
 * 1 1 0 1 1
 * 0 0 0 0 0
 * <p>
 * Input 2: start coordinate (rowStart, colStart) = (0, 4)
 * Input 3: destination coordinate (rowDest, colDest) = (4, 4)
 * <p>
 * Output: 12
 * <p>
 * Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
 * The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.
 */
public class TheMaze2 {
    /**
     * Approach: Apply Djikstra since it's a weighted graph problem. BFS can also be applied but alongside pruning
     * BFS needs to visit the entire graph because the first time BFS reaches a cell, it's not guaranteed to be the shortest
     * path as the edges have different weights
     * <p>
     * {@link TheMaze} {@link PathWithMaxProbability} {@link CheapestFlightWithinKStop} related problem
     */

    int[] x_offsets = new int[]{0, 1, 0, -1};
    int[] y_offsets = new int[]{1, 0, -1, 0};

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        PriorityQueue<Node> pq = new PriorityQueue<>(((o1, o2) -> Integer.compare(o1.distance, o2.distance)));
        pq.add(new Node(start[0], start[1], 0));
        int[][] distance = new int[maze.length][maze[0].length];
        distance[start[0]][start[1]] = 0;
        for (int[] ints : distance) {
            Arrays.fill(ints, Integer.MAX_VALUE);
        }
        while (!pq.isEmpty()) {
            Node cur = pq.remove();
            if (cur.row == destination[0] && cur.col == destination[1]) { //destination reached, return the distance
                return cur.distance;
            }
            for (int i = 0; i < 4; i++) {
                int newRow = cur.row;
                int newCol = cur.col;
                int distanceTravelled = 0;
                //find the next cell at which the ball will stop, this is an optimization to what I have done in {TheMaze}
                while (newRow >= 0 && newRow < maze.length && newCol >= 0 && newCol < maze[0].length && maze[newRow][newCol] == 0) {
                    newRow += x_offsets[i];
                    newCol += y_offsets[i];
                    distanceTravelled++;
                }
                //if the ball can't travel in this direction, we are back to the current cell
                if (distanceTravelled == 1) {
                    continue;
                }
                //bring the cell to a valid state as we overshot by 1 in the while loop
                newRow -= x_offsets[i];
                newCol -= y_offsets[i];
                distanceTravelled -= 1;
                if (distance[newRow][newCol] > distanceTravelled + cur.distance) { //standard djiksktra stuff
                    //add a node to the pq only if adding this edge will reduce the distance required to reach new cell
                    pq.add(new Node(newRow, newCol, distanceTravelled + cur.distance));
                    distance[newRow][newCol] = distanceTravelled + cur.distance;
                }
            }
        }
        return -1;
    }

    private static class Node {
        int row;
        int col;
        int distance;

        public Node(int row, int col, int distance) {
            this.row = row;
            this.col = col;
            this.distance = distance;
        }
    }
}
