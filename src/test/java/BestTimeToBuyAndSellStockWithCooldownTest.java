import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BestTimeToBuyAndSellStockWithCooldownTest {
    @Test
    public void test() {
        assertEquals(3, new BestTimeToBuyAndSellStockWithCooldown().maxProfitInLinearTime(new int[]{1, 4, 2}));
        assertEquals(3, new BestTimeToBuyAndSellStockWithCooldown().maxProfitInLinearTime(new int[]{1, 2, 3, 0, 2}));
        assertEquals(3, new BestTimeToBuyAndSellStockWithCooldown().maxProfitInLinearTime(new int[]{1, 2, 4}));
    }
}
