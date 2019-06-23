import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestValidParentheses {

    @Test
    public void test() {
        assertTrue(new ValidParentheses().isValid("()"));

        assertTrue(new ValidParentheses().isValid("()[]{}"));

        assertEquals(false, new ValidParentheses().isValid("(]"));

        assertEquals(false, new ValidParentheses().isValid("([)]"));

        assertTrue(new ValidParentheses().isValid("{[]}"));
    }

}
