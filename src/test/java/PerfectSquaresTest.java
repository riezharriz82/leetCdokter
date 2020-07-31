import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PerfectSquaresTest {
    @Test
    public void test() {
        assertEquals(2, new PerfectSquares().numSquaresUsingBFS(13));
        assertEquals(3, new PerfectSquares().numSquaresUsingBFS(12));
    }
}
