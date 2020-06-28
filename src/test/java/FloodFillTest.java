import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class FloodFillTest {
    @Test
    public void test() {
        int[][] expectedOutput = {{2, 2, 2}, {2, 2, 0}, {2, 0, 1}};
        int[][] input = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        int[][] actualOutput = new FloodFill().floodFill(input, 1, 1, 2);
        System.out.println(Arrays.deepToString(actualOutput));
        for (int i = 0; i < input.length; i++) {
            assertArrayEquals(expectedOutput[i], actualOutput[i]);
        }
    }
}
