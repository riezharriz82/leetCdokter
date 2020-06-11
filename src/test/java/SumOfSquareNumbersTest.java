import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SumOfSquareNumbersTest {
    @Test
    public void test() {
        assertTrue(new SumOfSquareNumbers().twoPointerSolution(4));
        assertTrue(new SumOfSquareNumbers().twoPointerSolution(5));
        assertFalse(new SumOfSquareNumbers().twoPointerSolution(3));
    }
}
