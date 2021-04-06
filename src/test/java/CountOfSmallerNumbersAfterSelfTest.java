import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountOfSmallerNumbersAfterSelfTest {
    @Test
    public void test() {
        assertEquals(Arrays.asList(2, 1, 1, 0), new CountOfSmallerNumbersAfterSelf().countSmaller(new int[]{5, 2, 6, 1}));
        assertEquals(Arrays.asList(0, 0), new CountOfSmallerNumbersAfterSelf().countSmaller(new int[]{-1, -1}));
    }
}
