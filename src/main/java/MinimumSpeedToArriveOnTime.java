/**
 * <pre>
 * https://leetcode.com/problems/minimum-speed-to-arrive-on-time/
 *
 * You are given a floating-point number hour, representing the amount of time you have to reach the office. To commute to the office,
 * you must take n trains in sequential order. You are also given an integer array dist of length n, where dist[i] describes the distance (in kilometers) of the ith train ride.
 *
 * Each train can only depart at an integer hour, so you may need to wait in between each train ride.
 *
 * For example, if the 1st train ride takes 1.5 hours, you must wait for an additional 0.5 hours before you can depart on the 2nd train ride at the 2 hour mark.
 * Return the minimum positive integer speed (in kilometers per hour) that all the trains must travel at for you to reach the office on time, or -1 if it is impossible to be on time.
 *
 * Tests are generated such that the answer will not exceed 107 and hour will have at most two digits after the decimal point.
 *
 * Input: dist = [1,3,2], hour = 6
 * Output: 1
 * Explanation: At speed 1:
 * - The first train ride takes 1/1 = 1 hour.
 * - Since we are already at an integer hour, we depart immediately at the 1 hour mark. The second train takes 3/1 = 3 hours.
 * - Since we are already at an integer hour, we depart immediately at the 4 hour mark. The third train takes 2/1 = 2 hours.
 * - You will arrive at exactly the 6 hour mark.
 *
 * Input: dist = [1,3,2], hour = 2.7
 * Output: 3
 * Explanation: At speed 3:
 * - The first train ride takes 1/3 = 0.33333 hours.
 * - Since we are not at an integer hour, we wait until the 1 hour mark to depart. The second train ride takes 3/3 = 1 hour.
 * - Since we are already at an integer hour, we depart immediately at the 2 hour mark. The third train takes 2/3 = 0.66667 hours.
 * - You will arrive at the 2.66667 hour mark.
 *
 * Input: dist = [1,3,2], hour = 1.9
 * Output: -1
 * Explanation: It is impossible because the earliest the third train can depart is at the 2 hour mark.
 *
 * Constraints:
 * n == dist.length
 * 1 <= n <= 10^5
 * 1 <= dist[i] <= 10^5
 * 1 <= hour <= 10^9
 * There will be at most two digits after the decimal point in hour.
 * </pre>
 */
public class MinimumSpeedToArriveOnTime {
    /**
     * Approach: Binary Search, Perform binary search on [1, 10e7] 10e7 limit is mentioned in the problem statement.
     * Follow errichto's binary search template.
     * <p>
     * Happy to solve this question on my own. I distinctly remember to see {@link MagneticForceBetweenTwoBalls} in weekly contest way back which I was
     * not able to solve back then. I was able to quickly solve this one in virtual contest in ~15 min.
     * <p>
     * {@link KokoEatingBananas}
     */
    public int minSpeedOnTime(int[] dist, double hour) {
        int low = 1, high = 10_000_000, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            double curTime = timeTaken(dist, mid);
            if (curTime > hour) {
                //increase speed
                low = mid + 1;
            } else {
                //we are able to arrive before time, store the possible candidate and try decreasing speed even further
                ans = mid;
                high = mid - 1;
            }
        }
        return ans;
    }

    private double timeTaken(int[] dist, int speed) {
        double curTime = 0;
        for (int i = 0; i < dist.length; i++) {
            double tempTime = ((double) dist[i]) / speed;
            if (i != dist.length - 1) {
                //round up for intermediate stations
                curTime += Math.ceil(tempTime);
            } else {
                //do not round up for the last station
                curTime += tempTime;
            }
        }
        return curTime;
    }
}
