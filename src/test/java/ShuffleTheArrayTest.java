import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ShuffleTheArrayTest {
    @Test
    public void test() {
        assertArrayEquals(new int[]{2, 3, 5, 4, 1, 7}, new ShuffleTheArray().shuffle(new int[]{2, 5, 1, 3, 4, 7}, 3));
        assertArrayEquals(new int[]{1, 2, 1, 2}, new ShuffleTheArray().shuffle(new int[]{1, 1, 2, 2}, 2));
    }
}
