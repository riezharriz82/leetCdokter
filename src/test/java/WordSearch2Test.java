import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WordSearch2Test {
    @Test
    public void test() {
        List<String> actual = new WordSearch2().findWordsWithTrie(new char[][]{
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        }, new String[]{"oath", "pea", "eat", "rain"});
        System.out.println(actual);
        List<String> expected = Arrays.asList("oath", "eat");
        assertTrue(actual.containsAll(expected));
        assertTrue(expected.containsAll(actual));

        actual = new WordSearch2().findWordsWithTrie(new char[][]{
                {'a', 'a', 'f', 'a'},
                {'o', 'a', 't', 'h'},
                {'h', 't', 'e', 'a'}
        }, new String[]{"oath", "fte"});
        System.out.println(actual);
        expected = Arrays.asList("oath", "fte");
        assertTrue(actual.containsAll(expected));
        assertTrue(expected.containsAll(actual));
    }
}
