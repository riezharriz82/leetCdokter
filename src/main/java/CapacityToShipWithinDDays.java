/**
 * https://leetcode.com/problems/capacity-to-ship-packages-within-d-days
 * <p>
 * A conveyor belt has packages that must be shipped from one port to another within D days.
 * <p>
 * The i-th package on the conveyor belt has a weight of weights[i].  Each day, we load the ship with packages on the
 * conveyor belt (in the order given by weights). We may not load more weight than the maximum weight capacity of the ship.
 * <p>
 * Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within D days.
 * <p>
 * Input: weights = [1,2,3,4,5,6,7,8,9,10], D = 5
 * Output: 15
 * Explanation:
 * A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
 * 1st day: 1, 2, 3, 4, 5
 * 2nd day: 6, 7
 * 3rd day: 8
 * 4th day: 9
 * 5th day: 10
 * <p>
 * Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and splitting the packages
 * into parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.
 * <p>
 * Input: weights = [3,2,2,4,1,4], D = 3
 * Output: 6
 * Explanation:
 * A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
 * 1st day: 3, 2
 * 2nd day: 2, 4
 * 3rd day: 1, 4
 */
public class CapacityToShipWithinDDays {
    /**
     * Approach: Binary Search
     * <p>
     * {@link DivideChocolates} {@link KokoEatingBananas} {@link MinimumDifficultyOfJobSchedule} {@link MagneticForceBetweenTwoBalls}
     */
    public int shipWithinDays(int[] weights, int D) {
        int maxWeight = Integer.MIN_VALUE, totalWeight = 0;
        for (int weight : weights) {
            maxWeight = Math.max(maxWeight, weight);
            totalWeight += weight;
        }
        int low = maxWeight, high = totalWeight, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (daysToShipWithMaxWeight(mid, weights) <= D) { //if we can ship in <= D days, try a lower value to optimize
                ans = mid;
                high = mid - 1;
            } else { //try a larger value to get a valid result
                low = mid + 1;
            }
        }
        return ans;
    }

    //[1,2,3], 6 need to return 1
    //[1,2,3,4], 6 need to return 2
    private int daysToShipWithMaxWeight(int maxWeight, int[] weights) {
        int splits = 0, curTotalWeight = 0;
        for (int weight : weights) {
            curTotalWeight += weight;
            if (curTotalWeight > maxWeight) { //this is important to understand, if we have accumulated more than allowed, need to split
                curTotalWeight = weight;
                splits++;
            }
        }
        return splits + 1;  //need to do +1 because we have to return total no of partitions we have, not the no of splits performed
    }
}
