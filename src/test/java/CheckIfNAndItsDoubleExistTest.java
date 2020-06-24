import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckIfNAndItsDoubleExistTest {
    @Test
    public void test() {
        assertTrue(new CheckIfNAndItsDoubleExist().checkIfExist(new int[]{10, 2, 5, 3}));
        assertFalse(new CheckIfNAndItsDoubleExist().checkIfExist(new int[]{3, 1, 7, 11}));
        assertFalse(new CheckIfNAndItsDoubleExist().checkIfExist(new int[]{-2, 0, 10, -19, 4, 6, -8}));
        assertTrue(new CheckIfNAndItsDoubleExist().checkIfExist(new int[]{7, 1, 14, 11}));
    }
}
