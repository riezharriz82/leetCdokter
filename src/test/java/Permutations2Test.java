import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Permutations2Test {
    @Test
    public void test() {
        List<List<Integer>> actual = new Permutations2().permuteUnique(new int[]{1, 1, 2});
        System.out.println(actual);
        List<List<Integer>> expected = Arrays.asList(Arrays.asList(1, 1, 2), Arrays.asList(1, 2, 1), Arrays.asList(2, 1, 1));
        assertTrue(actual.containsAll(expected));
        assertTrue(expected.containsAll(actual));
    }
}
