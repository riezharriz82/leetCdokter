import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SubsetsTest {
    @Test
    public void test() {
        List<List<Integer>> output = new Subsets().subsets(new int[]{1, 2, 3});
        System.out.println(output);
        List<List<Integer>> expected = Arrays.asList(Arrays.asList(), Arrays.asList(1), Arrays.asList(2), Arrays.asList(3), Arrays.asList(1, 2), Arrays.asList(1, 3),
                Arrays.asList(2, 3), Arrays.asList(1, 2, 3));
        assertEquals(expected.size(), output.size());
        assertTrue(output.containsAll(expected));
    }
}
