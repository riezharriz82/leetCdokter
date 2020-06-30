import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CombinationSum3Test {
    @Test
    public void test() {
        List<List<Integer>> actual = new CombinationSum3().combinationSum3(3, 7);
        System.out.println(actual);
        List<List<Integer>> expected = Arrays.asList(Arrays.asList(1, 2, 4));
        assertEquals(actual.size(), expected.size());
        assertTrue(actual.containsAll(expected));

        actual = new CombinationSum3().combinationSum3(3, 9);
        System.out.println(actual);
        expected = Arrays.asList(Arrays.asList(1, 2, 6), Arrays.asList(1, 3, 5), Arrays.asList(2, 3, 4));
        assertEquals(actual.size(), expected.size());
        assertTrue(actual.containsAll(expected));
    }
}
