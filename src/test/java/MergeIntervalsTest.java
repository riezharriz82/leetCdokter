import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MergeIntervalsTest {
    @Test
    public void test() {
        int[][] actual = new MergeIntervals().mergeSimplified(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}});
        assertArrayEquals(new int[]{1, 6}, actual[0]);
        assertArrayEquals(new int[]{8, 10}, actual[1]);
        assertArrayEquals(new int[]{15, 18}, actual[2]);
        assertEquals(3, actual.length);

        actual = new MergeIntervals().mergeSimplified(new int[][]{{1, 3}, {2, 4}, {3, 5}, {4, 6}});
        assertArrayEquals(new int[]{1, 6}, actual[0]);
        assertEquals(1, actual.length);

        actual = new MergeIntervals().mergeSimplified(new int[][]{{1, 4}, {2, 3}});
        assertArrayEquals(new int[]{1, 4}, actual[0]);
        assertEquals(1, actual.length);

        actual = new MergeIntervals().mergeSimplified(new int[][]{{2, 3}, {5, 5}, {2, 2}, {3, 4}, {5, 5}, {3, 4}});
        assertArrayEquals(new int[]{2, 4}, actual[0]);
        assertArrayEquals(new int[]{5, 5}, actual[1]);
        assertEquals(2, actual.length);

        actual = new MergeIntervals().mergeSimplified(new int[][]{{5, 5}, {1, 3}, {3, 5}, {4, 6}, {1, 1}, {3, 3}, {5, 6}, {3, 3}, {2, 4}, {0, 0}});
        assertArrayEquals(new int[]{0, 0}, actual[0]);
        assertArrayEquals(new int[]{1, 6}, actual[1]);

    }
}
