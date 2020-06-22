import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Search2DMatrixIITest {
    @Test
    public void test() {
        int[][] input = {{1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}};
        assertTrue(new Search2DMatrixII().searchMatrix(input, 5));
        assertTrue(new Search2DMatrixII().searchMatrix(input, 17));
        assertFalse(new Search2DMatrixII().searchMatrix(input, 25));
    }
}
