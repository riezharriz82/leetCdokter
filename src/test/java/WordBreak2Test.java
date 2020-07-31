import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WordBreak2Test {
    @Test
    public void test() {
        List<String> actual = new WordBreak2().wordBreak("catsand", Arrays.asList("cat", "cats", "sand", "and"));
        List<String> expected = Arrays.asList("cats and", "cat sand");
        assertEquals(expected.size(), actual.size());
        assertTrue(expected.containsAll(actual));
        assertTrue(actual.containsAll(expected));

        actual = new WordBreak2().wordBreak("catsandog", Arrays.asList("cat", "cats", "and", "sand", "dog"));
        assertTrue(actual.isEmpty());

        actual = new WordBreak2().wordBreak("pineapplepenapple", Arrays.asList("apple", "pen", "applepen", "pine", "pineapple"));
        expected = Arrays.asList("pine apple pen apple",
                "pineapple pen apple",
                "pine applepen apple");
        assertEquals(expected.size(), actual.size());
        assertTrue(expected.containsAll(actual));
        assertTrue(actual.containsAll(expected));

        actual = new WordBreak2().wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                Arrays.asList("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa"));
        assertTrue(actual.isEmpty());
    }
}