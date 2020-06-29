import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LetterCombinationsOfPhoneNumberTest {
    @Test
    public void test() {
        List<String> actual = new LetterCombinationsOfPhoneNumber().letterCombinations("23");
        List<String> expected = Arrays.asList("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf");
        assertTrue(actual.containsAll(expected));
        assertEquals(actual.size(), expected.size());
    }
}
