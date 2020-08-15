import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CombinationIteratorRecursiveTest {
    @Test
    public void test() {
        CombinationIteratorRecursive iterator = new CombinationIteratorRecursive("abc", 2);
        assertEquals("ab", iterator.next());
        assertEquals("ac", iterator.next());
        assertEquals("bc", iterator.next());
        assertFalse(iterator.hasNext());
    }
}
