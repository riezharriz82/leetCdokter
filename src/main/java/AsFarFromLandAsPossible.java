import java.util.ArrayDeque;

/**
 * https://leetcode.com/problems/as-far-from-land-as-possible/
 * <p>
 * Given an n x n grid containing only values 0 and 1, where 0 represents water and 1 represents land, find a water cell
 * such that its distance to the nearest land cell is maximized, and return the distance. If no land or water exists in the grid, return -1.
 * <p>
 * The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.
 * <p>
 * Input: grid = [[1,0,1],[0,0,0],[1,0,1]]
 * Output: 2
 * Explanation: The cell (1, 1) is as far as possible from all the land with distance 2.
 * <p>
 * Input: grid = [[1,0,0],[0,0,0],[0,0,0]]
 * Output: 4
 * Explanation: The cell (2, 2) is as far as possible from all the land with distance 4.
 */
public class AsFarFromLandAsPossible {
    int[] x_offset = new int[]{0, 1, 0, -1};
    int[] y_offset = new int[]{1, 0, -1, 0};

    /**
     * Approach: Whenever asked to find distance from a set of points to another set of points, use multi source BFS
     * Put all the 1's in the queue and start BFS until all nodes are visited, keeping track of the smallest distance seen so far
     * <p>
     * {@link ShortestBridge} {@link PacificAtlanticWaterFlow} {@link SurroundedRegions} {@link RottingOranges} related problems
     */
    public int maxDistance(int[][] grid) {
        ArrayDeque<Node> queue = new ArrayDeque<>();
        int n = grid.length;
        boolean isLandPresent = false, isWaterPresent = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = -1;
                    queue.add(new Node(i, j, 0)); //push all lands in the queue to expand upon during BFS
                    isLandPresent = true;
                } else {
                    isWaterPresent = true;
                }
            }
        }
        if (isLandPresent && isWaterPresent) {
            int maxDistanceFound = 0;
            while (!queue.isEmpty()) {
                Node head = queue.remove();
                maxDistanceFound = Math.max(maxDistanceFound, head.distance); //keep track of the max distance seen
                for (int i = 0; i < 4; i++) {
                    int new_x = head.x + x_offset[i];
                    int new_y = head.y + y_offset[i];
                    if (new_x >= 0 && new_x < n && new_y >= 0 && new_y < n && grid[new_x][new_y] != -1) {
                        grid[new_x][new_y] = -1; //always mark the cell as visited before pushing it to the queue, not after popping it
                        int new_distance = head.distance + Math.abs(head.x - new_x) + Math.abs(head.y - new_y); //new distance is the manhattan distance
                        queue.add(new Node(new_x, new_y, new_distance));
                    }
                }
            }
            return maxDistanceFound;
        } else {
            //either no land or no water is found in the matrix
            return -1;
        }
    }

    private static class Node {
        int x;
        int y;
        int distance;

        public Node(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
}
