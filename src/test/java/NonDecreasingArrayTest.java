import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NonDecreasingArrayTest {
    @Test
    public void test() {
        assertTrue(new NonDecreasingArray().checkPossibility(new int[]{4, 2, 3}));
        assertTrue(new NonDecreasingArray().checkPossibility(new int[]{-1, 4, 2, 3}));
        assertFalse(new NonDecreasingArray().checkPossibility(new int[]{4, 2, 1}));
        assertFalse(new NonDecreasingArray().checkPossibility(new int[]{3, 4, 2, 3}));
    }
}
