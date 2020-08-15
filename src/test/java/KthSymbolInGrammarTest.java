import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KthSymbolInGrammarTest {
    @Test
    public void test() {
        assertEquals(1, new KthSymbolInGrammar().kthGrammar(4, 5));
    }
}
