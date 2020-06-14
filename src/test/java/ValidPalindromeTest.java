import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidPalindromeTest {
    @Test
    public void test() {
        assertTrue(new ValidPalindrome().isPalindrome("A man, a plan, a canal: Panama"));
        assertFalse(new ValidPalindrome().isPalindrome("race a car"));
        assertTrue(new ValidPalindrome().isPalindrome(""));
    }
}
