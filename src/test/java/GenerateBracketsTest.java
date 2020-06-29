import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GenerateBracketsTest {
    @Test
    public void test() {
        List<String> res = new GenerateBrackets().generateParenthesis(3);
        List<String> expected = Arrays.asList("((()))", "(()())", "()()()", "(())()", "()(())");
        assertEquals(5, res.size());
        assertTrue(res.containsAll(expected));

        res = new GenerateBrackets().generateParenthesis(1);
        assertEquals(1, res.size());
        assertTrue(res.contains("()"));
    }
}
