import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrangingCoinsTest {
    @Test
    public void test() {
        assertEquals(2, new ArrangingCoins().arrangeCoins(5));
        assertEquals(3, new ArrangingCoins().arrangeCoins(8));
        assertEquals(60070, new ArrangingCoins().arrangeCoins(1804289383));
    }
}
