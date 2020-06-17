import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SingleNumberTest {
    @Test
    public void test() {
        Assertions.assertEquals(1, new SingleNumber().singleNumber(new int[]{2, 2, 1}));
        Assertions.assertEquals(3, new SingleNumber().singleNumber(new int[]{4, 1, 1, 4, 3}));
    }
}
