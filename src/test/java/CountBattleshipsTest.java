import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountBattleshipsTest {
    @Test
    public void test() {
        char[][] input = {{'X', '.', '.', 'X'}, {'.', '.', '.', 'X'}, {'.', '.', '.', 'X'}};
        assertEquals(2, new CountBattleships().countBattleshipsWithoutDFS(input));
    }
}
