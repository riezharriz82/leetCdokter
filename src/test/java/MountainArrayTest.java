import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MountainArrayTest {
    @Test
    public void test() {
        assertFalse(new MountainArray().validMountainArray(new int[]{2, 1}));
        assertTrue(new MountainArray().validMountainArray(new int[]{0, 3, 2, 1}));
        assertFalse(new MountainArray().validMountainArray(new int[]{3, 5, 5}));
    }
}
