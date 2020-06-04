import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BuddyStringsTest {
    @Test
    public void test() {
        assertTrue(new BuddyStrings().buddyStrings("ab", "ba"));
        assertFalse(new BuddyStrings().buddyStrings("ab", "ab"));
        assertTrue(new BuddyStrings().buddyStrings("aa", "aa"));
        assertTrue(new BuddyStrings().buddyStrings("aaaaaaabc", "aaaaaaacb"));
        assertFalse(new BuddyStrings().buddyStrings("abc", "acd"));
        assertFalse(new BuddyStrings().buddyStrings("abcaa","abcbb"));
    }
}
