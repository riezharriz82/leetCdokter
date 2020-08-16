import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EraseNonOverlappingIntervalsTest {
    @Test
    public void test() {
        assertEquals(1, new EraseNonOverlappingIntervals().eraseOverlapIntervalsSimplified(new int[][]{{1, 10}, {1, 3}, {3, 5}, {5, 10}}));
        assertEquals(1, new EraseNonOverlappingIntervals().eraseOverlapIntervalsSimplified(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 3}}));
        assertEquals(2, new EraseNonOverlappingIntervals().eraseOverlapIntervalsSimplified(new int[][]{{1, 2}, {1, 2}, {1, 2}}));
    }
}
