import static org.junit.Assert.*;

import org.junit.Test;

public class TestValidParentheses {

	@Test
	public void test() {
		assertTrue(new ValidParentheses().isValid("()"));

		assertTrue(new ValidParentheses().isValid("()[]{}"));

		assertTrue(new ValidParentheses().isValid("(]") == false);

		assertTrue(new ValidParentheses().isValid("([)]") == false);

		assertTrue(new ValidParentheses().isValid("{[]}"));
	}

}
