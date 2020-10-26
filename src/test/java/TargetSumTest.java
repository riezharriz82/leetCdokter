import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TargetSumTest {
    @Test
    public void test() {
        Assertions.assertEquals(4, new TargetSum().findTargetSumWays(new int[]{0, 0, 1}, 1));
        Assertions.assertEquals(5, new TargetSum().findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));
    }
}
