import java.util.Arrays;

/**
 * https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/
 * <p>
 * Given two strings s1, s2, find the lowest ASCII sum of deleted characters to make two strings equal.
 * <p>
 * Input: s1 = "delete", s2 = "leet"
 * Output: 403
 * Explanation: Deleting "dee" from "delete" to turn the string into "let",
 * adds 100[d]+101[e]+101[e] to the sum.  Deleting "e" from "leet" adds 101[e] to the sum.
 * At the end, both strings are equal to "let", and the answer is 100+101+101+101 = 403.
 * If instead we turned both strings into "lee" or "eet", we would get answers of 433 or 417, which are higher.
 */
public class MinimumAsciiDeleteSumForTwoStrings {
    /**
     * Approach: Exactly similar to {@link DeleteOperationForTwoString}
     * Instead of minimizing length, we have to minimize ascii sum of characters being deleted
     */
    public int minimumDeleteSum(String s1, String s2) {
        int[][] memoized = new int[s1.length()][s2.length()];
        for (int[] ints : memoized) {
            Arrays.fill(ints, -1);
        }
        return recur(s1, s2, s1.length() - 1, s2.length() - 1, memoized);
    }

    private int recur(String word1, String word2, int idx1, int idx2, int[][] memoized) {
        if (idx1 < 0 && idx2 >= 0) { //word1 is done but word2 isn't
            int sum = 0;
            while (idx2 >= 0) {
                sum += word2.charAt(idx2--);
            }
            return sum;
        } else if (idx2 < 0 && idx1 >= 0) { //word2 is done but word1 isn't
            int sum = 0;
            while (idx1 >= 0) {
                sum += word1.charAt(idx1--);
            }
            return sum;
        } else if (idx1 < 0 && idx2 < 0) { //both words are done
            return 0;
        } else if (memoized[idx1][idx2] != -1) {
            return memoized[idx1][idx2];
        } else if (word1.charAt(idx1) == word2.charAt(idx2)) { //characters match, do nothing and skip to next character
            return memoized[idx1][idx2] = recur(word1, word2, idx1 - 1, idx2 - 1, memoized);
        } else {
            int deleteCharAtIdx1 = word1.charAt(idx1) + recur(word1, word2, idx1 - 1, idx2, memoized);
            int deleteCharAtIdx2 = word2.charAt(idx2) + recur(word1, word2, idx1, idx2 - 1, memoized);
            return memoized[idx1][idx2] = Math.min(deleteCharAtIdx1, deleteCharAtIdx2);
        }
    }
}
