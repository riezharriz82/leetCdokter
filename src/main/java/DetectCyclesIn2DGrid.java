/**
 * https://leetcode.com/problems/detect-cycles-in-2d-grid/
 * <p>
 * Given a 2D array of characters grid of size m x n, you need to find if there exists any cycle consisting of the same value in grid.
 * <p>
 * A cycle is a path of length 4 or more in the grid that starts and ends at the same cell. From a given cell,
 * you can move to one of the cells adjacent to it - in one of the four directions (up, down, left, or right), if it has the same value of the current cell.
 * <p>
 * Also, you cannot move to the cell that you visited in your last move.
 * For example, the cycle (1, 1) -> (1, 2) -> (1, 1) is invalid because from (1, 2) we visited (1, 1) which was the last visited cell.
 * <p>
 * Return true if any cycle of the same value exists in grid, otherwise, return false.
 * <p>
 * Input: grid = [["a","a","a","a"],["a","b","b","a"],["a","b","b","a"],["a","a","a","a"]]
 * Output: true
 * Explanation: There are two valid cycles a->a, b->b
 */
public class DetectCyclesIn2DGrid {
    int[] x_offset = new int[]{0, 1, 0, -1};
    int[] y_offset = new int[]{1, 0, -1, 0};

    /**
     * Important thing to understand here is that cycle does not constitutes if the adjacent node is the parent of the current node
     * Do a DFS and keep track of the parent. If you find a node that has the same char value as the current node and is visited and is not the parent
     * you have find yourself a cycle
     */
    public boolean containsCycle(char[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!visited[i][j] && dfs(grid, i, j, -1, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    //represent the parent node as row * total no of columns + col
    private boolean dfs(char[][] grid, int row, int col, int parent, boolean[][] visited) {
        visited[row][col] = true;
        for (int i = 0; i < x_offset.length; i++) {
            int new_row = row + x_offset[i];
            int new_col = col + y_offset[i];
            if (new_row >= 0 && new_row < grid.length && new_col >= 0 && new_col < grid[0].length) { //valid new offsets
                if (grid[new_row][new_col] == grid[row][col]) { //neighbour should have the same value
                    if (visited[new_row][new_col] && new_row * grid[0].length + new_col != parent) {
                        //if neighbour is visited and is not the parent of the current node, cycle found
                        return true;
                    }
                    if (!visited[new_row][new_col]) {
                        //not visited, so visit it, if cycle found, short circuit
                        if (dfs(grid, new_row, new_col, row * grid[0].length + col, visited)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
