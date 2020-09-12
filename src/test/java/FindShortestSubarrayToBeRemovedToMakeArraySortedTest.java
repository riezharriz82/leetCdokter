import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindShortestSubarrayToBeRemovedToMakeArraySortedTest {
    @Test
    public void test() {
        assertEquals(3, new FindShortestSubarrayToBeRemovedToMakeArraySorted().findLengthOfShortestSubarray(new int[]{1, 2, 3, 10, 4, 2, 3, 5}));
        assertEquals(8, new FindShortestSubarrayToBeRemovedToMakeArraySorted().findLengthOfShortestSubarray(new int[]{13, 0, 14, 7, 18, 18, 18, 16, 8, 15, 20}));
        assertEquals(4, new FindShortestSubarrayToBeRemovedToMakeArraySorted().findLengthOfShortestSubarray(new int[]{5, 4, 3, 2, 1}));
        assertEquals(2, new FindShortestSubarrayToBeRemovedToMakeArraySorted().findLengthOfShortestSubarray(new int[]{1, 2, 3, 3, 10, 1, 3, 3, 5}));
        assertEquals(8, new FindShortestSubarrayToBeRemovedToMakeArraySorted().findLengthOfShortestSubarray(new int[]{6, 3, 10, 11, 15, 20, 13, 3, 18, 12}));
    }
}
