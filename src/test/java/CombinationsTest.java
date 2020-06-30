import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CombinationsTest {
    @Test
    public void test() {
        List<List<Integer>> actual = new Combinations().combine(4, 2);
        System.out.println(actual);
        List<List<Integer>> expected = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(1, 3), Arrays.asList(1, 4),
                Arrays.asList(2, 3), Arrays.asList(2, 4), Arrays.asList(3, 4));
        assertEquals(expected.size(), actual.size());
        assertTrue(actual.containsAll(expected));
    }
}
