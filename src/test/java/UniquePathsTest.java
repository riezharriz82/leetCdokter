import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UniquePathsTest {
    @Test
    public void test() {
        assertEquals(3, new UniquePaths().uniquePaths(3, 2));
        assertEquals(28, new UniquePaths().uniquePaths(7, 3));
    }
}
