import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChangeOnlyOneElementToSortArrayTest {
    @Test
    public void test() {
        assertTrue(new ChangeOnlyOneElementToSortArray().checkPossibility(new int[]{4, 2, 3}));
        assertTrue(new ChangeOnlyOneElementToSortArray().checkPossibility(new int[]{-1, 4, 2, 3}));
        assertFalse(new ChangeOnlyOneElementToSortArray().checkPossibility(new int[]{4, 2, 1}));
        assertFalse(new ChangeOnlyOneElementToSortArray().checkPossibility(new int[]{3, 4, 2, 3}));
    }
}
