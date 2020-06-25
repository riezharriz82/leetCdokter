import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindTheDuplicateNumberTest {
    @Test
    public void test() {
        assertEquals(2, new FindTheDuplicateNumber().floydsSlowAndFastPointerAlgorithm(new int[]{1, 3, 4, 2, 2}));
        assertEquals(2, new FindTheDuplicateNumber().floydsSlowAndFastPointerAlgorithm(new int[]{2, 2, 2, 2}));
    }
}
