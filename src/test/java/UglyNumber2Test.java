import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UglyNumber2Test {
    @Test
    public void test() {
        Assertions.assertEquals(12, new UglyNumber2().nthUglyNumber(10));
    }
}
