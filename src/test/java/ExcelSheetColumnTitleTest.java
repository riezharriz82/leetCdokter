import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExcelSheetColumnTitleTest {

    @Test
    public void test() {
        assertEquals("A", new ExcelSheetColumnTitle().convertToTitle(1));
        assertEquals("Z", new ExcelSheetColumnTitle().convertToTitle(26));
        assertEquals("AB", new ExcelSheetColumnTitle().convertToTitle(28));
        assertEquals("ZY", new ExcelSheetColumnTitle().convertToTitle(701));
        assertEquals("AAA", new ExcelSheetColumnTitle().convertToTitle(703));
    }
}
