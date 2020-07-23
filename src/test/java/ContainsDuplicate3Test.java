import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContainsDuplicate3Test {
    @Test
    public void test() {
        Assertions.assertTrue(new ContainsDuplicate3().containsNearbyAlmostDuplicate(new int[]{0, 2147483647}, 1, 2147483647));
    }
}
