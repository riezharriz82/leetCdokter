import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LengthofLastWordTest {
    @Test
    public void test() {
        assertEquals(5, new LengthofLastWord().lengthOfLastWord("Hello World"));
        assertEquals(1, new LengthofLastWord().lengthOfLastWord("a "));
        assertEquals(1, new LengthofLastWord().lengthOfLastWord("a   "));
        assertEquals(2, new LengthofLastWord().lengthOfLastWord("  ab   "));
        assertEquals(1, new LengthofLastWord().lengthOfLastWord("  a   "));
    }
}
