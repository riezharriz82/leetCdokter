import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class RotateArrayTest {
    @Test
    public void test() {
        int[] input = new int[]{1, 2};
        new RotateArray().inplaceUsingReverse(input, 3);
        assertArrayEquals(new int[]{2, 1}, input);

        input = new int[]{1, 2, 3, 4, 5, 6, 7};
        new RotateArray().inplaceUsingReverse(input, 3);
        assertArrayEquals(new int[]{5, 6, 7, 1, 2, 3, 4}, input);

        input = new int[]{-1, -100, 3, 99};
        new RotateArray().inplaceUsingReverse(input, 2);
        assertArrayEquals(new int[]{3, 99, -1, -100}, input);
    }
}
