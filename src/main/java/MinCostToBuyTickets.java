import java.util.Arrays;

/**
 * https://leetcode.com/problems/minimum-cost-for-tickets/
 * <p>
 * In a country popular for train travel, you have planned some train travelling one year in advance.
 * The days of the year that you will travel is given as an array days.  Each day is an integer from 1 to 365.
 * <p>
 * Train tickets are sold in 3 different ways:
 * <p>
 * a 1-day pass is sold for costs[0] dollars;
 * a 7-day pass is sold for costs[1] dollars;
 * a 30-day pass is sold for costs[2] dollars.
 * The passes allow that many days of consecutive travel.
 * For example, if we get a 7-day pass on day 2, then we can travel for 7 days: day 2, 3, 4, 5, 6, 7, and 8.
 * <p>
 * Return the minimum number of dollars you need to travel every day in the given list of days.
 * <p>
 * Input: days = [1,4,6,7,8,20], costs = [2,7,15]
 * Output: 11
 * Explanation:
 * For example, here is one way to buy passes that lets you travel your travel plan:
 * On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
 * On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
 * On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
 * In total you spent $11 and covered all the days of your travel.
 */
public class MinCostToBuyTickets {
    /**
     * {@link BestTimeToBuyAndSellStock3} is a similar problem
     * Approach: Every day has 3 choices, and you have to return the min cost after taking each choice, so naturally DP comes into play
     * For taking day's pass today, you have to find the min cost at previous day + cost of day's pass
     * For taking week's pass today, you have to find the min cost at (i - 7) + 1 day, that's the day outside the 7 day window + cost of week pass
     * Similar approach for taking a month pass today
     */
    public int mincostTickets(int[] days, int[] costs) {
        int n = days.length;
        int[] oneDayPass = new int[n];
        int[] sevenDayPass = new int[n];
        int[] monthPass = new int[n];
        oneDayPass[0] = costs[0];
        sevenDayPass[0] = costs[1];
        monthPass[0] = costs[2];
        for (int i = 1; i < n; i++) {
            //min cost at previous day + cost of day's pass
            oneDayPass[i] = Math.min(oneDayPass[i - 1], Math.min(sevenDayPass[i - 1], monthPass[i - 1])) + costs[0];
            int firstDayToBuy7DayPass = days[i] - 7 + 1;
            int firstDayIndex = Arrays.binarySearch(days, firstDayToBuy7DayPass); //need to find index
            if (firstDayIndex < 0) { //need to find the upper bound
                firstDayIndex = -1 * firstDayIndex - 1;
            }
            if (firstDayIndex == 0) {//edge case to handle index out of bounds
                sevenDayPass[i] = costs[1];
            } else {
                //min cost at day outside the 7 day window + cost of week pass
                sevenDayPass[i] = Math.min(oneDayPass[firstDayIndex - 1], Math.min(sevenDayPass[firstDayIndex - 1], monthPass[firstDayIndex - 1])) + costs[1];
            }
            //repeat the same thing but for 30 days pass
            int firstDayToBuyMonthPass = days[i] - 30 + 1;
            firstDayIndex = Arrays.binarySearch(days, firstDayToBuyMonthPass);
            if (firstDayIndex < 0) {
                firstDayIndex = -1 * firstDayIndex - 1;
            }
            if (firstDayIndex == 0) {
                monthPass[i] = costs[2];
            } else {
                monthPass[i] = Math.min(oneDayPass[firstDayIndex - 1], Math.min(sevenDayPass[firstDayIndex - 1], monthPass[firstDayIndex - 1])) + costs[2];
            }
        }
        //return the min cost out of three choices
        return Math.min(oneDayPass[n - 1], Math.min(sevenDayPass[n - 1], monthPass[n - 1]));
    }
}
