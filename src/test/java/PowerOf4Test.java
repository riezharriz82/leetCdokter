import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PowerOf4Test {
    @Test
    public void test() {
        assertTrue(new Powerof4().isPowerOfFour(4));
        assertTrue(new Powerof4().isPowerOfFour(16));
        assertFalse(new Powerof4().isPowerOfFour(19));
    }
}
