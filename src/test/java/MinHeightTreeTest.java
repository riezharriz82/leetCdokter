import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinHeightTreeTest {
    @Test
    public void test() {
        assertEquals(Arrays.asList(3, 4), new MinHeightTree().findMinHeightTrees(6, new int[][]{{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}}));
    }
}
