import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MinimumNumberOfFibonacciRequiredToReachSumKTest {
    @Test
    public void test() {
        Assertions.assertEquals(2, new MinimumNumberOfFibonacciRequiredToReachSumK().findMinimumNumberOfFibonacciGreedy(7));
        Assertions.assertEquals(14, new MinimumNumberOfFibonacciRequiredToReachSumK().findMinimumNumberOfFibonacciGreedy(1_000_000_000));
    }
}
