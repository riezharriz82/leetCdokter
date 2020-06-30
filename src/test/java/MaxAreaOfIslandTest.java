import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaxAreaOfIslandTest {
    @Test
    public void test() {
        int[][] input = {{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};
        assertEquals(6, new MaxAreaOfIsland().maxAreaOfIsland(input));
        assertEquals(0, new MaxAreaOfIsland().maxAreaOfIsland(new int[][]{{0, 0, 0, 0, 0}}));
        assertEquals(4, new MaxAreaOfIsland().maxAreaOfIsland(new int[][]{{1, 1, 1}, {1, 0, 0}}));
    }
}
