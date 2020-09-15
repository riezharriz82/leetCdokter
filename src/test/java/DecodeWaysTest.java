import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DecodeWaysTest {
    @Test
    public void test() {
        assertEquals(2, new DecodeWays().numDecodings("2378"));
    }
}
