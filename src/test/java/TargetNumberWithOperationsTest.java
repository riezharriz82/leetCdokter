import alternate.TargetNumberWithOperations;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TargetNumberWithOperationsTest {
    @Test
    public void test() {
        assertEquals(3, new TargetNumberWithOperations().solve(2, 9));
        assertEquals(361, new TargetNumberWithOperations().solve(581, 60302676));
    }
}
