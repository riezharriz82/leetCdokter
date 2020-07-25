import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class NextGreaterElement2Test {
    @Test
    public void test() {
        assertArrayEquals(new int[]{2, -1, 2}, new NextGreaterElement2().nextGreaterElements(new int[]{1, 2, 1}));
    }
}
