import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class WordBreak1Test {
    @Test
    public void test() {
        Assertions.assertTrue(new WordBreak1().wordBreakOptimized("ab", Arrays.asList("a", "b")));
    }
}
