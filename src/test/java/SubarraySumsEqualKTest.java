import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubarraySumsEqualKTest {
    @Test
    public void test() {
        assertEquals(2, new SubarraySumEqualsK().subarraySumWithoutHashMap(new int[]{1, 1, 1}, 2));
        assertEquals(4, new SubarraySumEqualsK().subarraySumWithoutHashMap(new int[]{0, 0, 0, 3}, 3));
    }
}
