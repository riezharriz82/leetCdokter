import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.com/problems/find-right-interval/
 * <p>
 * Given a set of intervals, for each of the interval i, check if there exists an interval j whose start point is bigger
 * than or equal to the end point of the interval i, which can be called that j is on the "right" of i.
 * <p>
 * For any interval i, you need to store the minimum interval j's index, which means that the interval j
 * has the minimum start point to build the "right" relationship for interval i. If the interval j doesn't exist, store -1 for the interval i.
 * Finally, you need output the stored value of each interval as an array.
 * <p>
 * Note:
 * <p>
 * You may assume the interval's end point is always bigger than its start point.
 * You may assume none of these intervals have the same start point.
 * <p>
 * Input: [ [3,4], [2,3], [1,2] ]
 * <p>
 * Output: [-1, 0, 1]
 * <p>
 * Explanation: There is no satisfied "right" interval for [3,4].
 * For [2,3], the interval [3,4] has minimum-"right" start point;
 * For [1,2], the interval [2,3] has minimum-"right" start point.
 */
public class FindRightInterval {
    /**
     * Approach, since we are interested in the index of the next right interval, sorting comes into mind.
     * But sorting will change the original indexes, so we need to store the original indices somewhere
     */
    public int[] findRightInterval(int[][] intervals) {
        Point[] points = new Point[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            points[i] = new Point(intervals[i][0], intervals[i][1], i);
        }
        Arrays.sort(points, Comparator.comparingInt(o -> o.x)); //sort by start time
        int[] result = new int[intervals.length];
        Arrays.fill(result, -1);
        for (int i = 0; i < intervals.length; i++) {
            //need to find the point whose x index >= current y index
            int index = Arrays.binarySearch(points, new Point(points[i].y, -1, -1), Comparator.comparingInt(o -> o.x));
            if (index == -1 * (intervals.length + 1)) { //if current y is the largest value
                continue;
            }
            if (index < 0) {
                index = -1 * index - 1;
            }
            /*
            This is not required as the start points are guaranteed to be unique, but during my first implementation, I thought otherwise
            Upon reading the doc of binary search, it's mentioned that if their are duplicate keys present, any of them can be returned
            But since we are interested in the first right index instead, we need the leftmost one
            So iterate left until we find such character, care must be taken to handle the case when index equals the current index as well
            while (index - 1>= 0 && points[index-1].x >= points[i].y) {
                index--;
            }
            if (index == i & index + 1 < intervals.length && points[index + 1].x >= points[i].y) {
                index++;
            }*/
            result[points[i].index] = points[index].index;
        }
        return result;
    }

    private static class Point {
        int x;
        int y;
        int index;

        public Point(int x, int y, int index) {
            this.x = x;
            this.y = y;
            this.index = index;
        }
    }
}
