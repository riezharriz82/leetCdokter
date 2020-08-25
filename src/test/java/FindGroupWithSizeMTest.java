import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindGroupWithSizeMTest {
    @Test
    public void test() {
        assertEquals(4, new FindGroupWithSizeM().findLatestStep(new int[]{3, 5, 1, 2, 4}, 1));
    }
}
