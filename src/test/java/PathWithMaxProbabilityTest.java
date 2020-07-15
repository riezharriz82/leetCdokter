import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PathWithMaxProbabilityTest {
    @Test
    public void test() {
        assertEquals(0.25D, new PathWithMaxProbability().maxProbabilityUsingDjikstra(3, new int[][]{{0, 1}, {1, 2}, {0, 2}}, new double[]{0.5, 0.5, 0.2}, 0, 2));
        assertTrue(Math.abs(0.070D - new PathWithMaxProbability().maxProbabilityUsingDjikstra(4, new int[][]{{0, 1}, {0, 2}, {1, 3}, {2, 3}}, new double[]{0.1, 0.6, 0.7, 0.1}, 0, 3)) < 0.00001);
    }
}
