import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepeatedStringMatchTest {

    @Test
    public void test() {
        assertEquals(2, new RepeatedStringMatch().repeatedStringMatch("aabaa", "aaab"));
        assertEquals(3, new RepeatedStringMatch().repeatedStringMatch("abcd", "cdabcdab"));
        assertEquals(1, new RepeatedStringMatch().repeatedStringMatch("a", "a"));
        assertEquals(2, new RepeatedStringMatch().repeatedStringMatch("aaaaaaaaaab", "ba"));
        assertEquals(1, new RepeatedStringMatch().repeatedStringMatch("aa", "a"));
        assertEquals(4, new RepeatedStringMatch().repeatedStringMatch("aa", "aaaaaaa"));
    }
}
