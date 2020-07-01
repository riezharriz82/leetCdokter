import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LargestPlusSignTest {
    @Test
    public void test() {
        assertEquals(3, new LargestPlusSign().orderOfLargestPlusSign(5, new int[][]{{3, 0}, {3, 3}}));
        assertEquals(2, new LargestPlusSign().orderOfLargestPlusSign(3, new int[][]{{0, 0}}));
        assertEquals(2, new LargestPlusSign().orderOfLargestPlusSign(5, new int[][]{{4, 2}}));
        assertEquals(1, new LargestPlusSign().orderOfLargestPlusSign(2, new int[][]{{}}));
        assertEquals(0, new LargestPlusSign().orderOfLargestPlusSign(1, new int[][]{{0, 0}}));
    }
}
