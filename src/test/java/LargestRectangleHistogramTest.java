import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LargestRectangleHistogramTest {
    @Test
    public void test() {
        assertEquals(10, new LargestRectangleInHistogram().largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
        assertEquals(3, new LargestRectangleInHistogram().largestRectangleArea(new int[]{2, 1, 2}));
    }
}
