import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MoveZeroesTest {
    @Test
    public void test() {
        int[] input = new int[]{0, 1, 0, 3, 12};
        new MoveZeroes().moveZeroes(input);
        Assertions.assertArrayEquals(new int[]{1, 3, 12, 0, 0}, input);
    }
}
