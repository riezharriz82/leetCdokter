import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WordSearchTest {
    @Test
    public void test() {
        char[][] input = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        assertTrue(new WordSearch().exist(input, "ABCCED"));
        assertTrue(new WordSearch().exist(input, "SEE"));
        assertFalse(new WordSearch().exist(input, "ABCB"));

        input = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'E', 'S'}, {'A', 'D', 'E', 'E'}};
        assertTrue(new WordSearch().exist(input, "ABCEFSADEESE"));
    }
}
