import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MajorityElement2Test {
    @Test
    public void test() {
        List<Integer> actual = new MajorityElement2().majorityElement(new int[]{1, 1, 1, 3, 3, 2, 2, 2});
        System.out.println(actual);
        List<Integer> expected = Arrays.asList(1, 2);
        assertTrue(actual.containsAll(expected));
        assertTrue(expected.containsAll(actual));

        actual = new MajorityElement2().majorityElement(new int[]{4, 2, 1, 1});
        System.out.println(actual);
        expected = Arrays.asList(1);
        assertTrue(actual.containsAll(expected));
        assertTrue(expected.containsAll(actual));

        actual = new MajorityElement2().majorityElement(new int[]{6, 6, 6, 7, 7});
        System.out.println(actual);
        expected = Arrays.asList(6, 7);
        assertTrue(actual.containsAll(expected));
        assertTrue(expected.containsAll(actual));
    }
}