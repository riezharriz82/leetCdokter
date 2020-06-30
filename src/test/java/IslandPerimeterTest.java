import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IslandPerimeterTest {
    @Test
    public void test() {
        assertEquals(16, new IslandPerimeter().islandPerimeterWithoutDFS(new int[][]{
                {0, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 0, 0},
                {1, 1, 0, 0}}));

        assertEquals(4, new IslandPerimeter().islandPerimeterWithoutDFS(new int[][]{
                {1, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}}));
    }
}
