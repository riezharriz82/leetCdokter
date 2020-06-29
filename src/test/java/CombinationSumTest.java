import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CombinationSumTest {
    @Test
    public void test() {
        List<List<Integer>> actual = new CombinationSum().combinationSumUsingBacktracking(new int[]{2, 3, 6, 7}, 7);
        System.out.println(actual);
        assertTrue(actual.contains(Arrays.asList(7)));
        assertTrue(actual.contains(Arrays.asList(2, 2, 3)));

        actual = new CombinationSum().combinationSumUsingBacktracking(new int[]{2, 3, 5}, 8);
        System.out.println(actual);
        assertTrue(actual.contains(Arrays.asList(2, 3, 3)));
        assertTrue(actual.contains(Arrays.asList(2, 2, 2, 2)));
        assertTrue(actual.contains(Arrays.asList(3, 5)));
    }
}
