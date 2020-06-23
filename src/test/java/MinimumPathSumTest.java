import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinimumPathSumTest {
    @Test
    public void test() {
        int[][] input = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        assertEquals(7, new MinimumPathSum().minPathSum(input));
    }
}
