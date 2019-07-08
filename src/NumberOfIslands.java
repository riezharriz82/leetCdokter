import javafx.util.Pair;

import java.util.LinkedList;

/**
 * https://leetcode.com/problems/number-of-islands/
 */
public class NumberOfIslands {

    public int numIslands(char[][] grid) {
        int horLength = grid[0].length;
        int vertLength = grid.length;
        int[][] visited = new int[vertLength][horLength];

        int result = 0;
        for (int i = 0; i < vertLength; i++) {
            for (int j = 0; j < horLength; j++) {
                if (grid[i][j] == 1 && visited[i][j] == 0) { //if its a land not yet visited
                    //do a bfs and mark all the adjacent lands
                    result++;
                    bfs(grid, visited, i, j);
                }

            }
        }

        return 0;
    }

    private void bfs(char[][] grid, int[][] visited, int i, int j) {
        LinkedList<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(i,j));
        while(!queue.isEmpty()) {
            Pair<Integer, Integer> head = queue.remove();
            for (int )
        }
    }
}

