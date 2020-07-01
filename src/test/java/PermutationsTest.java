import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PermutationsTest {
    @Test
    public void test() {
        List<List<Integer>> actual = new Permutations().permute(new int[]{1, 2, 3});
        List<List<Integer>> expected = Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(1, 3, 2), Arrays.asList(2, 1, 3),
                Arrays.asList(2, 3, 1), Arrays.asList(3, 1, 2), Arrays.asList(3, 2, 1));
        assertEquals(expected.size(), actual.size());
        assertTrue(actual.containsAll(expected));
    }
}
