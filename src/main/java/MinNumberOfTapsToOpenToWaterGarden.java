import java.util.Arrays;

/**
 * https://leetcode.com/problems/minimum-number-of-taps-to-open-to-water-a-garden/
 * <p>
 * There is a one-dimensional garden on the x-axis. The garden starts at the point 0 and ends at the point n. (i.e The length of the garden is n).
 * <p>
 * There are n + 1 taps located at points [0, 1, ..., n] in the garden.
 * <p>
 * Given an integer n and an integer array ranges of length n + 1 where ranges[i] (0-indexed) means
 * the i-th tap can water the area [i - ranges[i], i + ranges[i]] if it was open.
 * <p>
 * Return the minimum number of taps that should be open to water the whole garden, If the garden cannot be watered return -1.
 * <p>
 * Input: n = 5, ranges = [3,4,1,1,0,0]
 * Output: 1
 * Explanation: The tap at point 0 can cover the interval [-3,3]
 * The tap at point 1 can cover the interval [-3,5]
 * The tap at point 2 can cover the interval [1,3]
 * The tap at point 3 can cover the interval [2,4]
 * The tap at point 4 can cover the interval [4,4]
 * The tap at point 5 can cover the interval [5,5]
 * Opening Only the second tap will water the whole garden [0,5]
 */
public class MinNumberOfTapsToOpenToWaterGarden {
    /**
     * Approach: Similar to {@link JumpGame2}
     * First you have to modify the input to match jump games input i.e. need a way of finding the max index an index can jump to
     * Once converted it will be similar to greedy logic in JumpGame2
     */
    public int minTapsGreedy(int n, int[] ranges) {
        int[] jumpTo = new int[n + 1];
        for (int i = 0; i < ranges.length; i++) {
            int left = Math.max(0, i - ranges[i]);
            jumpTo[left] = Math.min(n, i + ranges[i]); //left index can jump to the target index
        }
        int levels = 0, levelEnd = 0, farthestReachableIndex = 0;
        for (int i = 0; i < n && i <= levelEnd; i++) {
            farthestReachableIndex = Math.max(farthestReachableIndex, jumpTo[i]);
            if (i == levelEnd) { //once a level is done, update the indices of the end of the next level and increment level count
                levelEnd = farthestReachableIndex;
                levels++;
            }
        }
        //if last index was reached, return levels jumped so far otherwise return -1
        return farthestReachableIndex == n ? levels : -1;
    }

    /**
     * Approach: Similar to {@link JumpGame2} but care must be taken to return -1 if the start isn't reachable ie in case of disconnected intervals
     * Sort by start is required
     */
    public int minTaps(int n, int[] ranges) {
        int[][] intervals = new int[ranges.length][2];
        for (int i = 0; i < intervals.length; i++) {
            //prune the ranges as we are interested only in [0,n]
            int start = Math.max(i - ranges[i], 0);
            int end = Math.min(i + ranges[i], n);
            intervals[i] = new int[]{start, end};
        }
        Arrays.sort(intervals, (o1, o2) -> Integer.compare(o1[0], o2[0]));
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        if (intervals[0][0] == 0) { //check if first index is reachable or not
            dp[0] = 0;
        } else {
            return -1;
        }
        for (int i = 0; i < intervals.length; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];
            int tapsAtStart = dp[start];
            if (tapsAtStart == Integer.MAX_VALUE) { //if start index isn't reachable
                return -1;
            }
            for (int j = start; j <= end; j++) {
                dp[j] = Math.min(dp[j], tapsAtStart + 1);
            }
        }
        return dp[n];
    }
}
