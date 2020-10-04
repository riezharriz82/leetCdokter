import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/
 * <p>
 * There are a number of spherical balloons spread in two-dimensional space.
 * For each balloon, provided input is the start and end coordinates of the horizontal diameter.
 * Since it's horizontal, y-coordinates don't matter and hence the x-coordinates of start and end of the diameter suffice.
 * <p>
 * An arrow can be shot up exactly vertically from different points along the x-axis
 * A balloon with xstart and xend bursts by an arrow shot at x if xstart ≤ x ≤ xend.
 * There is no limit to the number of arrows that can be shot. An arrow once shot keeps travelling up infinitely.
 * The problem is to find the minimum number of arrows that must be shot to burst all balloons.
 * <p>
 * Input:
 * [[10,16], [2,8], [1,6], [7,12]]
 * <p>
 * Output:
 * 2
 * <p>
 * Explanation:
 * One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] and [1,6]) and another arrow at x = 11 (bursting the other two balloons).
 */
public class MinimumArrowsToBurstBalloons {

    /**
     * Similar to {@link EraseNonOverlappingIntervals}
     * Greedy approach, for each balloon, in order to minimize total no of arrows, it must be shot at the rightmost point possible
     * Once shot, if the next balloon starts after the last shot point, a new arrow is required
     */
    public int findMinArrowShots(int[][] points) {
        int n = points.length;
        if (n == 0) {
            return 0;
        }
        Arrays.sort(points, Comparator.comparingInt(o -> o[1])); //sort by end
        int end = points[0][1];
        int result = 1;
        for (int i = 1; i < n; i++) {
            if (points[i][0] > end) { //if this balloon starts after the last one ends, then we need another arrow
                result++;
                end = points[i][1]; // don't forget to update the end
            }
            //since the array is sorted by end, for the balloons that lies before the end, same arrow can burst it
        }
        return result;
    }

    public int findMinArrowShotsSortByStart(int[][] points) {
        int n = points.length;
        if (n == 0) {
            return 0;
        }
        Arrays.sort(points, Comparator.comparingInt(o -> o[0])); //sort by start
        int end = points[0][1];
        int result = 1;
        for (int i = 1; i < n; i++) {
            if (points[i][0] > end) { //if this balloon starts after the last one ends, then we need another arrow
                result++;
                end = points[i][1];
            } else if (points[i][1] < end) { //if the next balloon ends before the current balloon, then it will act as a new end point.
                end = points[i][1];
            }
        }
        return result;
    }
}
