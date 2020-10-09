import javafx.util.Pair;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/ones-and-zeroes/
 * <p>
 * Given an array, strs, with strings consisting of only 0s and 1s. Also two integers m and n.
 * <p>
 * Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. Each 0 and 1 can be used at most once.
 * <p>
 * Input: strs = ["10","0001","111001","1","0"], m = 5, n = 3
 * Output: 4
 * Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are "10","0001","1","0".
 * <p>
 * Input: strs = ["10","0","1"], m = 1, n = 1
 * Output: 2
 * Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".
 */
public class OnesAndZeroes {
    /**
     * Approach: Problem can be reduced to 0-1 knapsack problem
     * Every string has two option, either get picked or be skipped.
     * If picked, reduce the m and n by the no of zeroes and ones present in the string. Increase the current length.
     * If skipped, move on to next index without affecting m and n
     */
    public int findMaxForm(String[] strs, int m, int n) {
        Pair<Integer, Integer>[] counts = new Pair[strs.length]; //count of zeroes and ones
        for (int i = 0; i < strs.length; i++) {
            int zeroes = 0, ones = 0;
            String candidate = strs[i];
            for (int j = 0; j < candidate.length(); j++) {
                if (candidate.charAt(j) == '0') {
                    zeroes++;
                } else {
                    ones++;
                }
            }
            counts[i] = new Pair<>(zeroes, ones);
        }
        int[][][] memoized = new int[m][n][counts.length];
        for (int[][] ints : memoized) {
            for (int[] anInt : ints) {
                Arrays.fill(anInt, -1);
            }
        }
        return recur(m, n, 0, counts, memoized);
    }

    private int recur(int m, int n, int idx, Pair<Integer, Integer>[] counts, int[][][] memoized) {
        if (idx == counts.length) {
            return 0;
        }
        if (m == 0 && n == 0) {
            return 0;
        }
        if (memoized[m][n][idx] != -1) {
            return memoized[m][n][idx];
        }
        Pair<Integer, Integer> curCount = counts[idx];
        int pickIndex = 0;
        if (m - curCount.getKey() >= 0 && n - curCount.getValue() >= 0) { //if this index can be picked
            pickIndex = 1 + recur(m - curCount.getKey(), n - curCount.getValue(), idx + 1, counts, memoized);
        }
        int skipIndex = recur(m, n, idx + 1, counts, memoized);
        return memoized[m][n][idx] = Math.max(pickIndex, skipIndex);
    }
}
