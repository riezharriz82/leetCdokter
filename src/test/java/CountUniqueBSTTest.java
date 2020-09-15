import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountUniqueBSTTest {
    @Test
    public void test() {
        assertEquals(5, new UniqueBST().numTrees(3));
    }
}