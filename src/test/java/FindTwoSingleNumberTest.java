import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FindTwoSingleNumberTest {
    @Test
    public void test() {
        Assertions.assertArrayEquals(new int[]{3, 5}, new FindTwoSingleNumber().singleNumber(new int[]{1, 1, 2, 2, 3, 5}));
    }
}
