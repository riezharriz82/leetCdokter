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
                }

            }
        }

        return 0;
    }
}

