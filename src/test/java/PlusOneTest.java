import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class PlusOneTest {
    @Test
    public void test() {
        assertArrayEquals(new int[]{1, 2, 4}, new PlusOne().plusOne(new int[]{1, 2, 3}));
        assertArrayEquals(new int[]{1, 3, 0}, new PlusOne().plusOne(new int[]{1, 2, 9}));
        assertArrayEquals(new int[]{1, 0, 0, 0}, new PlusOne().plusOne(new int[]{9, 9, 9}));
    }
}
