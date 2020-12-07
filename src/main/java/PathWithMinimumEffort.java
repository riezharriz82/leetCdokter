/**
 * https://leetcode.com/problems/path-with-minimum-effort/
 * <p>
 * You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns,
 * where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell
 * You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.
 * <p>
 * A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
 * <p>
 * Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
 * <p>
 * Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
 * Output: 2
 * Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
 * This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.
 * <p>
 * Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
 * Output: 1
 * Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].
 * <p>
 * Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
 * Output: 0
 * Explanation: This route does not require any effort.
 */
public class PathWithMinimumEffort {
    int[] x_offsets = new int[]{0, 1, 0, -1};
    int[] y_offsets = new int[]{1, 0, -1, 0};

    /**
     * Approach: Use Binary Search to find the path with the minimum effort
     * <p>
     * Other approaches include backtracking with pruning (TLE, avoid any path whose effort >= min effort found so far)
     * Djikstra, (Min Heap where max effort so far is the comparator, when we reach the target node, we are sure that we followed the path with min effort)
     * Union find (Maintain a decreasing sorted list of edges and their weight, keep performing union until source and target node are
     * part of the same component, the last edge weight to do so is the result, because weights are sorted in descending order)
     * <p>
     * {@link PathWithMaximumMinimumValue} related path finding problem in grid
     */
    public int minimumEffortPath(int[][] heights) {
        int low = 0, high = 1_000_000, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            boolean[][] visited = new boolean[heights.length][heights[0].length];
            visited[0][0] = true;
            if (pathWithEffortExists(mid, heights, 0, 0, visited)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private boolean pathWithEffortExists(int maxDiff, int[][] heights, int row, int col, boolean[][] visited) {
        for (int i = 0; i < 4; i++) {
            int new_row = row + x_offsets[i];
            int new_col = col + y_offsets[i];
            if (new_row >= 0 && new_row < heights.length && new_col >= 0 && new_col < heights[0].length
                    && Math.abs(heights[new_row][new_col] - heights[row][col]) <= maxDiff) { //ensure that we don't choose a path with effort > maxDiff
                visited[new_row][new_col] = true;
                if (pathWithEffortExists(maxDiff, heights, new_row, new_col, visited)) {
                    return true;
                }
            }
        }
        return false;
    }
}
