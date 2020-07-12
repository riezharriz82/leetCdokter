import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReverseBitsTest {
    @Test
    public void test() {
        Assertions.assertEquals(964176192, new ReverseBits().reverseBitsSimplified(43261596));
    }
}
