import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LongestCommonSubarrayTest {
    @Test
    public void test() {
        assertEquals(2, new LongestCommonSubarray().findLength(new int[]{0, 1, 1, 1, 1}, new int[]{1, 0, 1, 0, 1}));
    }
}
