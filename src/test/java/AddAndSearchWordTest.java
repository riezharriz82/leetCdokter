import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddAndSearchWordTest {
    @Test
    public void test() {
        AddAndSearchWord dictionary = new AddAndSearchWord();
        dictionary.addWord("bad");
        dictionary.addWord("dad");
        dictionary.addWord("mad");
        assertFalse(dictionary.search("pad"));
        assertTrue(dictionary.search("bad"));
        assertTrue(dictionary.search(".ad"));
        assertTrue(dictionary.search("b.."));

        dictionary = new AddAndSearchWord();
        dictionary.addWord("a");
        dictionary.addWord("a");
        assertFalse(dictionary.search(".a"));
        assertFalse(dictionary.search("a."));
    }
}
