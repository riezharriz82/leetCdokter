import static org.junit.Assert.assertTrue;

public class TestReverseInteger {

	@org.junit.Test
	public void testReverseInteger() {
		assertTrue(new ReverseInteger().reverse(123) == 321);
		assertTrue(new ReverseInteger().reverse(-123) == -321);
		assertTrue(new ReverseInteger().reverse(120) == 21);
		assertTrue(new ReverseInteger().reverse(0) == 0);
	}
}
