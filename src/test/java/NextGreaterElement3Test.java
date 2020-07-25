import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NextGreaterElement3Test {
    @Test
    public void test() {
        assertEquals(-1, new NextGreaterElement3().nextGreaterElement(3));
        assertEquals(21, new NextGreaterElement3().nextGreaterElement(12));
        assertEquals(1532, new NextGreaterElement3().nextGreaterElement(1523));
        assertEquals(230412, new NextGreaterElement3().nextGreaterElement(230241));
        assertEquals(13222344, new NextGreaterElement3().nextGreaterElement(12443322));
    }
}
