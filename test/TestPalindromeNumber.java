import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPalindromeNumber {

    @Test
    public void test() {
        assertTrue(new PalindromeNumber().isPalindrome(121));
        assertFalse(new PalindromeNumber().isPalindrome(-121));
    }
}
