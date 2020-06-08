import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CountPrimesTest {
    @Test
    public void test() {
        Assertions.assertEquals(78497, new CountPrimes().countPrimes(999983));
    }
}
