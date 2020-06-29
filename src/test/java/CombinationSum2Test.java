import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CombinationSum2Test {
    @Test
    public void test() {
        List<List<Integer>> actual = new CombinationSum2().combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8);
        System.out.println(actual);
        assertTrue(actual.contains(Arrays.asList(1, 7)));
        assertTrue(actual.contains(Arrays.asList(1, 2, 5)));
        assertTrue(actual.contains(Arrays.asList(2, 6)));
        assertTrue(actual.contains(Arrays.asList(1, 1, 6)));

        actual = new CombinationSum2().combinationSum2(new int[]{2, 5, 2, 1, 2}, 5);
        System.out.println(actual);
        assertTrue(actual.contains(Arrays.asList(1, 2, 2)));
        assertTrue(actual.contains(Arrays.asList(5)));
    }
}
