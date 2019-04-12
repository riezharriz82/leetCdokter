import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class TestPalindromeNumber {

	@Test
	public void test() {
		assertTrue(new PalindromeNumber().isPalindrome(121) == true);
		assertTrue(new PalindromeNumber().isPalindrome(-121) == false);
	}
}
