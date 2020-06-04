import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestReverseInteger {

    @Test
    public void testReverseInteger() {
        assertEquals(321, new ReverseInteger().reverse(123));
        assertEquals(new ReverseInteger().reverse(-123), -321);
        assertEquals(21, new ReverseInteger().reverse(120));
        assertEquals(0, new ReverseInteger().reverse(0));
    }
}
