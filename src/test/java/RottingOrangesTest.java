import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RottingOrangesTest {
    @Test
    public void test() {
        assertEquals(4, new RottingOranges().orangesRotting(new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}}));
        assertEquals(-1, new RottingOranges().orangesRotting(new int[][]{{2, 1, 1}, {0, 1, 1}, {1, 0, 1}}));
        assertEquals(0, new RottingOranges().orangesRotting(new int[][]{{0, 2}}));
        assertEquals(2, new RottingOranges().orangesRotting(new int[][]{{2}, {1}, {1}, {1}, {2}, {1}, {1}}));
        assertEquals(0, new RottingOranges().orangesRotting(new int[][]{{0}}));
        assertEquals(1, new RottingOranges().orangesRotting(new int[][]{{2, 2}, {1, 1}, {0, 0}, {2, 0}}));
    }
}
