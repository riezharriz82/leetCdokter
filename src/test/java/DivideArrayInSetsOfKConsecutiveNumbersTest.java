import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DivideArrayInSetsOfKConsecutiveNumbersTest {
    @Test
    public void test() {
        assertTrue(new DivideArrayInSetsOfKConsecutiveNumbers().isNStraightHand(new int[]{1, 2, 3, 6, 2, 3, 4, 7, 8}, 3));
        assertFalse(new DivideArrayInSetsOfKConsecutiveNumbers().isNStraightHand(new int[]{1, 2, 3, 4, 5}, 4));
    }
}
