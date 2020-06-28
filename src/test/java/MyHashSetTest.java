import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyHashSetTest {
    @Test
    public void test() {
        MyHashSet hashSet = new MyHashSet();
        hashSet.add(1);
        hashSet.add(2);
        assertTrue(hashSet.contains(1));            // returns 1
        assertFalse(hashSet.contains(3));            // returns -1 (not found)
        hashSet.add(2);          // update the existing value
        assertTrue(hashSet.contains(2));            // returns 1
        hashSet.remove(2);          // remove the mapping for 2
        assertFalse(hashSet.contains(2));            // returns -1 (not found)
    }
}
