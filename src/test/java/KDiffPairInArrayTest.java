import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KDiffPairInArrayTest {
    @Test
    public void test() {
        assertEquals(2, new KDiffPairInArray().findPairs(new int[]{3, 1, 4, 1, 5}, 2));
        assertEquals(4, new KDiffPairInArray().findPairs(new int[]{1, 2, 3, 4, 5}, 1));
        assertEquals(1, new KDiffPairInArray().findPairs(new int[]{1, 3, 1, 5, 4}, 0));
        assertEquals(0, new KDiffPairInArray().findPairs(new int[]{1, 2, 3, 4, 5}, -1));
        assertEquals(1, new KDiffPairInArray().findPairs(new int[]{1, 1, 1, 1}, 0));
    }
}
