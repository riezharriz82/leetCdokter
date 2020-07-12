import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PathWithMaxProbabilityTest {
    @Test
    public void test() {
        Assertions.assertEquals(0.25D, new PathWithMaxProbability().maxProbabilityUsingDjikstra(3, new int[][]{{0, 1}, {1, 2}, {0, 2}}, new double[]{0.5, 0.5, 0.2}, 0, 2));
    }
}
