import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheapestFlightWithinKStopTest {
    @Test
    public void test() {
        assertEquals(6, new CheapestFlightWithinKStop().findCheapestPriceUsingDjikstra(4,
                new int[][]{{0, 1, 1}, {0, 2, 5}, {1, 2, 1}, {2, 3, 1}},
                0,
                3,
                1));
        assertEquals(5, new CheapestFlightWithinKStop().findCheapestPriceUsingBFS(5,
                new int[][]{{1, 2, 10}, {2, 0, 7}, {1, 3, 8}, {4, 0, 10}, {3, 4, 2}, {4, 2, 10}, {0, 3, 3}, {3, 1, 6}, {2, 4, 5}},
                0,
                4,
                1));
    }
}
