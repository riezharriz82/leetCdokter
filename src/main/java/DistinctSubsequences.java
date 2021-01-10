import java.util.*;

/**
 * https://leetcode.com/problems/distinct-subsequences/
 * <p>
 * Given two strings s and t, return the number of distinct subsequences of s which equals t.
 * <p>
 * A string's subsequence is a new string formed from the original string by deleting some (can be none) of the characters
 * without disturbing the relative positions of the remaining characters. (i.e., "ACE" is a subsequence of "ABCDE" while "AEC" is not).
 * <p>
 * It's guaranteed the answer fits on a 32-bit signed integer.
 * <p>
 * Input: s = "babgbag", t = "bag"
 * Output: 5
 * Explanation:
 * As shown below, there are 5 ways you can generate "bag" from S.
 * babgbag
 * babgbag
 * babgbag
 * babgbag
 * babgbag
 */
public class DistinctSubsequences {
    /**
     * Approach: Top down recursive with memoization similar to previously solved two string questions
     * {@link LongestCommonSubsequence} {@link DeleteOperationForTwoString} {@link EditDistance}
     * <p>
     * Current approach is similar to {@link IsSubsequence}, if chars at source_index and target_index does not matches, decrement/skip source_index
     * else decrement both source_index and target_index or decrement source_index
     * The decrement source_index is required to generate all subsequences
     */
    public int numDistinctOptimized(String s, String t) {
        int[][] memoized = new int[s.length()][t.length()];
        for (int[] ints : memoized) {
            Arrays.fill(ints, -1);
        }
        return recur(s, t, s.length() - 1, t.length() - 1, memoized);
    }

    private int recur(String s, String t, int s_index, int t_index, int[][] memoized) {
        if (t_index < 0) {
            return 1;
        } else if (s_index < 0) {
            return 0;
        } else if (memoized[s_index][t_index] != -1) {
            return memoized[s_index][t_index];
        } else {
            if (s.charAt(s_index) == t.charAt(t_index)) {
                //this step is important to count all matching subsequences, if chars match, then either
                //1. include char in s and char in t
                //2. move to next char in s and keep current char in t
                return memoized[s_index][t_index] = recur(s, t, s_index - 1, t_index - 1, memoized)
                        + recur(s, t, s_index - 1, t_index, memoized);
            } else {
                return memoized[s_index][t_index] = recur(s, t, s_index - 1, t_index, memoized);
            }
        }
    }

    /**
     * Approach: My initial approach of storing indices and finding a greater index for the next index in target
     * Similar to {@link MinimumWindowSubsequence} but it timed out
     * Was feeling so proud when I thought I could do it by leveraging this prior trick but unfortunately this trick does not
     * yield a memoizable solution
     */
    public int numDistinct(String s, String t) {
        if (s.length() == 0 || t.length() == 0) {
            return 0;
        }
        Map<Character, TreeSet<Integer>> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.computeIfAbsent(s.charAt(i), __ -> new TreeSet<>()).add(i);
        }
        return recur(map, t, 0, -1);
    }

    private int recur(Map<Character, TreeSet<Integer>> map, String t, int stringIndex, int previousIndex) {
        if (stringIndex == t.length()) { //if all the indices have been completed, we found a valid combination
            return 1;
        }
        int count = 0;
        TreeSet<Integer> indices = map.getOrDefault(t.charAt(stringIndex), new TreeSet<>());
        //look for all indices greater than previousIndex
        //inclusive is set to false, to find > matches otherwise results in >= matches
        SortedSet<Integer> greaterIndices = indices.tailSet(previousIndex, false);
        for (Integer greaterIndex : greaterIndices) {
            count += recur(map, t, stringIndex + 1, greaterIndex);
        }
        return count;
    }
}
