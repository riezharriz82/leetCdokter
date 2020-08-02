import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaximumSwapTest {
    @Test
    public void test() {
        assertEquals(90909011, new MaximumSwap().maximumSwap(10909091));
        assertEquals(7236, new MaximumSwap().maximumSwap(2736));
        assertEquals(9973, new MaximumSwap().maximumSwap(9973));
        assertEquals(98863, new MaximumSwap().maximumSwap(98368));
    }
}
