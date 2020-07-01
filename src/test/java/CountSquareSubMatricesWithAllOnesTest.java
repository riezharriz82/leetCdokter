import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountSquareSubMatricesWithAllOnesTest {
    @Test
    public void test() {
        int[][] input = {
                {0, 1, 1, 1},
                {1, 1, 1, 1},
                {0, 1, 1, 1}
        };
        assertEquals(15, new CountSquareSubmatricesWithAllOnes().countSquares(input));

        input = new int[][]{
                {1, 0, 1},
                {1, 1, 0},
                {1, 1, 0}
        };
        assertEquals(7, new CountSquareSubmatricesWithAllOnes().countSquares(input));
    }
}
