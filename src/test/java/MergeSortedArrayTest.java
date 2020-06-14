import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MergeSortedArrayTest {
    @Test
    public void test() {
        int[] input = {1, 2, 3, 0, 0, 0};
        new MergeSortedArray().mergeByComparingElementsFromTheEndRatherThanFront(input, 3, new int[]{2, 5, 6}, 3);
        Assertions.assertArrayEquals(new int[]{1, 2, 2, 3, 5, 6}, input);

        input = new int[]{5, 6, 7, 0, 0, 0};
        new MergeSortedArray().mergeByComparingElementsFromTheEndRatherThanFront(input, 3, new int[]{1, 2, 3}, 3);
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 5, 6, 7}, input);

        input = new int[]{1};
        new MergeSortedArray().mergeByComparingElementsFromTheEndRatherThanFront(input, 1, new int[]{}, 0);
        Assertions.assertArrayEquals(new int[]{1}, input);

        input = new int[]{0};
        new MergeSortedArray().mergeByComparingElementsFromTheEndRatherThanFront(input, 0, new int[]{1}, 1);
        Assertions.assertArrayEquals(new int[]{1}, input);

        input = new int[]{4, 0, 0, 0, 0, 0};
        new MergeSortedArray().mergeByComparingElementsFromTheEndRatherThanFront(input, 1, new int[]{1, 2, 3, 5, 6}, 5);
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6}, input);
    }
}
