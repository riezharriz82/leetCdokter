import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ThirdMaximumNumberTest {
    @Test
    public void test() {
        assertEquals(1, new ThirdMaximumNumber().thirdMax(new int[]{2, 2, 3, 3, 1}));
        assertEquals(Integer.MIN_VALUE, new ThirdMaximumNumber().thirdMax(new int[]{1, 2, Integer.MIN_VALUE}));
        assertEquals(Integer.MIN_VALUE, new ThirdMaximumNumber().thirdMax(new int[]{1, Integer.MIN_VALUE, 2}));
        assertEquals(1, new ThirdMaximumNumber().thirdMax(new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 1, 1, 1}));

    }
}
