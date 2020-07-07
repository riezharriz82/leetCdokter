import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PerfectSquaresTest {
    @Test
    public void test() {
        assertEquals(2, new PerfectSquares().numSquares(13));
        assertEquals(3, new PerfectSquares().numSquares(12));
    }
}
