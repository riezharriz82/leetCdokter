import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReverseStringTest {
    @Test
    public void test() {
        char[] input = {'h', 'e', 'l', 'l', 'o'};
        new ReverseString().reverseString(input);
        Assertions.assertArrayEquals(new char[]{'o', 'l', 'l', 'e', 'h'}, input);

        input = new char[]{'h', 'e'};
        new ReverseString().reverseString(input);
        Assertions.assertArrayEquals(new char[]{'e', 'h'}, input);
    }
}
