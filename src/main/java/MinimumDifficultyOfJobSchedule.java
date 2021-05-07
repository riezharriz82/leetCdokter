import java.util.Arrays;

/**
 * https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/
 * <p>
 * You want to schedule a list of jobs in d days. Jobs are dependent (i.e To work on the i-th job, you have to finish all the jobs j where 0 <= j < i).
 * <p>
 * You have to finish at least one task every day. The difficulty of a job schedule is the sum of difficulties of each day of the d days.
 * The difficulty of a day is the maximum difficulty of a job done in that day.
 * <p>
 * Given an array of integers jobDifficulty and an integer d. The difficulty of the i-th job is jobDifficulty[i].
 * <p>
 * Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs return -1.
 * <p>
 * Input: jobDifficulty = [6,5,4,3,2,1], d = 2
 * Output: 7
 * Explanation: First day you can finish the first 5 jobs, total difficulty = 6.
 * Second day you can finish the last job, total difficulty = 1.
 * The difficulty of the schedule = 6 + 1 = 7
 * <p>
 * Input: jobDifficulty = [9,9,9], d = 4
 * Output: -1
 * Explanation: If you finish a job per day you will still have a free day. you cannot find a schedule for the given jobs.
 * <p>
 * Input: jobDifficulty = [1,1,1], d = 3
 * Output: 3
 * Explanation: The schedule is one job per day. total difficulty will be 3.
 * <p>
 * Input: jobDifficulty = [7,1,7,1,7,1], d = 3
 * Output: 15
 * <p>
 * Input: jobDifficulty = [11,111,22,222,33,333,44,444], d = 6
 * Output: 843
 * <p>
 * Constraints:
 * 1 <= jobDifficulty.length <= 300
 * 0 <= jobDifficulty[i] <= 1000
 * 1 <= d <= 10
 */
public class MinimumDifficultyOfJobSchedule {
    /**
     * Approach: Problem statement reduces to split the array into d subarrays such that the sum of maximum in each subarray is minimized
     * Initially I thought of using binary search but could not figure out how to figure how many splits are required such that sum of maximums == mid
     * <p>
     * Went ahead with recurrence + memoization solution. Time complexity: O(nnd)
     * <p>
     * An more optimized solution involving monotonic stack reduces time complexity to O(nd)
     * <p>
     * {@link SplitArrayLargestSum} {@link DivideChocolates} {@link LargestNumberWithDigitsThatSumUpToTarget} {@link LargestSumOfAverages}
     */
    public int minDifficulty(int[] jobDifficulty, int d) {
        if (d > jobDifficulty.length) {
            return -1;
        }
        int[][] memoized = new int[d + 1][jobDifficulty.length];
        for (int[] ints : memoized) {
            Arrays.fill(ints, -1);
        }
        return recur(jobDifficulty, d, 0, memoized);
    }

    private int recur(int[] jobDifficulty, int splits, int index, int[][] memoized) {
        if (index == jobDifficulty.length) { //if reached the end, make sure that we used up all the splits
            if (splits == 0) {
                return 0;
            } else { //splits remaining, invalid state
                return Integer.MAX_VALUE;
            }
        } else if (splits == 0) { //if no more splits possible
            return Integer.MAX_VALUE;
        } else if (memoized[splits][index] != -1) {
            return memoized[splits][index];
        }
        int curMax = 0, minSumOfMaximums = Integer.MAX_VALUE;
        for (int i = index; i < jobDifficulty.length; i++) { //make splits at all possible places and keep track of minimum sum of maximum elements
            curMax = Math.max(jobDifficulty[i], curMax);
            int remaining = recur(jobDifficulty, splits - 1, i + 1, memoized);
            if (remaining != Integer.MAX_VALUE) {
                minSumOfMaximums = Math.min(minSumOfMaximums, curMax + remaining);
            }
        }
        return memoized[splits][index] = minSumOfMaximums;
    }
}
