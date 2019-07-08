import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NumberOfIslandsTest {

    @Test
    void numIslands() {
        NumberOfIslands obj = new NumberOfIslands();
        char[][] inp = {{'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}};
        int res = obj.numIslands(inp);
        Assertions.assertEquals(res, 1);
    }

    @Test
    void numIslandsTwo() {
        NumberOfIslands obj = new NumberOfIslands();
        char[][] inp = {{'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}};
        int res = obj.numIslands(inp);
        Assertions.assertEquals(res, 3);
    }
}