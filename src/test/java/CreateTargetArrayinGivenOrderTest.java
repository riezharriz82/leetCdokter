import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class CreateTargetArrayinGivenOrderTest {
    @Test
    public void test() {
        assertArrayEquals(new int[]{0, 4, 1, 3, 2}, new CreateTargetArrayinGivenOrder().solveUsingArrayList(new int[]{0, 1, 2, 3, 4}, new int[]{0, 1, 2, 2, 1}));
        assertArrayEquals(new int[]{0, 1, 2, 3, 4}, new CreateTargetArrayinGivenOrder().solveUsingArrayList(new int[]{1, 2, 3, 4, 0}, new int[]{0, 1, 2, 3, 0}));
        assertArrayEquals(new int[]{2, 2, 4, 4, 3}, new CreateTargetArrayinGivenOrder().solveUsingArrayList(new int[]{4, 2, 4, 3, 2}, new int[]{0, 0, 1, 3, 1}));
    }
}
