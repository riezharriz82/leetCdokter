import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShortestPathInBinaryMatrixTest {
    @Test
    public void test() {
        assertEquals(4, new ShortestPathInBinaryMatrix().shortestPathBinaryMatrixUsingAStar(new int[][]{{0, 0, 0}, {1, 1, 0}, {1, 1, 0}}));
        assertEquals(11, new ShortestPathInBinaryMatrix().shortestPathBinaryMatrixUsingAStar(new int[][]
                {
                        {0, 0, 0, 0, 1, 1, 1, 1, 0},
                        {0, 1, 1, 0, 0, 0, 0, 1, 0},
                        {0, 0, 1, 0, 0, 0, 0, 0, 0},
                        {1, 1, 0, 0, 1, 0, 0, 1, 1},
                        {0, 0, 1, 1, 1, 0, 1, 0, 1},
                        {0, 1, 0, 1, 0, 0, 0, 0, 0},
                        {0, 0, 0, 1, 0, 1, 0, 0, 0},
                        {0, 1, 0, 1, 1, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 1, 0, 1, 0}
                }));
    }
}
