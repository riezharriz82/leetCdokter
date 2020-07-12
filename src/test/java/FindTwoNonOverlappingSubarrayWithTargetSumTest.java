import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindTwoNonOverlappingSubarrayWithTargetSumTest {
    @Test
    public void test() {
        assertEquals(-1, new FindTwoNonOverlappingSubarrayWithTargetSum().minSumOfLengths(new int[]{4, 3, 2, 6, 2, 3, 4}, 6));
        assertEquals(3, new FindTwoNonOverlappingSubarrayWithTargetSum().minSumOfLengths(new int[]{3, 1, 1, 1, 5, 1, 2, 1}, 3));
    }
}