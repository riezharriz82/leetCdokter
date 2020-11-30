import java.util.Arrays;

/**
 * https://leetcode.com/problems/magnetic-force-between-two-balls/
 * <p>
 * In universe Earth C-137, Rick discovered a special form of magnetic force between two balls if they are put in his new invented basket.
 * Rick has n empty baskets, the ith basket is at position[i], Morty has m balls and needs to distribute the balls into the baskets
 * such that the minimum magnetic force between any two balls is maximum.
 * <p>
 * Rick stated that magnetic force between two different balls at positions x and y is |x - y|.
 * <p>
 * Given the integer array position and the integer m. Return the required force.
 * <p>
 * Input: position = [1,2,3,4,7], m = 3
 * Output: 3
 * Explanation: Distributing the 3 balls into baskets 1, 4 and 7 will make the magnetic force between ball pairs [3, 3, 6].
 * The minimum magnetic force is 3. We cannot achieve a larger minimum magnetic force than 3.
 * <p>
 * Input: position = [5,4,3,2,1,1000000000], m = 2
 * Output: 999999999
 * Explanation: We can use baskets 1 and 1000000000.
 */
public class MagneticForceBetweenTwoBalls {
    /**
     * Approach: Binary Search between 1 and the largest possible difference to see whether we can place all the balls by trying to place balls
     * at a minimum distance of k, if yes, recur right for a possible larger value
     * if no, recur left to try out a possible smaller value
     * <p>
     * {@link SplitArrayLargestSum} {@link DivideChocolates} {@link MissingElementInSortedArray} {@link FindMinimumInRotatedSortedArray2}
     * related binary search problems
     */
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int low = 1, high = position[position.length - 1] - position[0], ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (ballsPlacedAtMinDistanceOf(mid, position) >= m) { //if we placed enough balls, try to recur right to try a larger distance
                ans = mid;
                low = mid + 1;
            } else { //we can't place all balls, current distance is too large, have to try smaller distance
                high = mid - 1;
            }
        }
        return ans;
    }

    //try to place balls at minimum distance of curDistance
    private int ballsPlacedAtMinDistanceOf(int curDistance, int[] position) {
        int ballsPlaced = 1, previousLocation = 0;
        int index = 1;
        while (index < position.length) {
            if ((position[index] - position[previousLocation]) >= curDistance) {
                //if distance b/w two balls is >= curDistance, it's safe to place a new ball, remember that we can't place two
                //balls at a distance < curDistance, as it will violate our constraint
                ballsPlaced++;
                previousLocation = index;
            }
            index++;
        }
        return ballsPlaced;
    }
}
