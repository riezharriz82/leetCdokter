import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberOfIslandsTest {
    @Test
    void numIslands() {
        NumberOfIslands obj = new NumberOfIslands();
        char[][] inp = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}};
        int res = obj.numIslands(inp);
        assertEquals(1, res);
    }

    @Test
    void numIslandsTwo() {
        NumberOfIslands obj = new NumberOfIslands();
        char[][] inp = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}};
        int res = obj.numIslands(inp);
        assertEquals(3, res);
    }
}