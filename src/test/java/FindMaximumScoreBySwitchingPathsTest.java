import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindMaximumScoreBySwitchingPathsTest {
    @Test
    public void test() {
        assertEquals(30, new FindMaximumScoreBySwitchingPaths().maxSum(new int[]{2, 4, 5, 8, 10}, new int[]{4, 6, 8, 9}));
        assertEquals(109, new FindMaximumScoreBySwitchingPaths().maxSum(new int[]{1, 3, 5, 7, 9}, new int[]{3, 5, 100}));
        assertEquals(109, new FindMaximumScoreBySwitchingPaths().maxSum(new int[]{1, 3, 5, 7, 9}, new int[]{3, 5, 100}));
        assertEquals(40, new FindMaximumScoreBySwitchingPaths().maxSum(new int[]{1, 2, 3, 4, 5}, new int[]{6, 7, 8, 9, 10}));
    }
}
