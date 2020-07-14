import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CourseScheduleTest {
    @Test
    public void test() {
        assertTrue(new CourseSchedule().canFinish(3, new int[][]{{0, 1}, {0, 2}, {1, 2}}));
        assertFalse(new CourseSchedule().canFinish(2, new int[][]{{0, 1}, {1, 0}}));
    }
}
