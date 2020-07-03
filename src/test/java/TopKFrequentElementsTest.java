import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TopKFrequentElementsTest {
    public static boolean contains(final int[] array, final int v) {
        boolean result = false;
        for (int i : array) {
            if (i == v) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Test
    public void test() {
        int[] actual = new TopKFrequentElements().topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2);
        System.out.println(Arrays.toString(actual));
        assertTrue(contains(actual, 1));
        assertTrue(contains(actual, 2));
    }
}
