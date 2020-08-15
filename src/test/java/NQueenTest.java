import org.junit.jupiter.api.Test;

public class NQueenTest {
    @Test
    public void test() {
        new NQueen().solveNQueens(4).forEach(System.out::println);
    }
}
