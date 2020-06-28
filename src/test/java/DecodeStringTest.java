import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DecodeStringTest {
    @Test
    public void test() {
        assertEquals("aaabcbc", new DecodeString().decodeString("3[a]2[bc]"));
        assertEquals("accaccacc", new DecodeString().decodeString("3[a2[c]]"));
        assertEquals("abcabccdcdcdef", new DecodeString().decodeString("2[abc]3[cd]ef"));
        assertEquals("abccdcdcdxyz", new DecodeString().decodeString("abc3[cd]xyz"));
    }
}
