import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindAllNumberDisappearedInArrayTest {
    @Test
    public void test() {
        assertEquals(Arrays.asList(5, 6), new FindAllNumbersDisappearedInArray().findDisappearedNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
        assertEquals(Arrays.asList(1, 5), new FindAllNumbersDisappearedInArray().findDisappearedNumbers(new int[]{2, 2, 3, 3, 4}));
    }
}
