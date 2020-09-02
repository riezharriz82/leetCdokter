import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoinChangeTest {
    @Test
    public void test() {
        assertEquals(3, new CoinChange().coinChangeTopDown(new int[]{1, 2, 5}, 11));
        assertEquals(20, new CoinChange().coinChangeTopDown(new int[]{470, 18, 66, 301, 403, 112, 360}, 8235));
    }
}
