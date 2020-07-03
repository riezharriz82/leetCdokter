import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class PrisonCellAfterNDaysTest {
    @Test
    public void test() {
        assertArrayEquals(new int[]{0, 0, 0, 0}, new PrisonCellAfterNDays().prisonAfterNDays(new int[]{0, 1, 0, 1}, 4));
        assertArrayEquals(new int[]{0, 0, 0, 0}, new PrisonCellAfterNDays().prisonAfterNDays(new int[]{0, 1, 0, 1}, 2));
        assertArrayEquals(new int[]{0, 0, 1, 1, 0, 0, 0, 0}, new PrisonCellAfterNDays().prisonAfterNDays(new int[]{0, 1, 0, 1, 1, 0, 0, 1}, 7));
        assertArrayEquals(new int[]{0, 0, 1, 1, 1, 1, 1, 0}, new PrisonCellAfterNDays().prisonAfterNDays(new int[]{1, 0, 0, 1, 0, 0, 1, 0}, 1000000000));
    }
}
