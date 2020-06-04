import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class KClosestPointToOriginTest {

    @Test
    void kClosest() {
        KClosestPointToOrigin obj = new KClosestPointToOrigin();
        int[][] input = {{1, 3}, {-2, 2}};
        int[][] result = obj.kClosestUsingQuickSelect(input, 1);
        Assertions.assertArrayEquals(result, new int[][]{{-2, 2}});
        System.out.println(Arrays.deepToString(result));
    }

    @Test
    void kClosestTwo() {
        KClosestPointToOrigin obj = new KClosestPointToOrigin();
        int[][] input = {{3, 3}, {5, -1}, {-2, 4}};
        int[][] result = obj.kClosestUsingQuickSelect(input, 2);

        int[][] actual = {{3, 3}, {-2, 4}};
        Arrays.sort(actual, (o1, o2) -> Integer.compare(o1[0], o2[0]));
        Arrays.sort(result, (o1, o2) -> Integer.compare(o1[0], o2[0]));
        System.out.println(Arrays.deepToString(result));
        Assertions.assertArrayEquals(result, actual);

    }

}