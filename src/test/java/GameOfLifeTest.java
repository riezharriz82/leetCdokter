import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class GameOfLifeTest {
    @Test
    public void test() {
        int[][] input = new int[][]{
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        };
        new GameOfLife().gameOfLife(input);

        int[][] expected = new int[][]{
                {0, 0, 0},
                {1, 0, 1},
                {0, 1, 1},
                {0, 1, 0}
        };
        Assertions.assertTrue(Arrays.deepEquals(expected, input));
    }
}
