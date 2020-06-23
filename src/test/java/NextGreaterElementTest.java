import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class NextGreaterElementTest {
    @Test
    public void test() {
        assertArrayEquals(new int[]{1, 1, 4, 2, 1, 1, 0, 0}, new NextGreaterElement().dailyTemperaturesUsingCustomStack(new int[]{73, 74, 75, 71, 69, 72, 76, 73}));
    }
}
