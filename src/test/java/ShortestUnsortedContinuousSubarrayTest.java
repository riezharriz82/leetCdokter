import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShortestUnsortedContinuousSubarrayTest {
    @Test
    public void test() {
        assertEquals(5, new ShortestUnsortedContinuousSubarray().findUnsortedSubarrayInLinearTime(new int[]{2, 6, 4, 8, 10, 9, 15}));
        assertEquals(0, new ShortestUnsortedContinuousSubarray().findUnsortedSubarrayInLinearTime(new int[]{1, 2, 3, 4}));
    }
}
