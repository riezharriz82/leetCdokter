import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ThreeSumTest {
    @Test
    public void test() {
        List<List<Integer>> lists = new ThreeSum().threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(-1, 0, 1));
        expected.add(Arrays.asList(-1, -1, 2));
        assertTrue(lists.containsAll(expected));
    }

    @Test
    public void test2() {
        List<List<Integer>> lists = new ThreeSum().threeSum(new int[]{-2, 0, 0, 2, 2});
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(-2, 0, 2));
        assertTrue(lists.containsAll(expected));
    }
}
