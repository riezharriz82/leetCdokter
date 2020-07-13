import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindAllAnagramInStringTest {
    @Test
    public void test() {
        List<Integer> actual = new FindAllAnagramInString().findAnagrams("cbaebabacd", "abc");
        List<Integer> expected = Arrays.asList(0, 6);
        Collections.sort(actual);
        assertEquals(expected, actual);

        actual = new FindAllAnagramInString().findAnagrams("abab", "ab");
        expected = Arrays.asList(0, 1, 2);
        Collections.sort(actual);
        assertEquals(expected, actual);
    }
}
